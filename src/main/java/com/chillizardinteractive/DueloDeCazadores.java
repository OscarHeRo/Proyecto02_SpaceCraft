package com.chillizardinteractive;

import com.chillizardinteractive.modelo.state.*;

public class DueloDeCazadores {
    public static void main(String[] args) {
        Player player1 = new Player("Jugador 1");
        Player player2 = new Player("Jugador 2");
        GameContext context = new GameContext(player1, player2, new InicioJuego());

        // Iniciar el juego
        context.iniciarJuego();

        // Lanzar la moneda y determinar el primer jugador
        context.lanzarMoneda();
    }
}