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
        view.mostrarMensaje("Turno terminado. Pasando al siguiente jugador...");
        context.setState(new InicioTurno(view));
    }

    @Override
    public void iniciarJuego(GameContext context) {}

    @Override
    public void lanzarMoneda(GameContext context) {}

    @Override
    public void iniciarTurno(GameContext context) {}

    @Override
    public void faseCombate(GameContext context) {}

    @Override
    public void finalizarJuego(GameContext context) {}
}