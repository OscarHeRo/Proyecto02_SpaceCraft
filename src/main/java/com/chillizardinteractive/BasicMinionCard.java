package com.chillizardinteractive;

public class BasicMinionCard implements Minion {
    private final int attack;
    private final int defense;
    private final int nenCost;
    private final String description;
    private final Rarity rarity;

    public BasicMinionCard(int attack, int defense, int nenCost, String description, Rarity rarity) {
        this.attack = attack;
        this.defense = defense;
        this.nenCost = nenCost;
        this.description = description;
        this.rarity = rarity;
    }

    @Override
    public int getNenCost() {
        return nenCost;
    }

    @Override
    public String getDescription() {
        return description + " (Ataque: " + attack + ", Defensa: " + defense + ")";
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + " ha sido jugado.");
    }
}
