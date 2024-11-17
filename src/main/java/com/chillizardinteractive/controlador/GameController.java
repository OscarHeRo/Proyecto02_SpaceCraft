package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {
    private GameContext context;
    private GameView view;
    private ScheduledExecutorService scheduler;

    public GameController(GameContext context, GameView view) {
        this.context = context;
        this.view = view;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public GameContext getContext() {
        return context;
    }

    public void iniciarPartida(Player jugador1, Player jugador2) {
        context.setPlayer1(jugador1);
        context.setPlayer2(jugador2);
        context.iniciarJuego();
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
        Player currentPlayer = context.getCurrentPlayer();
        System.out.println("Iniciando turno del jugador: " + currentPlayer.getName());
        
        // Notificar al jugador que es su turno
        view.mostrarMensajePrivado(currentPlayer.getName(), "Iniciando turno del jugador actual...");
        
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
        context.terminarTurno();
    }

    public void finalizarJuego() {
        context.finalizarJuego();
        scheduler.shutdown();
    }

    public void procesarMovimiento(String jugador, String accion) {
        Player player = context.getPlayers().stream()
                .filter(p -> p.getName().equals(jugador))
                .findFirst()
                .orElse(null);

        if (player == null) {
            view.mostrarError("Jugador no encontrado: " + jugador);
            return;
        }

        if (player.getMano().getCartasEnMano().isEmpty()) {
            view.mostrarError("La mano está vacía. No se puede realizar la acción.");
            return;
        }

        System.out.println("Procesando movimiento del jugador " + jugador + ": " + accion);
        
        // Lógica específica para el tipo de movimiento
        switch (accion) {
            case "colocarCarta":
                System.out.println(jugador + " está colocando una carta en el tablero.");
                view.mostrarMensajePrivado(player.getName(), "Has colocado una carta en el tablero.");
                // Implementar lógica de colocar carta
                break;
            case "atacar":
                System.out.println(jugador + " está atacando.");
                view.mostrarMensajePrivado(player.getName(), "Has iniciado un ataque.");
                // Implementar lógica de ataque
                break;
            case "terminarTurno":
                System.out.println(jugador + " está terminando su turno.");
                terminarTurno();
                break;
            default:
                System.out.println("Acción no reconocida: " + accion);
                view.mostrarMensajePrivado(player.getName(), "Acción no reconocida, intenta de nuevo.");
        }
    }
}