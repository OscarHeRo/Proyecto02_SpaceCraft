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
        int resultado = (int) (Math.random() * 2); // 0 o 1
        if (resultado == 0) {
            context.setCurrentPlayer(context.getPlayer1());
            view.mostrarMensajePublico("Jugador 1 gana el lanzamiento de moneda y comienza primero.");
        } else {
            context.setCurrentPlayer(context.getPlayer2());
            view.mostrarMensajePublico("Jugador 2 gana el lanzamiento de moneda y comienza primero.");
        }
        
        // Configura el estado para que inicie el turno
        context.setState(new InicioTurno(view));
        context.iniciarTurno(); // Asegurarse de iniciar el turno después de lanzar la moneda
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
        view.mostrarError("No se puede pasar a la fase de combate en el estado de lanzamiento de moneda.");
    }

    @Override
    public void terminarTurno(GameContext context) {
        view.mostrarError("No se puede terminar el turno en el estado de lanzamiento de moneda.");
    }

    @Override
    public void finalizarJuego(GameContext context) {
        view.mostrarError("No se puede finalizar el juego en el estado de lanzamiento de moneda.");
    }

}