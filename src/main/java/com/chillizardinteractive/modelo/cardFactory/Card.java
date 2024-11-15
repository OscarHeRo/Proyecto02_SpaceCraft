package com.chillizardinteractive.modelo.cardFactory;

import com.chillizardinteractive.modelo.Rareza;

public abstract class Card {
    protected String name;
    protected String descripcion;
    protected int costoMana;
    protected Rareza rareza;

    public Card(String nombre, String descripcion, int costoMana, Rareza rareza) {
        this.name = nombre;
        this.descripcion = descripcion;
        this.costoMana = costoMana;
        this.rareza = rareza;
    }

    // Métodos comunes a todas las cartas
    public String getNombre() { return name; }
    public int getCostoMana() { return costoMana; }
    public String getDescripcion() { return descripcion; }
    public Rareza getRareza() { return rareza; }

    public abstract String getTipo();


    @Override
    public String toString() {
        return getTipo() + " - " + name + ": " + descripcion + " (Costo: " + costoMana + ", Rareza: " + rareza + ")";
    }
}
