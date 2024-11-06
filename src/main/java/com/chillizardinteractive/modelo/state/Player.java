package com.chillizardinteractive.modelo.state;

import com.chillizardinteractive.modelo.deck.Deck;

public class Player {
    private String name;
    private int health;
    private int nenSpaces;
    private int nenPoints;
    private Deck deck;

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

    public void setNenPoints(int nenPoints) {
        this.nenPoints = nenPoints;
    }

    public Deck getDeck() {
        return deck;
    }
}