package com.chillizardinteractive.modelo.player;

import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.deck.Deck;

public class Player {
    private String name;
    private int vida;
    private int nenSpaces;
    private int nenPoints;
    private Mano mano;
    private Deck deck;

    public Player(String name, Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("El mazo del jugador no puede ser nulo.");
        }
        this.name = name;
        this.vida = 30;
        this.nenSpaces = 1;
        this.nenPoints = 1;
        this.mano = new Mano();
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return vida;
    }

    public int getNenSpaces() {
        return nenSpaces;
    }

    public int getNenPoints() {
        return nenPoints;
    }

    public void incrementarNenPoints() {
        this.nenPoints++;
    }

    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida < 0) {
            this.vida = 0; // Prevent vida from going negative
        }
    }

    public Mano getMano() {
        return mano;
    }

    public Deck getDeck() {
        return deck;
    }

    public void robarCarta() {
        if (deck == null) {
            throw new IllegalStateException("El jugador no tiene mazo para robar cartas.");
        }
        Card carta = deck.sacarCarta();
        if (carta != null) {
            mano.agregarCartasMano(carta);
        } else {
            System.out.println("El mazo está vacío; no se puede robar carta.");
        }
    }

    public void incrementarNenSpaces() {
        this.nenSpaces++;
    }

    public void rellenarNenPoints() {
        this.nenPoints = nenSpaces;
    }
}
