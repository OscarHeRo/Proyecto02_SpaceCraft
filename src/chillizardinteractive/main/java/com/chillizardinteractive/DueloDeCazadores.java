package src.chillizardinteractive.main.java.com.chillizardinteractive;

import src.chillizardinteractive.Modelo.deck.Deck;

public class DueloDeCazadores {
    public static void main(String[] args) {
        // Crear un nuevo juego
        Juego juego = new Juego();

        // Inicializar el mazo
        Deck deck = new Deck();
        deck.initializeDeck("src/main/resources/decks.json");

        // Mostrar el mazo
        System.out.println("Deck:");
        deck.showDeck();

        // Iniciar el juego
        juego.iniciar();
    }
}