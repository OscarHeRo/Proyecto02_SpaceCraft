package com.chillizardinteractive.modelo.card;

public class MinionCard extends Card {
    private final int attack;
    private int defense;

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

    public void recibirDanio(int danio) {
        this.defense -= danio;
        System.out.println(getName() + " ha recibido " + danio + " puntos de daño. Defensa restante: " + this.defense);
    }

    public boolean estaMuerto() {
        return defense <= 0;
    }

    public boolean hasTaunt() {
        return description.contains("Taunt");
    }

    @Override
    public String getDescription() {
        return description + " (Ataque: " + attack + ", Defensa: " + defense + ")";
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + " ha sido jugado.");
    }

  
    public void receiveDamage(int damage) {
        recibirDanio(damage);
    }

 
    public boolean isDead() {
        return estaMuerto();
    }
}