package com.chillizardinteractive;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.gameState.InicioJuego;
import com.chillizardinteractive.vista.GameView;

import java.util.Scanner;

public class DueloDeCazadores {
    public static void main(String[] args) {
        GameView view = new GameView();
        GameContext context = new GameContext(null, null, new InicioJuego(view));
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