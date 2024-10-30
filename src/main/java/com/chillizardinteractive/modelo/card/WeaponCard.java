package com.chillizardinteractive.modelo.card;

public class WeaponCard extends Card {
    private final int attack;
    private final int durability;

    public WeaponCard(int attack, int durability, int nenCost, String description, Rarity rarity) {
        super(description, nenCost, rarity);
        this.attack = attack;
        this.durability = durability;
    }

    public int getAttack() {
        return attack;
    }

    public int getDurability() {
        return durability;
    }

    @Override
    public String getDescription() {
        return description + " (Ataque: " + attack + ", Durabilidad: " + durability + ")";
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + " ha sido jugado.");
    }
}