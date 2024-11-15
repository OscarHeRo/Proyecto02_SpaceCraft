package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.Player;
import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.cardFactory.Card;
import com.chillizardinteractive.modelo.deckProxy.Deck;
import com.chillizardinteractive.vista.GameView;

import java.util.ArrayList;
import java.util.List;

public class GameContext {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private List<Player> players;
    private boolean permisoParaAtaque = false;
    private Board board;
    private GameView view;

    // Constructor actualizado para permitir tanto el uso de una lista dinámica como dos jugadores individuales.
    public GameContext(Player player1, Player player2, GameView view) {
        this.player1 = player1;
        this.player2 = player2;
        this.view = view;
        this.players = new ArrayList<>();
        
        if (player1 != null) {
            this.players.add(player1);
        }
        if (player2 != null) {
            this.players.add(player2);
        }

        this.currentPlayer = player1; // Por defecto, el primer jugador es el que comienza.
        this.board = new Board();
    }

    // Constructor adicional que acepta una lista dinámica de jugadores
    public GameContext(List<Player> players, GameView view) {
        this.players = players;
        this.view = view;
        this.board = new Board();

        if (!players.isEmpty()) {
            this.currentPlayer = players.get(0); // Asignar el primer jugador como jugador actual si hay al menos uno en la lista
        }
    }

    public void iniciarJuego() {
        view.mostrarMensaje("Iniciando el juego...");

        // Inicializar los mazos para cada jugador desde el archivo decks.json
        for (Player player : players) {
            Deck deck = new Deck("Mazo de " + player.getName());
            deck.initializeDeck("src/main/resources/decks.json");
            player.setDeck(deck);
            player.incrementarNenPoints(); // Inicializar con un punto de Nen
            System.out.println(player.getDeck().getCards()); // Mostrar el mazo de cada jugador
        }

        // Repartir las cartas iniciales
        repartirCartas();

        // Lanzar moneda para determinar el primer jugador
        lanzarMoneda();
    }

    private void repartirCartas() {
        for (Player player : players) {
            int numCartasIniciales = (player == player1) ? 3 : 4; // Player 1 recibe 3 cartas, Player 2 recibe 4 cartas
            for (int i = 0; i < numCartasIniciales; i++) {
                player.robarCarta();
            }
        }
    }

    public void lanzarMoneda() {
        view.mostrarMensaje("Lanzando moneda para determinar el primer jugador...");
        int jugadorInicialIndex = (int) (Math.random() * players.size()); // Elegir al azar un jugador de la lista
        currentPlayer = players.get(jugadorInicialIndex);
        view.mostrarMensaje(currentPlayer.getName() + " gana el lanzamiento de moneda y comienza primero.");
        iniciarTurno();
    }

    public void iniciarTurno() {
        if (currentPlayer == null) {
            view.mostrarError("No hay jugador actual definido.");
            return;
        }

        view.mostrarMensajePrivado(currentPlayer.getName(), "Iniciando turno del jugador actual...");
        currentPlayer.incrementarNenSpaces();
        currentPlayer.rellenarNenPoints();
        Card drawnCard = currentPlayer.getDeck().sacarCarta();

        if (drawnCard != null) {
            currentPlayer.getMano().agregarCartasMano(drawnCard);
            view.mostrarCartaRobada(currentPlayer.getName(), drawnCard.getDescripcion());
        } else {
            view.mostrarMensajePrivado(currentPlayer.getName(), "No tienes más cartas en el mazo.");
        }
    }

    public void faseCombate() {
        if (permisoParaAtaque) {
            // Lógica de combate
        } else {
            view.mostrarError("No tienes permiso para atacar.");
        }
    }

    public void terminarTurno() {
        view.mostrarMensaje("Terminando el turno del jugador actual...");
        switchPlayer();
        iniciarTurno();
    }

    public void finalizarJuego() {
        view.mostrarMensaje("El juego ha terminado.");
        // Lógica para finalizar el juego
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void autorizarAtaque() {
        this.permisoParaAtaque = true;
    }

    public Board getBoard() {
        return board;
    }

    public void switchPlayer() {
        // Obtener el índice del jugador actual y pasar al siguiente
        int currentIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentIndex + 1) % players.size());
    }

    public Player getOpponentPlayer() {
        // Obtener al oponente (usado en combate, por ejemplo)
        int currentIndex = players.indexOf(currentPlayer);
        return players.get((currentIndex + 1) % players.size()); // Simplemente tomar el siguiente
    }

    public boolean todosLosJugadoresListos() {
        for (Player player : players) {
            if (!player.estaListo()) {
                return false;
            }
        }
        return true;
    }

    public String getNumeroJugadoresListos() {
        int count = 0;
        for (Player player : players) {
            if (player.estaListo()) {
                count++;
            }
        }
        return String.valueOf(count);
    }
}