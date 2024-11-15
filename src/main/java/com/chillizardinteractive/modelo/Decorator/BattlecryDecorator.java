package com.chillizardinteractive.modelo.Decorator;

import com.chillizardinteractive.modelo.cardFactory.Card;

public class BattlecryDecorator extends CardDecorator {
    private final String battlecryEffect;

    public BattlecryDecorator(Card card, String effect) {
        super(card);
        this.battlecryEffect = effect;
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescripcion() + " (Battlecry)";
    }

    @Override
    public void playEffect() {
        System.out.println("Grito de Batalla: " + battlecryEffect);
        decoratedCard.playEffect();
    }

    @Override
    public String getTipo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
    }
}