package com.chillizardinteractive.modelo.state;

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

    public Mano getMano(){
        return manoJugador;
    }

    public void setNenPoints(int nenPoints) {
        this.nenPoints = nenPoints;
    }

    public void setMano(Mano manoJugador){
        this.manoJugador = manoJugador;
    }

    public Deck getDeck() {
        return deck;
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