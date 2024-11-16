package com.chillizardinteractive.modelo;

import com.chillizardinteractive.modelo.cardFactory.Card;
import com.chillizardinteractive.modelo.deckProxy.Deck;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int vida;
    private int nenSpaces;
    private int nenPoints;
    private Mano mano;
    private Deck deck;

    public Player(String name, Deck deck) {
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
    }

    public Mano getMano() {
        return mano;
    }

    public Deck getDeck() {
        return deck;
    }

    public void robarCarta() {
        Card carta = deck.sacarCarta();
        if (carta != null) {
            mano.agregarCartasMano(carta);
        }
    }


    public void incrementarNenSpaces() {
        this.nenSpaces++;
    }


    public void rellenarNenPoints() {
        this.nenPoints = nenSpaces;
    }
}