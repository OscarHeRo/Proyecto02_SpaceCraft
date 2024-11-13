package com.chillizardinteractive;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.modelo.deck.Deck;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.util.Scanner;

public class DueloDeCazadores {
    public static void main(String[] args) {
        // Crear la vista del juego
        GameView view = new GameView();

        // Inicializar los mazos desde el archivo decks.json
        Deck deck1 = new Deck("Mazo1");
        Deck deck2 = new Deck("Mazo2");
        deck1.initializeDeck("src/main/resources/decks.json");
        deck2.initializeDeck("src/main/resources/decks.json");

        // Crear los jugadores con sus mazos
        Player player1 = new Player("Jugador 1", deck1);
        Player player2 = new Player("Jugador 2", deck2);

        // Crear el contexto del juego con los jugadores y la vista
        GameContext context = new GameContext(player1, player2, view);

        // Crear el controlador del juego con el contexto y la vista
        GameController controller = new GameController(context, view);

        // Iniciar el juego
        controller.iniciarJuego();

        // Lanzar la moneda y determinar el primer jugador
        controller.lanzarMoneda();

        // Iniciar el turno del jugador actual
        Scanner scanner = new Scanner(System.in);
        while (true) {
            controller.iniciarTurno();
            while (true) {
                System.out.println("Seleccione una acción: 1. Colocar carta, 2. Atacar, 3. Terminar turno");
                int accion = scanner.nextInt();
                if (accion == 1) {
                    controller.colocarCartaEnTablero(context.getCurrentPlayer(), context.getBoard());
                } else if (accion == 2) {
                    controller.atacarConMinion(context.getCurrentPlayer(), context.getBoard());
                } else if (accion == 3) {
                    controller.terminarTurno();
                    break;
                } else {
                    System.out.println("Acción no válida. Intente de nuevo.");
                }
            }
            if (context.getCurrentPlayer().getHealth() <= 0 || context.getOpponentPlayer().getHealth() <= 0) {
                controller.finalizarJuego();
                break;
            }
        }
    }
}