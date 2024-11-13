package com.chillizardinteractive.modelo.turnState;

import com.chillizardinteractive.modelo.gameState.GameState;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.vista.GameView;

public class TerminarTurno implements GameState {
    private GameView view;

    public TerminarTurno(GameView view) {
        this.view = view;
    }

    @Override
    public void terminarTurno(GameContext context) {
        if (context == null) {
            view.mostrarError("Error: Contexto de juego no disponible.");
            return;
        }
        view.mostrarMensaje("Turno terminado. Pasando al siguiente jugador...");
        context.switchPlayer();
        context.setState(new InicioTurno(view));
        context.iniciarTurno();
    }

    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarError("No se puede iniciar el juego en el estado de terminar turno.");
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        view.mostrarError("No se puede lanzar la moneda en el estado de terminar turno.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        view.mostrarError("No se puede iniciar el turno en el estado de terminar turno.");
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarError("No se puede entrar en fase de combate en el estado de terminar turno.");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        view.mostrarError("No se puede finalizar el juego en el estado de terminar turno.");
    }
}