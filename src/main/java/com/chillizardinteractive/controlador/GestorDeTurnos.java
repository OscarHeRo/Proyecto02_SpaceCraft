package com.chillizardinteractive.controlador;
import java.util.List;

import com.chillizardinteractive.modelo.player.Player;

public class GestorDeTurnos {
    private List<Player> jugadores;
    private int indiceTurnoActual;

    public GestorDeTurnos(List<Player> jugadores) {
        this.jugadores = jugadores;
        this.indiceTurnoActual = 0; // Comienza con el primer jugador
    }

    public Player obtenerJugadorEnTurno() {
        return jugadores.get(indiceTurnoActual);
    }

    public boolean esTurnoDe(Player jugador) {
        return jugador.equals(obtenerJugadorEnTurno());
    }

    public void avanzarTurno() {
        indiceTurnoActual = (indiceTurnoActual + 1) % jugadores.size();
    }
}
