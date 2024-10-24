package com.chillizardinteractive;

public class BattlecryDecorator implements Minion {
    private final Minion decoratedMinion;
    private final String battlecryEffect;

    public BattlecryDecorator(Minion minion, String effect) {
        this.decoratedMinion = minion;
        this.battlecryEffect = effect;
    }

    @Override
    public int getNenCost() {
        return decoratedMinion.getNenCost();
    }

    @Override
    public String getDescription() {
        return decoratedMinion.getDescription() + " (Battlecry)";
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
        System.out.println("Grito de Batalla: " + battlecryEffect);
        decoratedMinion.playEffect();
    }
}
