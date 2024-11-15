package com.chillizardinteractive.modelo.Decorator;

import com.chillizardinteractive.modelo.cardFactory.Card;

public class TauntDecorator extends CardDecorator {
    public TauntDecorator(Card card) {
        super(card);
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescripcion() + " (Taunt)";
    }

    @Override
    public void playEffect() {
        decoratedCard.playEffect();
        System.out.println("Este minion debe ser atacado primero.");
    }

    @Override
    public String getTipo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
    }
}