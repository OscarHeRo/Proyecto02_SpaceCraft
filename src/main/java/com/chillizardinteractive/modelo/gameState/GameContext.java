package com.chillizardinteractive.modelo.gameState;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.player.Player;
import java.util.ArrayList;
import java.util.List;

public class GameContext {
    private GameState currentState;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private List<Player> players;
    private boolean permisoParaAtaque = false;
    private Board board;

    public GameContext(Player player1, Player player2, GameState initialState) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentState = initialState;
        this.players = new ArrayList<>();
        if (player1 != null) this.players.add(player1);
        if (player2 != null) this.players.add(player2);
        this.currentPlayer = player1;
        this.board = new Board();
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void iniciarJuego() {
        currentState.iniciarJuego(this);
    }

    public void lanzarMoneda() {
        currentState.lanzarMoneda(this);
    }

    // Cambiado para evitar duplicación de cartas
    public void iniciarTurno() {
        currentState.iniciarTurno(this);
    }

    public void faseCombate() {
        currentState.faseCombate(this);
    }

    public void terminarTurno() {
        currentState.terminarTurno(this);
    }

    public void finalizarJuego() {
        currentState.finalizarJuego(this);
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
        if (!players.contains(player1)) {
            players.add(player1);
        }
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
        if (!players.contains(player2)) {
            players.add(player2);
        }
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
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public Player getOpponentPlayer() {
        return currentPlayer == player1 ? player2 : player1;
    }
}
