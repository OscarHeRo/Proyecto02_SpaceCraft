package com.chillizardinteractive.modelo.state;

import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.vista.GameView;

public class InicioTurno implements GameState {
    private GameView view;

    public InicioTurno(GameView view) {
        this.view = view;
    }

    /**
     * Inicio de turno: el jugador actual recibe una carta de la pila de su mazo.
     * 
     * Precondiciones:
     * - El juego ha sido iniciado.
     * - Se ha lanzado la moneda y se ha determinado el jugador actual.
     */
    @Override
    public void iniciarTurno(GameContext context) {
        view.mostrarMensaje("Iniciando turno del jugador actual...");

        Player currentPlayer = context.getCurrentPlayer();
        Card drawnCard = currentPlayer.getDeck().sacarCarta();

        if (drawnCard != null) {
            view.mostrarCartaRobada(currentPlayer.getName(), drawnCard.getDescription());
        } else {
            view.mostrarMensaje(currentPlayer.getName() + " no tiene más cartas en el mazo.");
        }

        context.setState(new FaseAccion(view));
    }

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