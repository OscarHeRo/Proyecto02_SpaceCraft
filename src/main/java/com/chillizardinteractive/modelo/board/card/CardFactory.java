package com.chillizardinteractive.modelo.card;

public class CardFactory {

    public static Card crearCarta(String tipo, String nombre, String descripcion, int costoMana, int ataque, int vida, Rarity rarity) {
        switch (tipo) {
            case "Arma":
                return new WeaponCard(nombre, descripcion, costoMana, ataque, rarity);
            case "Hechizo":
                return new SpellCard(nombre, descripcion, costoMana, rarity);
            case "Minion":
                return new MinionCard(nombre, descripcion, costoMana, ataque, vida, rarity);
            default:
                throw new IllegalArgumentException("Tipo de carta desconocido: " + tipo);
        }
    }
}