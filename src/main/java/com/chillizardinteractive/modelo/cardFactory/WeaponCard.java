package com.chillizardinteractive.modelo.cardFactory;
import org.json.simple.JSONObject;

import com.chillizardinteractive.modelo.Rareza;

public class WeaponCard extends Card {
    private int attack;
    private int durability;

<<<<<<< Updated upstream
    public WeaponCard(String name, int cost, Rareza rarity, String description, int attack, int durability) {
        super(name, cost, rarity, description);
=======
    public WeaponCard(String name, int cost, Rareza rareza, String description, int attack, int durability) {
        super( name,  description,  cost,  rareza);
>>>>>>> Stashed changes
        this.attack = attack;
        this.durability = durability;
    }

    public static WeaponCard fromJson(JSONObject json) {
        return new WeaponCard(
            (String) json.get("name"),
            ((Long) json.get("cost")).intValue(),
            Rareza.valueOf(((String) json.get("rarity")).toUpperCase()),
            (String) json.getOrDefault("description", ""),
            ((Long) json.get("attack")).intValue(),
            ((Long) json.get("durability")).intValue()
        );
    }

    // Métodos específicos para WeaponCard

    public int getAttack() {
        return attack;
    }

    @Override
    public String getTipo() {
        return "Arma";
    }
}
