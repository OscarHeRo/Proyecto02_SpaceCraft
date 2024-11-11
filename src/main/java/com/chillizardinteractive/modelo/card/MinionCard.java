package com.chillizardinteractive.modelo.card;

public class MinionCard extends Card {
    private final int attack;
    private final int defense;

    public MinionCard(String name, String description, int nenCost, int attack, int defense, Rarity rarity) {
        super(name, description, nenCost, rarity);
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
        return description + " (Ataque: " + attack + ", Defensa: " + defense + ")";
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + " ha sido jugado.");
    }
}