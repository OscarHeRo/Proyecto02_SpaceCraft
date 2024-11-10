package com.chillizardinteractive.modelo.state.gameState;

import com.chillizardinteractive.modelo.state.player.Player;
import com.chillizardinteractive.modelo.state.turn.*;
import java.util.ArrayList;
import java.util.List;

public class GameContext {
    private GameState currentState;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private List<Player> players;
    private boolean permisoParaAtaque = false;

    public GameContext(Player player1, Player player2, GameState initialState) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentState = initialState;
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
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
        permisoParaAtaque = true;
    }
}