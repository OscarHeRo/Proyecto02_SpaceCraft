package com.chillizardinteractive;

public class WeaponCard implements Card {
    private final int attack;
    private final int durability;
    private final int nenCost;
    private final String description;
    private final Rarity rarity;

    public WeaponCard(int attack, int durability, int nenCost, String description, Rarity rarity) {
        this.attack = attack;
        this.durability = durability;
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
        return "Arma: " + description + " (Ataque: " + attack + ", Durabilidad: " + durability + ")";
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + " ha sido equipada.");
    }
}
