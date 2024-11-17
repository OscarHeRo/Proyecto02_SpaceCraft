package com.chillizardinteractive.vista;

import com.chillizardinteractive.servidor.ServidorJuego;

public class GameView {
    private ServidorJuego servidorJuego;

    public GameView(ServidorJuego servidorJuego) {
        this.servidorJuego = servidorJuego;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarEstadoJugador(String nombre, int vida, int nenSpaces, int nenPoints) {
        servidorJuego.notificarMensajePublico(nombre + ": Vida = " + vida + ", Espacios de Nen = " + nenSpaces + ", Puntos de Nen = " + nenPoints);
    }

    public void mostrarCartaRobada(String nombreJugador, String descripcionCarta) {
        servidorJuego.notificarMensajePrivado(nombreJugador, nombreJugador + " ha robado la carta: " + descripcionCarta);
    }

    public void mostrarError(String mensaje) {
        servidorJuego.notificarMensajePublico("Error: " + mensaje);
    }

    public void mostrarMensajePrivado(String nombreJugador, String mensaje) {
        servidorJuego.notificarMensajePrivado(nombreJugador, mensaje);
    }

    public void mostrarMensajePublico(String mensaje) {
        servidorJuego.notificarMensajePublico(mensaje);
    }
}