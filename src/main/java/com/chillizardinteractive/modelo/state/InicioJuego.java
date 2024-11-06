package com.chillizardinteractive.modelo.state;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.vista.GameView;

public class InicioJuego implements GameState {
    private GameView view;

    public InicioJuego(GameView view) {
        this.view = view;
    }

    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarMensaje("El juego ha comenzado. Preparando jugadores y lanzamiento de moneda...");

        // Inicializar jugadores
        Player player1 = context.getPlayer1();
        Player player2 = context.getPlayer2();

        // Cada jugador inicia con un mazo de 30 cartas
        player1.getDeck().initializeDeck("src/main/resources/decks.json");
        player2.getDeck().initializeDeck("src/main/resources/decks.json");

        // Cada jugador comienza con 30 puntos de vida y 1 espacio de nen lleno
        view.mostrarEstadoJugador(player1.getName(), player1.getHealth(), player1.getNenSpaces(), player1.getNenPoints());
        view.mostrarEstadoJugador(player2.getName(), player2.getHealth(), player2.getNenSpaces(), player2.getNenPoints());

        context.setState(new LanzamientoMoneda(view));
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        view.mostrarError("No se puede lanzar la moneda en el estado de inicio del juego.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        view.mostrarError("No se puede iniciar el turno en el estado de inicio del juego.");
    }

    @Override
    public void faseCombate(GameContext context) {
        view.mostrarError("No se puede pasar a la fase de combate en el estado de inicio del juego.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        view.mostrarError("No se puede terminar el turno en el estado de inicio del juego.");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        view.mostrarError("No se puede finalizar el juego en el estado de inicio del juego.");
    }
}