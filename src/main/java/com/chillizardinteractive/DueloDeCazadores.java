package com.chillizardinteractive;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.modelo.deck.Deck;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.gameState.InicioJuego;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.CardHtmlGenerator;
import com.chillizardinteractive.vista.GameView;
import com.chillizardinteractive.modelo.card.Card;

import java.io.File;
import java.io.IOException;

public class DueloDeCazadores {
    public static void main(String[] args) {
        Player player1 = new Player("Jugador 1");
        Player player2 = new Player("Jugador 2");
        GameView view = new GameView();
        GameContext context = new GameContext(player1, player2, new InicioJuego(view));
        GameController controller = new GameController(context, view);
        Deck deck = new Deck("Deck de Prueba");

        // Iniciar el juego
        controller.iniciarJuego();

        // Lanzar la moneda y determinar el primer jugador
        controller.lanzarMoneda();

        // Generar HTML para las cartas del mazo
        generateDeckHtml(deck, "output/cards");
    }

    private static void generateDeckHtml(Deck deck, String outputDir) {
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (Card card : deck.getCards()) {
            String outputPath = outputDir + "/" + card.getName().replaceAll(" ", "_") + ".html";
            CardHtmlGenerator.generateCardHtml(card, outputPath);
        }
    }
}