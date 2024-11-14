package com.chillizardinteractive.modelo.deck;

import com.chillizardinteractive.modelo.card.Card;

import java.util.List;

public class DeckProxy implements IDeck {
    private final Deck deck;

    public DeckProxy(Deck deck) {
        this.deck = deck;
    }

    @Override
    public List<Card> getCards() {
        return deck.getCards();
    }

    @Override
    public Card sacarCarta() {
        return deck.sacarCarta();
    }

    @Override
    public String deckToString() {
        return deck.deckToString();
    }
}
