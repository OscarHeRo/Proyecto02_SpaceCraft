package com.chillizardinteractive;

public class Main {
    public static void main(String[] args) {
        String filePath = "decks.json";

        Deck gonDeck = new Deck();
        gonDeck.loadDeck(filePath, "GON");

        Deck kurapikaDeck = new Deck();
        kurapikaDeck.loadDeck(filePath, "KURAPIKA");

        Deck kuroroDeck = new Deck();
        kuroroDeck.loadDeck(filePath, "KURORO");

        System.out.println("Gon Deck:");
        gonDeck.showDeck();

        System.out.println("\nKurapika Deck:");
        kurapikaDeck.showDeck();

        System.out.println("\nKuroro Deck:");
        kuroroDeck.showDeck();
    }
}