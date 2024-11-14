package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {
    private GameContext context;
    private ScheduledExecutorService scheduler;

    public GameController(GameContext context) {
        this.context = context;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void procesarJugadorListo() {
        System.out.println("Jugador listo confirmado. Total de jugadores listos: " + context.getNumeroJugadoresListos());
        if (context.todosLosJugadoresListos()) {
            iniciarJuego();
        }
    }

    public void iniciarJuego() {
        System.out.println("Todos los jugadores están listos. Iniciando juego...");
        context.iniciarJuego(); // Este método repartirá las cartas
        iniciarTurno();
    }

    public void lanzarMoneda() {
        System.out.println("Lanzando moneda para determinar el jugador inicial...");
        context.lanzarMoneda();
    }

    public void iniciarTurno() {
        System.out.println("Iniciando turno del jugador: " + context.getCurrentPlayer().getName());
        context.iniciarTurno();
        startTurnTimer();
    }

    private void startTurnTimer() {
        System.out.println("Iniciando temporizador de turno (60 segundos)...");
        scheduler.schedule(() -> {
            System.out.println("Tiempo de turno agotado. Terminando turno automáticamente...");
            terminarTurno();
        }, 60, TimeUnit.SECONDS);
    }

    public void terminarTurno() {
        System.out.println("Turno finalizado.");
        context.terminarTurno();
        iniciarTurno();
    }

    // Método añadido para finalizar el juego
    public void finalizarJuego() {
        System.out.println("El juego ha terminado.");
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
        // Aquí podrías agregar más lógica para limpiar recursos o reiniciar el juego
    }
    // Método añadido para procesar el movimiento de un jugador
    public void procesarMovimiento(String jugador, String accion) {
        Player player = context.getPlayers().stream()
                .filter(p -> p.getName().equals(jugador))
                .findFirst()
                .orElse(null);

        if (player == null) {
            System.out.println("Jugador no encontrado: " + jugador);
            return;
        }

        System.out.println("Procesando movimiento del jugador " + jugador + ": " + accion);
        // Aquí se debería añadir la lógica específica para el tipo de movimiento
        switch (accion) {
            case "colocarCarta":
                System.out.println(jugador + " está colocando una carta en el tablero.");
                // Implementar lógica de colocar carta
                break;
            case "atacar":
                System.out.println(jugador + " está atacando.");
                // Implementar lógica de ataque
                break;
            case "terminarTurno":
                System.out.println(jugador + " está terminando su turno.");
                terminarTurno();
                break;
            default:
                System.out.println("Acción no reconocida: " + accion);
        }
    }
}
