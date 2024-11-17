package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;
import com.chillizardinteractive.modelo.card.SpellCard;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.vista.GameView;

import java.util.Scanner;

public class GameController {
    private GameContext context;
    private GameView view;
    private Scanner scanner;

    public GameController(GameContext context, GameView view) {
        this.context = context;
        this.view = view;
        this.scanner = new Scanner(System.in);
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

    public void mostrarMano(Player player) {
        view.mostrarMensaje("Mano de " + player.getName() + ": " + player.getMano().toString());
    }

    public void colocarCartaEnTablero(Player player, Board board) {
        mostrarMano(player);
        view.mostrarMensaje("Seleccione una carta para colocar en el tablero (1-" + player.getMano().getCartasEnMano().size() + "): ");
        int cartaIndex = scanner.nextInt() - 1;
        Card cartaSeleccionada = player.getMano().getCartasEnMano().get(cartaIndex);

        if (cartaSeleccionada instanceof MinionCard) {
            view.mostrarMensaje("Seleccione una posición para el minion (1-5): ");
            int posicion = scanner.nextInt() - 1;
            if (board.placeMinion((MinionCard) cartaSeleccionada, posicion)) {
                view.mostrarMensaje("Minion colocado en la posición " + (posicion + 1));
                player.getMano().getCartasEnMano().remove(cartaSeleccionada);
            } else {
                view.mostrarError("Posición inválida o ya ocupada.");
            }
        } else if (cartaSeleccionada instanceof SpellCard) {
            view.mostrarMensaje("Seleccione una posición para el hechizo (1-5): ");
            int posicion = scanner.nextInt() - 1;
            if (board.placeSpell((SpellCard) cartaSeleccionada, posicion)) {
                view.mostrarMensaje("Hechizo colocado en la posición " + (posicion + 1));
                player.getMano().getCartasEnMano().remove(cartaSeleccionada);
            } else {
                view.mostrarError("Posición inválida o ya ocupada.");
            }
        } else {
            view.mostrarError("Carta no válida.");
        }
    }
}