package com.chillizardinteractive.vista;

public class GameView {
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarEstadoJugador(String nombre, int vida, int nenSpaces, int nenPoints) {
        System.out.println(nombre + ": Vida = " + vida + ", Espacios de Nen = " + nenSpaces + ", Puntos de Nen = " + nenPoints);
    }

    public void mostrarCartaRobada(String nombreJugador, String descripcionCarta) {
        System.out.println(nombreJugador + " ha robado la carta: " + descripcionCarta);
    }

    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }
}