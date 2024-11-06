package com.chillizardinteractive.modelo.state;

import com.chillizardinteractive.modelo.card.Card;

public class InicioTurno implements GameState {
    /**
     * Inicio de turno: el jugador actual recibe una carta de la pila de su mazo.
     * 
     * Precondiciones:
     * - El juego ha sido iniciado.
     * - Se ha lanzado la moneda y se ha determinado el jugador actual.
     */
    @Override
    public void iniciarTurno(GameContext context) {
        System.out.println("Iniciando turno del jugador actual...");

        Player currentPlayer = context.getCurrentPlayer();
        Card drawnCard = currentPlayer.getDeck().sacarCarta();

        if (drawnCard != null) {
            System.out.println(currentPlayer.getName() + " ha robado la carta: " + drawnCard.getDescription());
        } else {
            System.out.println(currentPlayer.getName() + " no tiene más cartas en el mazo.");
        }

        context.setState(new FaseAccion());
    }

    @Override
    public void iniciarJuego(GameContext context) {
        System.out.println("Error: No se puede iniciar el juego en el estado de inicio de turno.");
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        System.out.println("Error: No se puede lanzar la moneda en el estado de inicio de turno.");
    }

    @Override
    public void faseCombate(GameContext context) {
        System.out.println("Error: No se puede pasar a la fase de combate en el estado de inicio de turno.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        System.out.println("Error: No se puede terminar el turno en el estado de inicio de turno.");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        System.out.println("Error: No se puede finalizar el juego en el estado de inicio de turno.");
    }
}