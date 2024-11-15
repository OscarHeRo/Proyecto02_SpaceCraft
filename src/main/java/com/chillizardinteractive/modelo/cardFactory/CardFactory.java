
package com.chillizardinteractive.modelo.cardFactory;

import com.chillizardinteractive.modelo.Rareza;

public class CardFactory {

    public static Card crearCarta(String tipo, String name, String description, int cost, int ataque, int vida, Rareza rareza) {
        switch (tipo) {
            case "Arma":
                return new WeaponCard(name, description, costoMana, ataque, rareza); 
            case "Hechizo":
                return new SpellCard(nombre, descripcion, costoMana, rareza); 
            case "Minion":
                return new MinionCard(name,  cost,  rareza,  description,  ataque,  defense);
            default:
                throw new IllegalArgumentException("Tipo de carta desconocido: " + tipo);
        }
    }
}