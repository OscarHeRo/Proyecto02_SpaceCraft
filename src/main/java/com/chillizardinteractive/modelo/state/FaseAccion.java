package com.chillizardinteractive.modelo.state;

import com.chillizardinteractive.vista.GameView;

public class FaseAccion implements GameState {
    private GameView view;

    public FaseAccion(GameView view) {
        this.view = view;
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarMensaje("Fase de combate comenzando. Los jugadores pueden atacar con sus criaturas...");
        context.autorizarAtaque();
        context.setState(new TerminarTurno(view));
    }

    @Override
    public void iniciarJuego(GameContext context) {}

    @Override
    public void lanzarMoneda(GameContext context) {}

    @Override
    public void iniciarTurno(GameContext context) {}

    @Override
    public void terminarTurno(GameContext context) {}

    @Override
    public void finalizarJuego(GameContext context) {}
}