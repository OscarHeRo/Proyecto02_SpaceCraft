package com.chillizardinteractive.modelo.card;

public class SpellCard extends Card {

    public SpellCard(String name, String description, int nenCost, Rarity rarity) {
        super(name, description, nenCost, rarity);
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + " ha sido jugado.");
    }
}