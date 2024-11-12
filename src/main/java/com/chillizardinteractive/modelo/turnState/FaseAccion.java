package com.chillizardinteractive.modelo.turnState;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.gameState.GameState;
import com.chillizardinteractive.modelo.gameState.TerminarJuego;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.util.Scanner;

public class FaseAccion implements GameState {
    private GameView view;

    public FaseAccion(GameView view) {
        this.view = view;
    }

    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarError("No se puede iniciar el juego en el estado de fase de acción.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        view.mostrarMensaje("Fase de acción. Los jugadores pueden colocar minions en el tablero y atacar.");
        colocarMinions(context);
        atacar(context);
        context.setState(new TerminarTurno(view));
        context.terminarTurno(); // Terminar el turno después de la fase de acción
    }

    private void colocarMinions(GameContext context) {
        Player currentPlayer = context.getCurrentPlayer();
        Board board = context.getBoard();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Mano de " + currentPlayer.getName() + ": " + currentPlayer.getMano().toString());
            System.out.println("Seleccione una carta para colocar en el tablero (1-" + currentPlayer.getMano().getCartasEnMano().size() + ") o 0 para terminar:");
            int cartaIndex = scanner.nextInt() - 1;

            if (cartaIndex == -1) {
                break;
            }

            try {
                Card cartaSeleccionada = currentPlayer.getMano().getCartaByIndex(cartaIndex);

                if (cartaSeleccionada instanceof MinionCard) {
                    System.out.println("Seleccione una posición para el minion (1-5):");
                    int posicion = scanner.nextInt() - 1;

                    if (board.placeMinion((MinionCard) cartaSeleccionada, posicion)) {
                        System.out.println("Minion colocado en la posición " + (posicion + 1));
                        currentPlayer.getMano().removerCartasByIndex(cartaIndex);
                    } else {
                        System.out.println("Posición inválida o ya ocupada.");
                    }
                } else {
                    System.out.println("Carta no válida. Solo se pueden colocar minions.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void atacar(GameContext context) {
        Player currentPlayer = context.getCurrentPlayer();
        Player opponentPlayer = context.getOpponentPlayer();
        Board board = context.getBoard();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione un minion para atacar (1-5) o 0 para terminar:");
            int atacanteIndex = scanner.nextInt() - 1;

            if (atacanteIndex == -1) {
                break;
            }

            MinionCard atacante = board.getMinion(atacanteIndex);
            if (atacante == null) {
                System.out.println("No hay minion en esa posición.");
                continue;
            }

            System.out.println("Seleccione un objetivo para atacar (1-5) o 0 para atacar al Hunter:");
            int objetivoIndex = scanner.nextInt() - 1;

            if (objetivoIndex == -1) {
                if (board.hasTauntMinion(opponentPlayer)) {
                    System.out.println("No puedes atacar al Hunter mientras haya minions con Taunt en el tablero.");
                } else {
                    opponentPlayer.recibirDanio(atacante.getAttack());
                    System.out.println("El Hunter ha recibido " + atacante.getAttack() + " puntos de daño.");
                    if (opponentPlayer.getHealth() <= 0) {
                        context.setState(new TerminarJuego(view));
                        context.finalizarJuego();
                        return;
                    }
                }
            } else {
                MinionCard objetivo = board.getMinion(objetivoIndex);
                if (objetivo == null) {
                    System.out.println("No hay minion en esa posición.");
                } else {
                    objetivo.recibirDanio(atacante.getAttack());
                    atacante.recibirDanio(objetivo.getAttack());
                    System.out.println("El minion objetivo ha recibido " + atacante.getAttack() + " puntos de daño.");
                    System.out.println("El minion atacante ha recibido " + objetivo.getAttack() + " puntos de daño.");
                    if (objetivo.estaMuerto()) {
                        board.removeMinion(objetivoIndex);
                        System.out.println("El minion objetivo ha sido destruido.");
                    }
                    if (atacante.estaMuerto()) {
                        board.removeMinion(atacanteIndex);
                        System.out.println("El minion atacante ha sido destruido.");
                    }
                }
            }
        }
    }

    @Override
    public void terminarTurno(GameContext context) {
        context.switchPlayer();
        context.setState(new InicioTurno(view));
    }

    @Override
    public void finalizarJuego(GameContext context) {
        view.mostrarMensaje("El juego ha terminado.");
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        view.mostrarMensaje("No se puede lanzar moneda en el estado de fase de acción.");
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarError("No se puede pasar a la fase de combate en el estado de fase de acción.");
    }
}