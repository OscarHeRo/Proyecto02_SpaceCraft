package com.chillizardinteractive.modelo.state;

public class TerminarTurno implements GameState {
    @Override
    public void terminarTurno(GameContext context) {
        System.out.println("Turno terminado. Pasando al siguiente jugador...");
        context.setState(new InicioTurno());
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