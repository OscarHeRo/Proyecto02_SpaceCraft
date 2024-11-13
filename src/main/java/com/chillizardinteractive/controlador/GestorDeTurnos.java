package com.chillizardinteractive.controlador;
import java.util.List;
import com.chillizardinteractive.modelo.player.Player;

public class GestorDeTurnos {
    private List<Player> jugadores;
    private int indiceTurnoActual;

    public GestorDeTurnos(List<Player> jugadores) {
        this.jugadores = jugadores;
        this.indiceTurnoActual = 0; // Start with the first player
    }

    public Player obtenerJugadorEnTurno() {
        return jugadores.get(indiceTurnoActual);
    }

    public boolean esTurnoDe(Player jugador) {
        if (jugador != null) {
            return jugador.equals(obtenerJugadorEnTurno());
        } else {
            System.out.println("Error: Player es nulo o el turno es incorrecto.");
            return false;
        }
    }
    
    public void avanzarTurno() {
        indiceTurnoActual = (indiceTurnoActual + 1) % jugadores.size();
    }
}
