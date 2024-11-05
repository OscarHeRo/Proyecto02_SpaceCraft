package com.chillizardinteractive;

import com.chillizardinteractive.modelo.deck.Deck;

public class DueloDeCazadores {
    public static void main(String[] args) {
        // Crear un nuevo juego
        Juego juego = new Juego();

        // Inicializar el mazo
        Deck deck = new Deck("Deck 1");
        deck.initializeDeck("src/main/resources/decks.json");

        // Mostrar el mazo
        System.out.println("Deck:");
        deck.showDeck();

        // Iniciar el juego
        juego.iniciar();
    }
}