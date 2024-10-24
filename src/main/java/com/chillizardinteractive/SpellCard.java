package com.chillizardinteractive;

public class SpellCard implements Card {
    private final int nenCost;
    private final String description;
    private final Rarity rarity;

    public SpellCard(int nenCost, String description, Rarity rarity) {
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
        return "Hechizo: " + description;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + " se ha activado.");
    }
}
