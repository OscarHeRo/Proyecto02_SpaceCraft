package com.chillizardinteractive;

public class BasicMinionCard extends CardDecorator {
    private final int attack;
    private final int defense;

    public BasicMinionCard(Card card, int attack, int defense) {
        super(card);
        this.attack = attack;
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescription() + " (Ataque: " + attack + ", Defensa: " + defense + ")";
    }

    @Override
    public void playEffect() {
        decoratedCard.playEffect();
        System.out.println(getDescription() + " ha sido jugado.");
    }
}