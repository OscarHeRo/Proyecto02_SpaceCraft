package com.chillizardinteractive;

public abstract class Card {
    protected String description;
    protected int nenCost;
    protected Rarity rarity;

    public Card(String description, int nenCost, Rarity rarity) {
        this.description = description;
        this.nenCost = nenCost;
        this.rarity = rarity;
    }

    public int getNenCost() {
        return nenCost;
    }

    public String getDescription() {
        return description;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public abstract void playEffect();
}
