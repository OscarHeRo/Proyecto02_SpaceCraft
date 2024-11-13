package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;
import com.chillizardinteractive.modelo.card.SpellCard;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.gameState.TerminarJuego;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.modelo.turnState.LanzamientoMoneda;
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

    public GameContext getContext() {
        return context;
    }

    public void iniciarJuego() {
        view.mostrarMensajePublico("Iniciando el juego...");
        context.setState(new LanzamientoMoneda(view));
        context.lanzarMoneda();
    }

    public void lanzarMoneda() {
        context.lanzarMoneda();
    }

    public void iniciarTurno() {
        context.iniciarTurno();
    }


    public void terminarTurno() {
        context.terminarTurno();
    }

    public void finalizarJuego() {
        context.finalizarJuego();
    }

    private void startTurnTimer() {
        scheduler.scheduleAtFixedRate(() -> {
            view.mostrarMensajePublico("Tiempo de turno restante: " + context.getTurnTime() + " segundos");
            context.decrementTurnTime();
            if (context.getTurnTime() <= 0) {
                terminarTurno();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void mostrarEstadoJugador(String nombre, int vida, int nenSpaces, int nenPoints) {
        view.mostrarMensajePublico("Estado de " + nombre + ": Vida=" + vida + ", NenSpaces=" + nenSpaces + ", NenPoints=" + nenPoints);
    }

    public void mostrarCartaRobada(String nombreJugador, String descripcionCarta) {
        view.mostrarMensajePrivado(nombreJugador, "Has robado la carta: " + descripcionCarta);
    }

    public void mostrarMensajePublico(String mensaje) {
        view.mostrarMensajePublico(mensaje);
    }

    public void mostrarMensajePrivado(String nombreJugador, String mensaje) {
        view.mostrarMensajePrivado(nombreJugador, mensaje);
    }

    public void mostrarError(String mensaje) {
        view.mostrarError(mensaje);
    }

    public void mostrarMano(Player player) {
        view.mostrarMensajePrivado(player.getName(), "Mano de " + player.getName() + ": " + player.getMano().toString());
    }

    public void colocarCartaEnTablero(Player player, Board board) {
        view.mostrarMensajePrivado(player.getName(), "Seleccione una carta para colocar en el tablero (1-" + player.getMano().getCartasEnMano().size() + "): ");
        int cartaIndex = scanner.nextInt() - 1;
        Card cartaSeleccionada = player.getMano().getCartasEnMano().get(cartaIndex);
        if (cartaSeleccionada instanceof MinionCard) {
            view.mostrarMensajePrivado(player.getName(), "Seleccione una posición para el minion (1-5): ");
            int posicion = scanner.nextInt() - 1;
            if (board.placeMinion((MinionCard) cartaSeleccionada, posicion)) {
                view.mostrarMensajePrivado(player.getName(), "Minion colocado en la posición " + (posicion + 1));
                player.getMano().getCartasEnMano().remove(cartaSeleccionada);
            } else {
                view.mostrarError("Posición inválida o ya ocupada.");
            }
        } else if (cartaSeleccionada instanceof SpellCard) {
            view.mostrarMensajePrivado(player.getName(), "Seleccione una posición para el hechizo (1-5): ");
            int posicion = scanner.nextInt() - 1;
            if (board.placeSpell((SpellCard) cartaSeleccionada, posicion)) {
                view.mostrarMensajePrivado(player.getName(), "Hechizo colocado en la posición " + (posicion + 1));
                player.getMano().getCartasEnMano().remove(cartaSeleccionada);
            } else {
                view.mostrarError("Posición inválida o ya ocupada.");
            }
        } else {
            view.mostrarError("Carta no válida.");
        }
    }

    public void atacarConMinion(Player player, Board board) {
        view.mostrarMensajePrivado(player.getName(), "Seleccione un minion para atacar (1-5): ");
        int atacanteIndex = scanner.nextInt() - 1;
        view.mostrarMensajePrivado(player.getName(), "Seleccione un objetivo para atacar (1-5): ");
        int objetivoIndex = scanner.nextInt() - 1;
        if (board.attackWithMinion(atacanteIndex, objetivoIndex)) {
            view.mostrarMensajePrivado(player.getName(), "Ataque realizado.");
        } else {
            view.mostrarError("Ataque fallido.");
        }
    }
}