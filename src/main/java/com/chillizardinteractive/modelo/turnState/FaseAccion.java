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
        if (currentPlayer == null) {
            view.mostrarError("El jugador actual no está disponible.");
            return;
        }
        
        Board board = context.getBoard();
        if (board == null) {
            view.mostrarError("El tablero no está disponible.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Mano de " + currentPlayer.getName() + ":");
                // Assuming display of hand cards and handling player choice to place minions
                // Add appropriate break condition to exit the loop.
            }
        } catch (Exception e) {
            view.mostrarError("Error al colocar minions: " + e.getMessage());
        }
    }

    private void atacar(GameContext context) {
        Player currentPlayer = context.getCurrentPlayer();
        Board board = context.getBoard();
        
        if (currentPlayer == null || board == null) {
            view.mostrarError("Error: jugador o tablero no disponible para el ataque.");
            return;
        }
        
        // Placeholder logic for attacking
        // Ensure appropriate exception handling as required
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lanzarMoneda'");
    }

    @Override
    public void faseCombate(GameContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'faseCombate'");
    }

    @Override
    public void terminarTurno(GameContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'terminarTurno'");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizarJuego'");
    }
}
