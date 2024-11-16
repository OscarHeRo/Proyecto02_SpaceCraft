package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.servidor.ServidorJuego;

<<<<<<< HEAD
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {
    private GameContext context;
    private ScheduledExecutorService scheduler;
    private ServidorJuego servidor; // Referencia al servidor para enviar mensajes a los jugadores
=======
import java.util.Scanner;

public class GameController {
    private GameContext context;
    private GameView view;
    private Scanner scanner;
>>>>>>> parent of d243832 (terminamos flujo de juego porfin)

    // Constructor actualizado para incluir la referencia al servidor
    public GameController(GameContext context, ServidorJuego servidor) {
        this.context = context;
<<<<<<< HEAD
        this.servidor = servidor;
        this.scheduler = Executors.newScheduledThreadPool(1);
=======
        this.view = view;
        this.scanner = new Scanner(System.in);
>>>>>>> parent of d243832 (terminamos flujo de juego porfin)
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
        Player currentPlayer = context.getCurrentPlayer();
        System.out.println("Iniciando turno del jugador: " + currentPlayer.getName());
        
        // Notificar al jugador que es su turno
        servidor.enviarMensajeAlJugador(currentPlayer, "Iniciando turno del jugador actual...");
        
        context.iniciarTurno();
    }

    private void startTurnTimer() {
        System.out.println("Iniciando temporizador de turno (60 segundos)...");
        scheduler.schedule(() -> {
            System.out.println("Tiempo de turno agotado. Terminando turno automáticamente...");
            terminarTurno();
        }, 60, TimeUnit.SECONDS);
    }

    public void terminarTurno() {
        Player currentPlayer = context.getCurrentPlayer();
        System.out.println("Turno finalizado del jugador: " + currentPlayer.getName());
        
        // Notificar al jugador que su turno ha finalizado
        servidor.enviarMensajeAlJugador(currentPlayer, "Terminando tu turno...");

        context.terminarTurno();
        iniciarTurno();
    }

    // Método añadido para finalizar el juego
    public void finalizarJuego() {
<<<<<<< HEAD
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
=======
        context.finalizarJuego();
    }
>>>>>>> parent of d243832 (terminamos flujo de juego porfin)

<<<<<<< HEAD
        if (player == null) {
            System.out.println("Jugador no encontrado: " + jugador);
=======
    public void mostrarEstadoJugador(String nombre, int vida, int nenSpaces, int nenPoints) {
        view.mostrarEstadoJugador(nombre, vida, nenSpaces, nenPoints);
    }

    public void mostrarCartaRobada(String nombreJugador, String descripcionCarta) {
        view.mostrarCartaRobada(nombreJugador, descripcionCarta);
    }

    public void mostrarMensaje(String mensaje) {
        view.mostrarMensaje(mensaje);
    }

    public void mostrarError(String mensaje) {
        view.mostrarError(mensaje);
    }

    public void mostrarMano(Player player) {
        view.mostrarMensaje("Mano de " + player.getName() + ": " + player.getMano().toString());
    }

    public void colocarCartaEnTablero(Player player, Board board) {
<<<<<<< HEAD
        if (player.getMano().getCartasEnMano().isEmpty()) {
            view.mostrarError("La mano está vacía. No se puede colocar ninguna carta en el tablero.");
>>>>>>> parent of a83ede4 (comit1)
            return;
        }

        System.out.println("Procesando movimiento del jugador " + jugador + ": " + accion);
        
        // Lógica específica para el tipo de movimiento
        switch (accion) {
            case "colocarCarta":
                System.out.println(jugador + " está colocando una carta en el tablero.");
                servidor.enviarMensajeAlJugador(player, "Has colocado una carta en el tablero.");
                // Implementar lógica de colocar carta
                break;
            case "atacar":
                System.out.println(jugador + " está atacando.");
                servidor.enviarMensajeAlJugador(player, "Has iniciado un ataque.");
                // Implementar lógica de ataque
                break;
            case "terminarTurno":
                System.out.println(jugador + " está terminando su turno.");
                terminarTurno();
                break;
            default:
                System.out.println("Acción no reconocida: " + accion);
                servidor.enviarMensajeAlJugador(player, "Acción no reconocida, intenta de nuevo.");
        }
    }
}
=======
        mostrarMano(player);
        view.mostrarMensaje("Seleccione una carta para colocar en el tablero (1-" + player.getMano().toString() + "): ");
        int cartaIndex = scanner.nextInt() - 1;
        Card cartaSeleccionada = player.getMano().get(cartaIndex);
        if (cartaSeleccionada instanceof MinionCard) {
            view.mostrarMensaje("Seleccione una posición para el minion (1-5): ");
            int posicion = scanner.nextInt() - 1;
            if (board.placeMinion((MinionCard) cartaSeleccionada, posicion)) {
                view.mostrarMensaje("Minion colocado en la posición " + (posicion + 1));
                player.getMano().remove(cartaSeleccionada);
            } else {
                view.mostrarError("Posición inválida o ya ocupada.");
            }
        } else if (cartaSeleccionada instanceof SpellCard) {
            view.mostrarMensaje("Seleccione una posición para el hechizo (1-5): ");
            int posicion = scanner.nextInt() - 1;
            if (board.placeSpell((SpellCard) cartaSeleccionada, posicion)) {
                view.mostrarMensaje("Hechizo colocado en la posición " + (posicion + 1));
                player.getMano().remove(cartaSeleccionada);
            } else {
                view.mostrarError("Posición inválida o ya ocupada.");
            }
        } else {
            view.mostrarError("Carta no válida.");
        }
    }
}
>>>>>>> parent of d243832 (terminamos flujo de juego porfin)
