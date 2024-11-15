package com.chillizardinteractive.modelo.factory;
import org.json.simple.JSONObject;

import com.chillizardinteractive.modelo.Rareza;

public class MinionCard extends Card {
    private int attack;
    private int defense;
    private Rareza rareza;

    public MinionCard(String name, int cost, Rareza rareza, String description, int attack, int defense) {
        super(name, cost, rareza, description);
        this.attack = attack;
        this.defense = defense;
        this.rareza = rareza;
    }

    public static MinionCard fromJson(JSONObject json) {
        return new MinionCard(
            (String) json.get("name"),
            ((Long) json.get("cost")).intValue(),
            Rareza.valueOf(((String) json.get("rareza")).toUpperCase()),
            (String) json.getOrDefault("description", ""),
            ((Long) json.get("attack")).intValue(),
            ((Long) json.get("defense")).intValue()
        );
    }

    // Métodos específicos para MinionCard
}
