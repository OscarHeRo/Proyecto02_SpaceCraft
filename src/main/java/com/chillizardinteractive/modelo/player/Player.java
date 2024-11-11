package com.chillizardinteractive.modelo.player;

import com.chillizardinteractive.modelo.deck.Deck;

public class Player {
    private String name;
    private int health;
    private int nenSpaces;
    private int nenPoints;
    private Deck deck;
    private Mano manoJugador;

    public Player(String name) {
        this.name = name;
        this.health = 30;
        this.nenSpaces = 1;
        this.nenPoints = 1;
        this.deck = new Deck(name + "'s Deck");
        this.deck.initializeDeck("src/main/resources/decks.json"); // Inicializar el mazo desde el archivo JSON
        this.manoJugador = new Mano();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getNenSpaces() {
        return nenSpaces;
    }

    public int getNenPoints() {
        return nenPoints;
    }

    public void setNenPoints(int nenPoints) {
        this.nenPoints = nenPoints;
    }

    public Deck getDeck() {
        return deck;
    }

    public Mano getMano() {
        return manoJugador;
    }

    public void setMano(Mano manoJugador) {
        this.manoJugador = manoJugador;
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void drawCard() {
        if (deck.getCardList().isEmpty()) {
            System.out.println(name + " no tiene más cartas en el mazo. Barajando el mazo...");
            shuffleDeck();
        }
        // Lógica para robar una carta
    }
}