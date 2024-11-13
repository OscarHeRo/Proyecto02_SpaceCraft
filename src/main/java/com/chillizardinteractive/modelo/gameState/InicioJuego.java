package com.chillizardinteractive.modelo.gameState;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.deck.Deck;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.modelo.turnState.LanzamientoMoneda;
import com.chillizardinteractive.vista.CardHtmlGenerator;
import com.chillizardinteractive.vista.GameView;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InicioJuego implements GameState {
    private GameView view;

    public InicioJuego(GameView view) {
        this.view = view;
    }

    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarMensajePublico("Iniciando el juego...");
    
        // Inicializar dos mazos diferentes para cada jugador
        Deck deck1 = new Deck("MazoJugador1");
        deck1.initializeDeck("src/main/resources/decks.json");
        Deck deck2 = new Deck("MazoJugador2");
        deck2.initializeDeck("src/main/resources/decks.json");
    
        // Asignar los mazos a los jugadores
        Player player1 = context.getPlayer1();
        Player player2 = context.getPlayer2();
        player1.setDeck(deck1);
        player2.setDeck(deck2);
    
        // Configurar jugadores con un espacio de nen y un punto de nen
        player1.incrementarNenPoints();
        player2.incrementarNenPoints();
    
        // Mostrar los mazos de los jugadores
        System.out.println(player1.getDeck().deckToString());
        System.out.println(player2.getDeck().deckToString());
    
        // Generar HTML para las cartas del mazo
        generateDeckHtml(player1.getDeck(), "output/cards/player1");
        generateDeckHtml(player2.getDeck(), "output/cards/player2");
    
        // Configurar el contexto del juego con los jugadores y el estado inicial
        context.setCurrentPlayer(player1);
        context.setState(new LanzamientoMoneda(view));
    }
    

    private void generateDeckHtml(Deck deck, String outputDir) {
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (Card card : deck.getCards()) {
            String outputPath = outputDir + "/" + card.getName().replaceAll(" ", "_") + ".html";
            CardHtmlGenerator.generateCardHtml(card, outputPath);
        }
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