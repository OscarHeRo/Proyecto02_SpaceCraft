package com.chillizardinteractive.modelo.state;

public class FaseAccion implements GameState {
    @Override
    public void faseCombate(GameContext context) {
        System.out.println("Fase de combate comenzando. Los jugadores pueden atacar con sus criaturas...");
        context.setState(new TerminarTurno());
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