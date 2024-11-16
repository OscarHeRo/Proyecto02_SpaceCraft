package com.chillizardinteractive.modelo.cardFactory;

import org.json.simple.JSONObject;
import com.chillizardinteractive.modelo.Rareza;

public class WeaponCard extends Card {
    private int attack;
    private int durability;

    public WeaponCard(String nombre, String descripcion, int costoMana, int attack, int durability, Rareza rareza) {
        super(nombre, descripcion, costoMana, rareza);
        this.attack = attack;
        this.durability = durability;
    }

    public static WeaponCard fromJson(JSONObject json) {
        return new WeaponCard(
            (String) json.get("name"),
            (String) json.getOrDefault("description", ""),
            ((Long) json.get("cost")).intValue(),
            ((Long) json.get("attack")).intValue(),
            ((Long) json.get("durability")).intValue(),
            Rareza.valueOf(((String) json.get("rarity")).toUpperCase())
        );
    }

    @Override
    public String getTipo() {
        return "Arma";
    }

    public int getAttack() {
        return attack;
    }

    public int getDurability() {
        return durability;
    }

    @Override
    public String toString() {
        return super.toString() + " (Ataque: " + attack + ", Durabilidad: " + durability + ")";
    }

}