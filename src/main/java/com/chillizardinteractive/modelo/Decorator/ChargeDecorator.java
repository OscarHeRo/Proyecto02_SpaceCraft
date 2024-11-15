package com.chillizardinteractive.modelo.Decorator;

public class ChargeDecorator extends CardDecorator {
    public ChargeDecorator(Card card) {
        super(card);
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescripcion() + " (Charge)";
    }

    @Override
    public void playEffect() {
        decoratedCard.playEffect();
        System.out.println("Este minion puede atacar inmediatamente.");
    }

    @Override
    public String getTipo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
    }
}