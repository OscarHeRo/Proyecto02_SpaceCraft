package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.player.Player;

import java.util.List;

public class GestorDeTurnos {
    private List<Player> jugadores;
    private int indiceJugadorActual;

    public GestorDeTurnos(List<Player> jugadores) {
        this.jugadores = jugadores;
        this.indiceJugadorActual = 0;
    }

    public Player obtenerJugadorEnTurno() {
        return jugadores.get(indiceJugadorActual);
    }

    public void avanzarTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
    }

    public boolean esTurnoDe(Player jugador) {
        return jugadores.get(indiceJugadorActual).equals(jugador);
    }
}