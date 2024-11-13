package com.chillizardinteractive.modelo.turnState;

import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.gameState.GameState;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

public class InicioTurno implements GameState {
    private final GameView view;

    public InicioTurno(GameView view) {
        this.view = view;
    }

    @Override
    public void iniciarTurno(GameContext context) {
        Player currentPlayer = context.getCurrentPlayer();
        view.mostrarMensajePrivado(currentPlayer.getName(), "Iniciando turno del jugador actual...");

        currentPlayer.incrementarNenSpaces();
        currentPlayer.rellenarNenPoints();

        Card drawnCard = currentPlayer.getDeck().sacarCarta();
        if (drawnCard != null) {
            currentPlayer.getMano().agregarCartasMano(drawnCard);
            view.mostrarCartaRobada(currentPlayer.getName(), drawnCard.getDescription());
        } else {
            view.mostrarMensajePrivado(currentPlayer.getName(), "No tienes más cartas en el mazo.");
        }

        context.setState(new FaseAccion(view));
    }

    // Métodos no válidos en el estado de inicio de turno
    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarError("No se puede iniciar el juego en el estado de inicio de turno.");
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        view.mostrarError("No se puede lanzar la moneda en el estado de inicio de turno.");
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarError("No se puede pasar a la fase de combate en el estado de inicio de turno.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        view.mostrarError("No se puede terminar el turno en el estado de inicio de turno.");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        view.mostrarError("No se puede finalizar el juego en el estado de inicio de turno.");
    }

}