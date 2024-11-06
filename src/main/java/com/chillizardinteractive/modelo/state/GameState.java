package com.chillizardinteractive.modelo.state;

// Interfaz GameState que define los diferentes estados del juego
public interface GameState {
    void iniciarJuego(GameContext context);
    void lanzarMoneda(GameContext context);
    void iniciarTurno(GameContext context);
    void faseCombate(GameContext context);
    void terminarTurno(GameContext context);
    void finalizarJuego(GameContext context);
}