package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;
import com.chillizardinteractive.modelo.hunter.Hunter;

import java.io.PrintWriter;
import java.util.List;

public class PlayerController {
    private List<Player> jugadores;
    private GameController gameController;
    private GameView view;
    private int jugadoresListos = 0;

    public PlayerController(List<Player> jugadores, GameController gameController, GameView view) {
        this.jugadores = jugadores;
        this.gameController = gameController;
        this.view = view;
    }

    public synchronized void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public synchronized void procesarNombre(String nombre, PrintWriter out) {
        Player jugador = new Player(nombre);
        jugadores.add(jugador);
        out.println("Nombre recibido: " + nombre);
    }

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
            // Inicializar player1 y player2 en GameContext
            if (jugadores.size() >= 2) {
                Player player1 = jugadores.get(0);
                Player player2 = jugadores.get(1);
                gameController.getContext().setPlayer1(player1);
                gameController.getContext().setPlayer2(player2);
            }
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