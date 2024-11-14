package com.chillizardinteractive.modelo.gameState;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.servidor.ServidorJuego;
import com.chillizardinteractive.vista.GameView;

import java.util.List;

public class FlujoDeJuego {
    private List<Player> jugadores;
    private GameController gameController;
    private GameView view;
    private GameContext context;
    private boolean juegoIniciado = false;
    private ServidorJuego servidor; // Referencia al servidor

    // Constructor actualizado para incluir referencia al ServidorJuego
    public FlujoDeJuego(List<Player> jugadores, GameView view, ServidorJuego servidor) {
        this.jugadores = jugadores;
        this.view = view;
        this.servidor = servidor; // Asignar la referencia del servidor
    }

    public void iniciarJuego() {
        if (jugadores.size() == 2 && !juegoIniciado) {
            juegoIniciado = true;
            context = new GameContext(jugadores.get(0), jugadores.get(1), this.view);
            gameController = new GameController(context, servidor); // Pasar referencia del servidor al controlador
            gameController.iniciarJuego();
            gameController.lanzarMoneda();
            cicloDelJuego();
        } else {
            System.out.println("No se puede iniciar el juego. Asegúrese de que haya dos jugadores y el juego no haya sido ya iniciado.");
        }
    }

    private void cicloDelJuego() {
        do {
            Player jugadorActual = context.getCurrentPlayer();
            System.out.println("[Mensaje Público] Iniciando turno del jugador: " + jugadorActual.getName());
            gameController.iniciarTurno();

            while (true) {
                try {
                    Thread.sleep(1000); // Espera para permitir que el cliente envíe su acción
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!context.getCurrentPlayer().equals(jugadorActual)) {
                    break;
                }
            }

            // Evaluación de condición de victoria
            if (context.getPlayer1().getHealth() <= 0) {
                System.out.println("[Mensaje Público] El jugador " + context.getPlayer2().getName() + " ha ganado la partida.");
                break;
            } else if (context.getPlayer2().getHealth() <= 0) {
                System.out.println("[Mensaje Público] El jugador " + context.getPlayer1().getName() + " ha ganado la partida.");
                break;
            }

            context.switchPlayer();
        } while (true);

        gameController.finalizarJuego();
        System.out.println("[Mensaje Público] El juego ha terminado.");
    }

    public GameContext getContext() {
        return context;
    }
}
