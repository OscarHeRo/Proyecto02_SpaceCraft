package com.chillizardinteractive.modelo.deck;

import com.chillizardinteractive.modelo.card.Card;

import java.util.List;

public interface IDeck {
    List<Card> getCards();
    Card sacarCarta();
    String deckToString();
}
