
package com.chillizardinteractive.modelo.cardFactory;

import com.chillizardinteractive.modelo.Rareza;

public class CardFactory {

    public static Card crearCarta(String tipo, String nombre, String descripcion, int costoMana, int ataque, int vida, Rareza rareza) {
        switch (tipo) {
            case "Arma":
                return new WeaponCard(nombre, descripcion, costoMana, ataque, rareza); 
            case "Hechizo":
                return new SpellCard(nombre, descripcion, costoMana, rareza); 
            case "Minion":
                return new MinionCard(nombre, descripcion, costoMana, ataque, rareza);
            default:
                throw new IllegalArgumentException("Tipo de carta desconocido: " + tipo);
        }
    }
}