package com.chillizardinteractive.modelo.turnState;

import com.chillizardinteractive.modelo.gameState.GameState;
import com.chillizardinteractive.modelo.gameState.Moneda;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.vista.GameView;

public class LanzamientoMoneda implements GameState {
    private GameView view;

    public LanzamientoMoneda(GameView view) {
        this.view = view;
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        view.mostrarMensaje("Lanzando moneda para determinar el primer jugador...");

        // Suponiendo que el jugador 1 elige cara
        String eleccionJugador1 = "cara"; // "cara" o "cruz"
        String resultado = Moneda.lanzarMoneda();

        if (resultado.equals(eleccionJugador1)) {
            view.mostrarMensaje("Jugador 1 gana el lanzamiento de moneda y comienza primero.");
            context.setCurrentPlayer(context.getPlayer1());
        } else {
            view.mostrarMensaje("Jugador 2 gana el lanzamiento de moneda y comienza primero.");
            context.setCurrentPlayer(context.getPlayer2());
        }

        context.setState(new InicioTurno(view));
        context.iniciarTurno();
    }

    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarError("No se puede iniciar el juego en el estado de lanzamiento de moneda.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        view.mostrarError("No se puede iniciar el turno en el estado de lanzamiento de moneda.");
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarError("No se puede pasar a la fase de combate en el estado de lanzamiento de moneda.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        view.mostrarError("No se puede terminar el turno en el estado de lanzamiento de moneda.");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        view.mostrarError("No se puede finalizar el juego en el estado de lanzamiento de moneda.");
    }
}