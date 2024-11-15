package com.chillizardinteractive.modelo.factory;
import org.json.simple.JSONObject;

import com.chillizardinteractive.modelo.Rareza;

public class MinionCard extends Card {
    private int attack;
    private int defense;
    private Rareza rareza;
    private String descripcion;

    public MinionCard(String name, int cost, Rareza rareza, String description, int attack, int defense) {
        super( name,  description,  cost,  rareza);
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

<<<<<<< HEAD
<<<<<<< HEAD
    @Override
    public String getTipo() {
<<<<<<< Updated upstream
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
=======
        return "Minion";
>>>>>>> Stashed changes
    }

=======
>>>>>>> parent of 15f8f8f (Más errores arreglados)
=======
>>>>>>> parent of 15f8f8f (Más errores arreglados)
    // Métodos específicos para MinionCard
}
