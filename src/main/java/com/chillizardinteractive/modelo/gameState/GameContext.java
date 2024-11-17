package com.chillizardinteractive.modelo.gameState;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.deck.Deck;
import com.chillizardinteractive.modelo.player.Player;
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

    public GameContext(List<Player> players, GameView view) {
        this.players = players;
        this.view = view;
        this.board = new Board();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
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
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getOpponentPlayer() {
        return (currentPlayer == player1) ? player2 : player1;
    }

    public boolean todosLosJugadoresListos() {
        return players.stream().allMatch(Player::estaListo);
    }

    public String getNumeroJugadoresListos() {
        return players.stream().filter(Player::estaListo).count() + "/" + players.size();
    }

    public void iniciarJuego() {
        view.mostrarMensajePublico("Iniciando el juego...");
        // Inicializar los mazos desde el archivo decks.json
        Deck deck1 = new Deck("Mazo1");
        Deck deck2 = new Deck("Mazo2");
        deck1.initializeDeck("src/main/resources/decks.json");
        deck2.initializeDeck("src/main/resources/decks.json");
        // Crear jugadores con sus mazos
        player1.setDeck(deck1);
        player2.setDeck(deck2);
        // Configurar jugadores con un espacio de nen y un punto de nen
        player1.incrementarNenPoints();
        player2.incrementarNenPoints();
        // Mostrar los mazos de los jugadores
        System.out.println(player1.getDeck().getCards());
        System.out.println(player2.getDeck().getCards());
        // Repartir las cartas
        repartirCartas();
        // Lanzar moneda para determinar el primer jugador
        lanzarMoneda();
    }

    private void repartirCartas() {
        for (int i = 0; i < 3; i++) {
            player1.robarCarta();
        }
        for (int i = 0; i < 4; i++) {
            player2.robarCarta();
        }
    }

    public void lanzarMoneda() {
        view.mostrarMensajePublico("Lanzando moneda para determinar el primer jugador...");
        // Suponiendo que el jugador 1 elige cara
        String eleccionJugador1 = "cara"; // "cara" o "cruz"
        String resultado = Moneda.lanzarMoneda();

        if (resultado.equals(eleccionJugador1)) {
            view.mostrarMensajePublico("Jugador 1 gana el lanzamiento de moneda y comienza primero.");
            currentPlayer = player1;
        } else {
            view.mostrarMensajePublico("Jugador 2 gana el lanzamiento de moneda y comienza primero.");
            currentPlayer = player2;
        }
        iniciarTurno();
    }

    public void iniciarTurno() {
        view.mostrarMensajePrivado(currentPlayer.getName(), "Iniciando turno del jugador actual...");
        currentPlayer.incrementarNenSpaces();
        currentPlayer.rellenarNenPoints();
        Card drawnCard = currentPlayer.getDeck().sacarCarta();
        if (drawnCard != null) {
            currentPlayer.getMano().agregarCartasMano(drawnCard);
            view.mostrarCartaRobada(currentPlayer.getName(), drawnCard.getDescription());
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
        view.mostrarMensajePublico("Terminando el turno del jugador actual...");
        switchPlayer();
        iniciarTurno();
    }

    public void finalizarJuego() {
        view.mostrarMensajePublico("El juego ha terminado.");
        // Lógica para finalizar el juego
    }
}