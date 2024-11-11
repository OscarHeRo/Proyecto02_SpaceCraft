package com.chillizardinteractive.modelo.turnState;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.gameState.GameState;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.util.Scanner;

public class FaseAccion implements GameState {
    private GameView view;

    public FaseAccion(GameView view) {
        this.view = view;
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarMensaje("Fase de combate comenzando. Los jugadores pueden atacar con sus criaturas...");
        System.out.println(context.getCurrentPlayer().getMano().toString());

        context.autorizarAtaque();
        context.setState(new TerminarTurno(view));
    }

    @Override
    public void iniciarJuego(GameContext context) {}

    @Override
    public void iniciarTurno(GameContext context) {
        view.mostrarMensaje("Fase de acción. Los jugadores pueden colocar minions en el tablero.");
        colocarMinions(context);
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

            Card cartaSeleccionada = currentPlayer.getMano().getCartasEnMano().get(cartaIndex);

            if (cartaSeleccionada instanceof MinionCard) {
                System.out.println("Seleccione una posición para el minion (1-5):");
                int posicion = scanner.nextInt() - 1;

                if (board.placeMinion((MinionCard) cartaSeleccionada, posicion)) {
                    System.out.println("Minion colocado en la posición " + (posicion + 1));
                    currentPlayer.getMano().getCartasEnMano().remove(cartaSeleccionada);
                } else {
                    System.out.println("Posición inválida o ya ocupada.");
                }
            } else {
                System.out.println("Carta no válida. Solo se pueden colocar minions.");
            }
        }
    }

    @Override
    public void terminarTurno(GameContext context) {
        context.switchPlayer();
        context.setState(new FaseAccion(view));
    }

    @Override
    public void finalizarJuego(GameContext context) {
        view.mostrarMensaje("El juego ha terminado.");
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        view.mostrarMensaje("No se puede lanzar moneda en el estado de fase de acción.");
    }
}