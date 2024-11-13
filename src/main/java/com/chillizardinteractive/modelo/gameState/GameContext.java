package com.chillizardinteractive.modelo.gameState;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

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
    private int turnTime;

    public GameContext(Player player1, Player player2, GameView view) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentState = new InicioJuego(view);
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.currentPlayer = player1;
        this.board = new Board();
        this.turnTime = 60; // Tiempo de turno inicial en segundos
    }

    public GameContext(Player player1, Player player2, InicioJuego inicioJuego) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentState = inicioJuego;
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.currentPlayer = player1;
        this.board = new Board();
        this.turnTime = 60; // Tiempo de turno inicial en segundos
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

    public Player getPlayer2() {
        return player2;
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
        if (currentPlayer == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void decrementTurnTime() {
        if (turnTime > 0) {
            turnTime--;
        }
    }

    public int getTurnTime() {
        return turnTime;
    }

}