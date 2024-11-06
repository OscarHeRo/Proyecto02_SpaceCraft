package com.chillizardinteractive.modelo.state;

import com.chillizardinteractive.modelo.deck.Deck;

public class InicioJuego implements GameState {
    @Override
    public void iniciarJuego(GameContext context) {
        System.out.println("El juego ha comenzado. Preparando jugadores y lanzamiento de moneda...");

        // Jugador 1 y jugador 2 inicializados en contexto
        Player player1 = context.getPlayer1();
        Player player2 = context.getPlayer2();

        // Cada jugador inicia con un mazo de 30 cartas
        player1.getDeck().initializeDeck("src/main/resources/decks.json");
        player2.getDeck().initializeDeck("src/main/resources/decks.json");

        // Cada jugador comienza con 30 puntos de vida y 1 espacio de nen lleno
        System.out.println("Jugador 1: Vida = " + player1.getHealth() + ", Espacios de Nen = " + player1.getNenSpaces() + ", Puntos de Nen = " + player1.getNenPoints());
        System.out.println("Jugador 2: Vida = " + player2.getHealth() + ", Espacios de Nen = " + player2.getNenSpaces() + ", Puntos de Nen = " + player2.getNenPoints());

        context.setState(new LanzamientoMoneda());
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        System.out.println("Error: No se puede lanzar la moneda en el estado de inicio del juego.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        System.out.println("Error: No se puede iniciar el turno en el estado de inicio del juego.");
    }

    @Override
    public void faseCombate(GameContext context) {
        System.out.println("Error: No se puede pasar a la fase de combate en el estado de inicio del juego.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        System.out.println("Error: No se puede terminar el turno en el estado de inicio del juego.");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        System.out.println("Error: No se puede finalizar el juego en el estado de inicio del juego.");
    }
}