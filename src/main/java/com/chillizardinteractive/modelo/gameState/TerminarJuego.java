package com.chillizardinteractive.modelo.gameState;

import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

public class TerminarJuego implements GameState {
    private final GameView view;

    public TerminarJuego(GameView view) {
        this.view = view;
    }

    @Override
    public void finalizarJuego(GameContext context) {
        view.mostrarMensajePublico("El juego ha terminado.");
        mostrarResumenJugador(context.getPlayer1());
        mostrarResumenJugador(context.getPlayer2());
    }

    private void mostrarResumenJugador(Player player) {
        view.mostrarMensajePrivado(player.getName(), "Resumen para " + player.getName() + ":");
        view.mostrarMensajePrivado(player.getName(), " - Salud restante: " + player.getHealth());
        view.mostrarMensajePrivado(player.getName(), " - Puntos Nen restantes: " + player.getNenPoints());
        // Aquí se pueden agregar más detalles si es necesario
    }

    // Métodos no válidos en el estado final
    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarError("El juego ya ha terminado. No se puede iniciar de nuevo.");
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        view.mostrarError("El juego ya ha terminado. No se puede lanzar la moneda.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        view.mostrarError("El juego ya ha terminado. No se puede iniciar el turno.");
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarError("El juego ya ha terminado. No se puede pasar a la fase de combate.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        view.mostrarError("El juego ya ha terminado. No se puede terminar el turno.");
    }


}