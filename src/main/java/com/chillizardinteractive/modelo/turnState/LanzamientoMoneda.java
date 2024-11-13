package com.chillizardinteractive.modelo.turnState;

import com.chillizardinteractive.modelo.gameState.GameState;
import com.chillizardinteractive.modelo.gameState.Moneda;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.vista.GameView;

public class LanzamientoMoneda implements GameState {
    private GameView view;

    public LanzamientoMoneda(GameView view) {
        this.view = view;
    }

    @Override
    public void lanzarMoneda(GameContext context) {
        view.mostrarMensaje("Lanzando moneda para determinar el primer jugador...");

        if (context.getPlayer1() == null || context.getPlayer2() == null) {
            view.mostrarError("Error: No se han inicializado ambos jugadores.");
            return;
        }

        String eleccionJugador1 = "cara"; // Suponiendo que el jugador 1 elige "cara"
        String resultado = Moneda.lanzarMoneda();
        
        if (resultado == null) {
            view.mostrarError("Error en el lanzamiento de moneda: resultado no disponible.");
            return;
        }

        if (resultado.equals(eleccionJugador1)) {
            view.mostrarMensaje("Jugador 1 gana el lanzamiento de moneda y comienza primero.");
            context.setCurrentPlayer(context.getPlayer1());
        } else {
            view.mostrarMensaje("Jugador 2 gana el lanzamiento de moneda y comienza primero.");
            context.setCurrentPlayer(context.getPlayer2());
        }

        context.setState(new InicioTurno(view));
        context.iniciarTurno();
    }

    @Override
    public void iniciarJuego(GameContext context) {
        view.mostrarError("No se puede iniciar el juego en el estado de lanzamiento de moneda.");
    }

    @Override
    public void iniciarTurno(GameContext context) {
        view.mostrarError("No se puede iniciar el turno en el estado de lanzamiento de moneda.");
    }

    @Override
    public void faseCombate(GameContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'faseCombate'");
    }

    @Override
    public void terminarTurno(GameContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'terminarTurno'");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizarJuego'");
    }
}