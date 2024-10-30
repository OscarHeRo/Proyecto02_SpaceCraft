package com.chillizardinteractive.handler;

import com.chillizardinteractive.modelo.deck.Deck;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameWebSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();
    private Deck deck1;
    private Deck deck2;

    public GameWebSocketHandler() {
        this.deck1 = new Deck();
        this.deck1.initializeDeck("src/main/resources/decks.json");
        this.deck2 = new Deck();
        this.deck2.initializeDeck("src/main/resources/decks.json");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        sendDeckToPlayer(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Manejar mensajes recibidos de los jugadores
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    private void sendDeckToPlayer(WebSocketSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String deckJson = mapper.writeValueAsString(deck1.getCards());
        session.sendMessage(new TextMessage(deckJson));
    }
}