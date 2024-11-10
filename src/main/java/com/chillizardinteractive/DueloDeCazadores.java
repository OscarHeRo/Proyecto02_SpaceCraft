package com.chillizardinteractive;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.gameState.InicioJuego;
import com.chillizardinteractive.vista.GameView;

public class DueloDeCazadores {
    public static void main(String[] args) {
        Player player1 = new Player("Jugador 1");
        Player player2 = new Player("Jugador 2");
        GameView view = new GameView();
        GameContext context = new GameContext(player1, player2, new InicioJuego(view));
        GameController controller = new GameController(context, view);

        // Iniciar el juego
        controller.iniciarJuego();

        // Lanzar la moneda y determinar el primer jugador
        controller.lanzarMoneda();
    }
}