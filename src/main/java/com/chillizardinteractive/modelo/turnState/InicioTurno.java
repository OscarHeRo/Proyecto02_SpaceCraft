package com.chillizardinteractive.modelo.turnState;

import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.gameState.GameState;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

public class InicioTurno implements GameState {
    private GameView view;

    public InicioTurno(GameView view) {
        this.view = view;
    }

    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarError("No se puede iniciar el juego en el estado de inicio de turno.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        Player currentPlayer = context.getCurrentPlayer();
        if (currentPlayer == null) {
            view.mostrarError("No se puede iniciar el turno porque el jugador actual es nulo.");
            return;
        }
        if (currentPlayer.getDeck() == null) {
            view.mostrarError("El jugador actual no tiene un mazo asignado.");
            return;
        }
    
        view.mostrarMensajePrivado(currentPlayer.getName(), "Iniciando turno del jugador actual...");
    
        // Agregar un espacio de nen y rellenar los puntos de nen
        currentPlayer.incrementarNenSpaces();
        currentPlayer.rellenarNenPoints();
    
        // Robar una carta y ponerla en la mano del jugador actual
        Card drawnCard = currentPlayer.getDeck().sacarCarta();
        if (drawnCard != null) {
            currentPlayer.getMano().agregarCartasMano(drawnCard);
            view.mostrarCartaRobada(currentPlayer.getName(), drawnCard.getDescription());
        } else {
            view.mostrarMensajePrivado(currentPlayer.getName(), "No tienes más cartas en el mazo.");
        }
    
        // Cambia el estado al de Fase de Acción
        context.setState(new FaseAccion(view));
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