package com.chillizardinteractive.modelo.card;

public class WeaponCard extends Card {
    private final int attack;

    public WeaponCard(String name, String description, int nenCost, int attack, Rarity rarity) {
        super(name, description, nenCost, rarity);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String getDescription() {
        return description + " (Ataque: " + attack + ")";
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + " ha sido jugado.");
    }
}