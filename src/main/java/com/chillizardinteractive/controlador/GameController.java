package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;
import com.chillizardinteractive.modelo.card.SpellCard;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {
    private GameContext context;
    private GameView view;
    private Scanner scanner;
    private ScheduledExecutorService scheduler;

    public GameController(GameContext context, GameView view) {
        this.context = context;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void iniciarJuego() {
        context.iniciarJuego();
    }

    public void lanzarMoneda() {
        context.lanzarMoneda();
    }

    public void iniciarTurno() {
        context.iniciarTurno();
        startTurnTimer();
    }

    public void faseAccion() {
        context.faseCombate();
    }

    public void terminarTurno() {
        context.terminarTurno();
    }

    public void finalizarJuego() {
        context.finalizarJuego();
        scheduler.shutdown();
    }

    private void startTurnTimer() {
        scheduler.schedule(() -> {
            System.out.println("Tiempo de turno agotado. Terminando turno...");
            terminarTurno();
        }, 60, TimeUnit.SECONDS); // 60 segundos por turno
    }

    public void mostrarEstadoJugador(String nombre, int vida, int nenSpaces, int nenPoints) {
        view.mostrarEstadoJugador(nombre, vida, nenSpaces, nenPoints);
    }

    public void mostrarCartaRobada(String nombreJugador, String descripcionCarta) {
        view.mostrarCartaRobada(nombreJugador, descripcionCarta);
    }
/* 
    public void mostrarMensaje(String mensaje) {
        view.mostrarMensaje(mensaje);
    }
*/

    public void mostrarError(String mensaje) {
        view.mostrarError(mensaje);
    }

    public void mostrarMano(Player player) {
        view.mostrarMensajePrivado(player.getName(),"Mano de " + player.getName() + ": " + player.getMano().toString());
    }

    public void colocarCartaEnTablero(Player player, Board board) {
        if (player.getMano().getCartasEnMano().isEmpty()) {
            view.mostrarError("La mano está vacía. No se puede colocar ninguna carta en el tablero.");
            return;
        }

        mostrarMano(player);
        view.mostrarMensajePrivado(player.getName(), "Seleccione una carta para colocar en el tablero (1-" + player.getMano().getCartasEnMano().size() + "): ");
        int cartaIndex = scanner.nextInt() - 1;

        if (cartaIndex < 0 || cartaIndex >= player.getMano().getCartasEnMano().size()) {
            view.mostrarError("Índice de carta no válido.");
            return;
        }

        Card cartaSeleccionada = player.getMano().getCartasEnMano().get(cartaIndex);
        if (cartaSeleccionada instanceof MinionCard) {
            view.mostrarMensajePrivado(player.getName() ,"Seleccione una posición para el minion (1-5): ");
            int posicion = scanner.nextInt() - 1;
            if (board.placeMinion((MinionCard) cartaSeleccionada, posicion)) {
                view.mostrarMensajePrivado(player.getName() ,"Minion colocado en la posición " + (posicion + 1));
                player.getMano().getCartasEnMano().remove(cartaSeleccionada);
            } else {
                view.mostrarError("Posición inválida o ya ocupada.");
            }
        } else if (cartaSeleccionada instanceof SpellCard) {
            view.mostrarMensajePrivado(player.getName() ,"Seleccione una posición para el hechizo (1-5): ");
            int posicion = scanner.nextInt() - 1;
            if (board.placeSpell((SpellCard) cartaSeleccionada, posicion)) {
                view.mostrarMensajePrivado(player.getName() , "Hechizo colocado en la posición " + (posicion + 1));
                player.getMano().getCartasEnMano().remove(cartaSeleccionada);
            } else {
                view.mostrarError("Posición inválida o ya ocupada.");
            }
        } else {
            view.mostrarError("Carta no válida.");
        }
    }

    public void atacarConMinion(Player player, Board board) {
        mostrarMano(player);
        view.mostrarMensajePrivado(player.getName() ,"Seleccione un minion para atacar (1-5): ");
        int atacanteIndex = scanner.nextInt() - 1;

        if (atacanteIndex < 0 || atacanteIndex >= board.getMinions().length) {
            view.mostrarError("Índice de minion no válido.");
            return;
        }

        MinionCard atacante = board.getMinion(atacanteIndex);
        if (atacante == null) {
            view.mostrarError("No hay minion en esa posición.");
            return;
        }

        view.mostrarMensajePrivado(player.getName(), "Seleccione un objetivo para atacar (1-5) o 0 para atacar al Hunter: ");
        int objetivoIndex = scanner.nextInt() - 1;

        if (objetivoIndex == -1) {
            if (board.hasTauntMinion(context.getOpponentPlayer())) {
                view.mostrarError("No puedes atacar al Hunter mientras haya minions con Taunt en el tablero.");
            } else {
                context.getOpponentPlayer().recibirDanio(atacante.getAttack());
                view.mostrarMensajePublico("El Hunter ha recibido " + atacante.getAttack() + " puntos de daño.");
                if (context.getOpponentPlayer().getHealth() <= 0) {
                    context.finalizarJuego();
                }
            }
        } else {
            if (objetivoIndex < 0 || objetivoIndex >= board.getMinions().length) {
                view.mostrarError("Índice de objetivo no válido.");
                return;
            }

            MinionCard objetivo = board.getMinion(objetivoIndex);
            if (objetivo == null) {
                view.mostrarError("No hay minion en esa posición.");
            } else {
                objetivo.recibirDanio(atacante.getAttack());
                atacante.recibirDanio(objetivo.getAttack());
                view.mostrarMensajePublico("El minion objetivo ha recibido " + atacante.getAttack() + " puntos de daño.");
                view.mostrarMensajePublico("El minion atacante ha recibido " + objetivo.getAttack() + " puntos de daño.");
                if (objetivo.estaMuerto()) {
                    board.removeMinion(objetivoIndex);
                    view.mostrarMensajePublico("El minion objetivo ha sido destruido.");
                }
                if (atacante.estaMuerto()) {
                    board.removeMinion(atacanteIndex);
                    view.mostrarMensajePublico("El minion atacante ha sido destruido.");
                }
            }
        }
    }
}