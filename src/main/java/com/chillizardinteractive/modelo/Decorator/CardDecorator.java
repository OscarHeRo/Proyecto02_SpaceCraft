package com.chillizardinteractive.modelo.Decorator;

import com.chillizardinteractive.modelo.cardFactory.Card;

public abstract class CardDecorator extends Card {
    protected final Card decoratedCard;
    public CardDecorator(Card card) {
        super(card.getNombre(), card.getDescripcion(), card.getNenCost(), card.getRarity());
        this.decoratedCard = card;
    }

    @Override
    public int getNenCost() {
        return decoratedCard.getNenCost();
    }

    @Override
    public String getName() {
        return decoratedCard.getNombre();
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescripcion();
    }

    @Override
    public Rarity getRarity() {
        return decoratedCard.getRarity();
    }

    @Override
    public void playEffect() {
        decoratedCard.playEffect();
    }
}