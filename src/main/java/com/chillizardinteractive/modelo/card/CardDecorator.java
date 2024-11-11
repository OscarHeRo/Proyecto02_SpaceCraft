package com.chillizardinteractive.modelo.card;

public abstract class CardDecorator extends Card {
    protected final Card decoratedCard;

    public CardDecorator(Card card) {
        super(card.getName(), card.getDescription(), card.getNenCost(), card.getRarity());
        this.decoratedCard = card;
    }

    @Override
    public int getNenCost() {
        return decoratedCard.getNenCost();
    }

    @Override
    public String getName() {
        return decoratedCard.getName();
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescription();
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