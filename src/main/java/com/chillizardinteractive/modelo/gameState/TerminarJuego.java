package com.chillizardinteractive.modelo.gameState;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

public class TerminarJuego implements GameState {
    private final GameView view;

    public TerminarJuego(GameView view) {
        this.view = view;
    }

    @Override
    public void finalizarJuego(GameContext context) {
        Player player1 = context.getPlayer1();
        Player player2 = context.getPlayer2();

        // Mostrar un mensaje de fin de juego
        view.mostrarMensaje("El juego ha terminado. Mostrando resumen de la partida...");

        // Mostrar resumen de cada jugador
        mostrarResumenJugador(player1);
        mostrarResumenJugador(player2);

        // Mensaje final de despedida
        view.mostrarMensaje("Gracias por jugar. ¡Esperamos que hayas disfrutado!");
    }

    private void mostrarResumenJugador(Player player) {
        view.mostrarMensaje("Resumen para " + player.getName() + ":");
        view.mostrarMensaje(" - Salud restante: " + player.getHealth());
        view.mostrarMensaje(" - Puntos Nen restantes: " + player.getNenPoints());
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
        view.mostrarError("El juego ya ha terminado. No se puede iniciar un turno.");
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarError("El juego ya ha terminado. No se puede entrar en fase de combate.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        view.mostrarError("El juego ya ha terminado. No se puede terminar el turno.");
    }
}
