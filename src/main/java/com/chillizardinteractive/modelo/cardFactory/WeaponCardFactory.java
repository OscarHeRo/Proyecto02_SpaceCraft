package com.chillizardinteractive.modelo.factory;

import com.chillizardinteractive.modelo.Rareza;

public class WeaponCardFactory {

    public static WeaponCard crearCarta(String nombre, String descripcion, int costoMana, int ataque, int durabilidad, Rareza rareza) {
        return new WeaponCard(nombre, costoMana, rareza, descripcion, ataque, durabilidad);
    }
}