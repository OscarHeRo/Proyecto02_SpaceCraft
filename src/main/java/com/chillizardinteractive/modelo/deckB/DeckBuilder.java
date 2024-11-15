package com.chillizardinteractive.modelo.builder;

import com.chillizardinteractive.modelo.factory.Card;
import com.chillizardinteractive.modelo.proxyDeck.deckProxy;;

import java.util.ArrayList;
import java.util.List;

public class DeckBuilder {
    private List<Card> cards;

    public DeckBuilder() {
        this.cards = new ArrayList<>();
    }

    public DeckBuilder addCard(Card card) {
        this.cards.add(card);
        return this;
    }

    public DeckBuilder reset() {
        this.cards.clear();
        return this;
    }

    public Deck build() {
        Deck deck = new Deck("Custom Deck");
        for (Card card : cards) {
            deck.addCard(card);
        }
        return deck;
    }
}
