package com.chillizardinteractive.modelo.player;

import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.deck.IDeck;
import com.chillizardinteractive.modelo.hunter.Hunter;

public class Player {
    private String name;
    private int vida;
    private int nenSpaces;
    private int nenPoints;
    private Mano mano;
    private IDeck deck;
    private Hunter hunter;
    private boolean listo = false; // Indica si el jugador está listo

    public Player(String name) {
        this.name = name;
        this.vida = 30;
        this.nenSpaces = 1;
        this.nenPoints = 1;
        this.mano = new Mano();
<<<<<<< HEAD
    }

    public Player(String name, Hunter hunter) {
        this.name = name;
        this.hunter = hunter;
        this.vida = 30;
        this.nenSpaces = 1;
        this.nenPoints = 1;
        this.mano = new Mano();
    }

=======
        this.deck = new Deck(name + "'s Deck");
    }

>>>>>>> parent of d243832 (terminamos flujo de juego porfin)
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
        nenPoints++;
    }

    public void recibirDanio(int danio) {
        vida -= danio;
    }

    public Mano getMano() {
        return mano;
    }

    public IDeck getDeck() {
        return deck;
    }

    public void setDeck(IDeck deck) {
        this.deck = deck;
    }

    public void robarCarta() {
        Card carta = deck.sacarCarta();
        if (carta != null) {
            mano.agregarCartasMano(carta);
        }
    }
<<<<<<< HEAD

    public void incrementarNenSpaces() {
        nenSpaces++;
    }

    public void rellenarNenPoints() {
        nenPoints = nenSpaces;
    }

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    public Hunter getHunter() {
        return hunter;
    }

    // Métodos de estado de preparación
    public boolean estaListo() {
        return listo;
    }

    public void marcarComoListo() {
        this.listo = true;
    }
}
=======
}
>>>>>>> parent of d243832 (terminamos flujo de juego porfin)
