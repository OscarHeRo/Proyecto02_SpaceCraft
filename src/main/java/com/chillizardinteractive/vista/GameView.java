package com.chillizardinteractive.vista;
public class GameView {

    // Mostrar un mensaje que todos los jugadores deberían ver
    public void mostrarMensajePublico(String mensaje) {
        System.out.println("[Mensaje Público] " + mensaje);
    }

    // Mostrar un mensaje específico para un jugador
    public void mostrarMensajePrivado(String nombreJugador, String mensaje) {
        System.out.println("[Mensaje Privado para " + nombreJugador + "]\n " + mensaje);
    }

    public void mostrarEstadoJugador(String nombre, int vida, int nenSpaces, int nenPoints) {
        System.out.println(nombre + ": Vida = " + vida + ", Espacios de Nen = " + nenSpaces + ", Puntos de Nen = " + nenPoints);
    }

    public void mostrarCartaRobada(String nombreJugador, String descripcionCarta) {
        mostrarMensajePrivado(nombreJugador, "Has robado la carta: " + descripcionCarta);
    }

    public void mostrarError(String mensaje) {
        System.out.println("[Error] " + mensaje);
    }
}
