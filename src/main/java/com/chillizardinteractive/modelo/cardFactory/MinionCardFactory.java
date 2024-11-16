package com.chillizardinteractive.modelo.cardFactory;

import com.chillizardinteractive.modelo.Rareza;

public class MinionCardFactory {

    public static MinionCard crearCarta(String nombre, String descripcion, int costoMana, int ataque, int vida, Rareza rareza) {
        return new MinionCard(nombre, costoMana, rareza, descripcion, ataque, vida);
    }
}