package com.chillizardinteractive.modelo.cardFactory;
import org.json.simple.JSONObject;

import com.chillizardinteractive.modelo.Rareza;

public class SpellCard extends Card {

    public SpellCard(String name, int cost, Rareza rareza, String description) {
        super( name,  description,  cost,  rareza);
    }

    public static SpellCard fromJson(JSONObject json) {
        return new SpellCard(
            (String) json.get("name"),
            ((Long) json.get("cost")).intValue(),
            Rareza.valueOf(((String) json.get("rarity")).toUpperCase()),
            (String) json.getOrDefault("description", "")
        );
    }

    // Método que define el comportamiento de "irse al cementerio" después de usar
    public void usarHechizo() {
        System.out.println("Hechizo usado y enviado al cementerio: " + this.name);
    }

    @Override
    public String getTipo() {
        return "Hechizo";
    }
}
