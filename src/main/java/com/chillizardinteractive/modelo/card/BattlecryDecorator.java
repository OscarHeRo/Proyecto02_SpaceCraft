package com.chillizardinteractive.modelo.card;

public class BattlecryDecorator extends CardDecorator {
    private final String battlecryEffect;

    public BattlecryDecorator(Card card, String effect) {
        super(card);
        this.battlecryEffect = effect;
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescription() + " (Battlecry)";
    }

    @Override
    public void playEffect() {
        System.out.println("Grito de Batalla: " + battlecryEffect);
        decoratedCard.playEffect();
    }
}