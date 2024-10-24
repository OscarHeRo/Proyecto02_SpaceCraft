package com.chillizardinteractive;

public class ChargeDecorator implements Minion {
    private final Minion decoratedMinion;

    public ChargeDecorator(Minion minion) {
        this.decoratedMinion = minion;
    }

    @Override
    public int getNenCost() {
        return decoratedMinion.getNenCost();
    }

    @Override
    public String getDescription() {
        return decoratedMinion.getDescription() + " (Charge)";
    }

    @Override
    public Rarity getRarity() {
        return decoratedMinion.getRarity();
    }

    @Override
    public int getAttack() {
        return decoratedMinion.getAttack();
    }

    @Override
    public int getDefense() {
        return decoratedMinion.getDefense();
    }

    @Override
    public void playEffect() {
        decoratedMinion.playEffect();
        System.out.println("Este minion puede atacar inmediatamente.");
    }
}
