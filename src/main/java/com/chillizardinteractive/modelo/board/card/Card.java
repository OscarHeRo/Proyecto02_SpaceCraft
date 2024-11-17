package com.chillizardinteractive.modelo.card;

public abstract class Card {
    protected String name;
    protected String description;
    protected int nenCost;
    protected Rarity rarity;

    public Card(String name, String description, int nenCost, Rarity rarity) {
        this.name = name;
        this.description = description;
        this.nenCost = nenCost;
        this.rarity = rarity;
    }

    public int getNenCost() {
        return nenCost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public abstract void playEffect();
}