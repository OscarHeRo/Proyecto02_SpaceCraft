package com.chillizardinteractive.modelo.deckB;

import java.util.ArrayList;
import java.util.List;

import com.chillizardinteractive.modelo.cardFactory.Card;
import com.chillizardinteractive.modelo.deckProxy.Deck;

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
