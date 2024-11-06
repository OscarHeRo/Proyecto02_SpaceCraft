package com.chillizardinteractive.modelo.juegos;

public interface GameState {
    public void iniciarjuego();
    public void iniciarTurno();
    public void faseCombate();
    public void terminarTurno();
    public void finalizarJuego();
}
