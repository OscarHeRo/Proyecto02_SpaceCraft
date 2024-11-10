package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.vista.GameView;

public class GameController {
    private GameContext context;
    private GameView view;

    public GameController(GameContext context, GameView view) {
        this.context = context;
        this.view = view;
    }

    public void iniciarJuego() {
        context.iniciarJuego();
    }

    public void lanzarMoneda() {
        context.lanzarMoneda();
    }

    public void iniciarTurno() {
        context.iniciarTurno();
    }

    public void faseCombate() {
        context.faseCombate();
    }

    public void terminarTurno() {
        context.terminarTurno();
    }

    public void finalizarJuego() {
        context.finalizarJuego();
    }

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
}