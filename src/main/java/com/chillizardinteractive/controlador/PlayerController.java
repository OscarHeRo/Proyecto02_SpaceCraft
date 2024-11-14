package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.hunter.Hunter;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;
import com.chillizardinteractive.controlador.GameController;

import java.io.PrintWriter;
import java.util.List;

public class PlayerController {
    private List<Player> jugadores;
    private GameController gameController;
    private GameView view;
    private int jugadoresListos = 0;

    // Constructor actualizado
    public PlayerController(List<Player> jugadores, GameController gameController, GameView view) {
        this.jugadores = jugadores;
        this.gameController = gameController;
        this.view = view;
    }

    public synchronized void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public synchronized void procesarNombre(String nombre, PrintWriter out) {
        Player player = new Player(nombre);
        jugadores.add(player);
        out.println("Nombre recibido: " + nombre);
    }

    // Corregir firma de método para que sea consistente con el contexto del proyecto
    public synchronized void procesarHunter(String nombreJugador, int hunterNumber, PrintWriter out) {
        Player jugador = buscarJugador(nombreJugador);
        if (jugador == null) {
            out.println("Jugador no encontrado.");
            return;
        }

        if (jugador.getHunter() != null) {
            out.println("Ya has seleccionado un Hunter: " + jugador.getHunter().getName());
            return;
        }

        try {
            Hunter hunterSeleccionado = Hunter.getHunterByNumber(hunterNumber);
            jugador.setHunter(hunterSeleccionado);
            out.println("Hunter elegido: " + hunterSeleccionado.getName());
        } catch (IllegalArgumentException e) {
            out.println("Número de Hunter inválido. Seleccione un número del 1 al 6.");
        }
    }

    public synchronized void procesarListo(String nombreJugador, PrintWriter out, Runnable iniciarJuegoCallback) {
        Player jugador = buscarJugador(nombreJugador);
        if (jugador == null) {
            out.println("Jugador no encontrado.");
            return;
        }
    
        if (!jugador.estaListo()) {
            jugadoresListos++;
            jugador.marcarComoListo();
            out.println("Jugador listo: " + nombreJugador);
            System.out.println("Jugador " + nombreJugador + " está listo. Total de jugadores listos: " + jugadoresListos);
        }
    
        if (jugadoresListos == jugadores.size()) {
            System.out.println("Todos los jugadores están listos. Iniciando el juego...");
            iniciarJuegoCallback.run();
        }
    }
    

    private Player buscarJugador(String nombre) {
        return jugadores.stream()
                .filter(j -> j.getName().equals(nombre))
                .findFirst()
                .orElse(null);
    }
}
