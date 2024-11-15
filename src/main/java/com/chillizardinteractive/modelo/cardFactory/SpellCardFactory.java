package com.chillizardinteractive.modelo.cardFactory;

import com.chillizardinteractive.modelo.Rareza;

public class SpellCardFactory {

    public static SpellCard crearCarta(String nombre, String descripcion, int costoMana, Rareza rareza) {
        return new SpellCard(nombre, costoMana, rareza, descripcion);
    }
}