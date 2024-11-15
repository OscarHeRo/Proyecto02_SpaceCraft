package com.chillizardinteractive.modelo.cardFactory;
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of dd32a03 (Merge branch 'nueva_rama_estructura_mvc' of https://github.com/OscarHeRo/Proyecto02_SpaceCraft into nueva_rama_estructura_mvc)
    @Override
    public String getTipo() {
<<<<<<< Updated upstream
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
=======
        return "Minion";
>>>>>>> Stashed changes
    }

<<<<<<< HEAD
=======
>>>>>>> parent of 15f8f8f (Más errores arreglados)
=======
>>>>>>> parent of 15f8f8f (Más errores arreglados)
=======
>>>>>>> parent of 15f8f8f (Más errores arreglados)
=======
>>>>>>> parent of dd32a03 (Merge branch 'nueva_rama_estructura_mvc' of https://github.com/OscarHeRo/Proyecto02_SpaceCraft into nueva_rama_estructura_mvc)
    // Métodos específicos para MinionCard
}
