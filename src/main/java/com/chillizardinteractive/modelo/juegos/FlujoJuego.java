package com.chillizardinteractive.modelo.juegos;

import com.chillizardinteractive.modelo.deck.Deck;
import com.chillizardinteractive.modelo.card.Card;

import java.util.List;

public class FlujoJuego {
    private Deck originalDeck;
    private Deck playerDeck;
    private Deck computerDeck;
    private boolean collision = false;
    private boolean canAttack = false;
    private boolean playersTurn = false;
    private boolean alreadyMocked = false;
    private boolean gameIsWon = false;
    private boolean isTutorial = false;
    private boolean tauntExists = false;
    private int manaCost;
    private int manaCapacity = 1;
    private int mana = manaCapacity;
    private final int maxOpponentCardsInPlay = 7;
    private boolean inRound;

    public void startGame() {
        Deck deck = new Deck("Default Deck");
        deck.initializeDeck("src/main/resources/decks.json");
        originalDeck = new Deck("Original Deck");
        originalDeck.initializeDeck("src/main/resources/decks.json");
        playerDeck = new Deck("Player Deck");
        computerDeck = new Deck("Computer Deck");

        List<Card> cards = originalDeck.getCardList();
        for (int i = 0; i < 30; i++) {
            playerDeck.addCard(cards.get(i));
        }
        for (int i = 30; i < 60; i++) {
            computerDeck.addCard(cards.get(i));
        }

        playerDeck.shuffle();
        computerDeck.shuffle();
        inRound = false;
        updateDeckCount();

        if (isTutorial) {
            for (Card card : playerDeck.getCardList()) {
                if (card.getNenCost() == 1) {
                    // Añadir carta a la mano del jugador
                    break;
                }
            }
            playerDeck.getCardList().remove(0);
            computerDeck.getCardList().remove(0);
            updateDeckCount();
            checkForRequiredMana();
        } else {
            for (int i = 0; i < 2; i++) {
                // Añadir cartas a la mano del jugador
                playerDeck.getCardList().remove(0);
                computerDeck.getCardList().remove(0);
                updateDeckCount();
            }
            for (Card card : playerDeck.getCardList()) {
                if (card.getNenCost() == 1) {
                    // Añadir carta a la mano del jugador
                    playerDeck.getCardList().remove(0);
                    computerDeck.getCardList().remove(0);
                    updateDeckCount();
                    break;
                }
            }
            checkForRequiredMana();
        }
        createManaCrystal();
        if (/* condición para el tutorial */) {
            isTutorial = true;
        }
    }

    public void placeCardFunc(Card card) {
        if (collision) {
            boolean found = false;
            for (Card originalCard : originalDeck.getCardList()) {
                if (originalCard.getDescription().equals(card.getDescription()) && playerDeck.getCardList().size() < 7) {
                    found = true;
                    mana -= originalCard.getNenCost();
                    // Actualizar GUI de mana
                    playerDeck.addCard(originalCard);
                    checkForRequiredMana();
                    updateManaGUI();
                    // Reproducir sonido de colocar carta
                    if (/* condición para la mano vacía */) {
                        // Mostrar pista para finalizar turno
                    }
                    break;
                }
            }
        }
    }

    public void updateManaGUI() {
        // Actualizar GUI de mana
    }

    public void placeCard() {
        // Añadir listeners para colocar cartas
    }

    public void updateDeckCount() {
        // Actualizar conteo de cartas en los mazos
    }

    public void opponentTurn() {
        playersTurn = false;
        // Lógica para el turno del oponente
    }

    public void computerCardPlace() {
        for (Card card : computerDeck.getCardList()) {
            if (card.getNenCost() == manaCapacity) {
                // Colocar carta en el tablero del oponente
                break;
            } else if (manaCapacity == 10) {
                // Colocar carta en el tablero del oponente
                break;
            }
        }
        // Remover carta del mazo del oponente
        updateDeckCount();
    }

    public void playerTurn() {
        playersTurn = true;
        if (manaCapacity != 10) {
            manaCapacity++;
            createManaCrystal();
        }
        mana = manaCapacity;
        // Actualizar GUI de mana
        // Lógica para el turno del jugador
    }

    public void checkForRequiredMana() {
        // Verificar si el jugador tiene suficiente mana para jugar cartas
    }

    public void createManaCrystal() {
        // Crear cristal de mana
    }

    public void enableDrag() {
        // Habilitar arrastre de cartas
    }

    public void dragElement(Card card) {
        // Lógica para arrastrar cartas
    }

    public void start() {
        startGame();
        placeCard();
        enableDrag();
    }

    public void mensaje1(){
        System.out.println("Jugador 1 inicia.");
    }

    public void mensaje2(){
        System.out.println("Jugador 1 termina turno.");
        System.out.println("Jugador 2 inicia.");
    }

    public void mensajes3(){
        System.out.println("Jugador 1 ataca.");        
    }

    public void mensajes4(){
        System.out.println("Jugador 2 ataca.");          
    }

    public void mensajes5(){
        System.out.println("Jugador 1 roba 3 cartas.");        
    }

    public void mensajes6(){
        System.out.println("Jugador 2 roba 4 cartas."); 
    }

    public void mensajes7(){
        System.out.println("Jugador 1 sufre fatiga de invocación.");        
    }

    public void mensajes8(){
        System.out.println("Jugador 2 sufre fatiga de invocación.");        
    }

    public void mensajes9(){
        System.out.println("Jugador 1 comprueba su aura.");        
    }

    public void mensajes10(){
        System.out.println("Jugador 2 comprueba su aura.");        
    }

    public void mensajes11(){
        System.out.println("Jugador 1 no posee aura suficiente.");        
    }

    public void mensajes12(){
        System.out.println("Jugador 2 no posee aura suficiente.");        
    }

    public void mensajes13(){
        System.out.println("Jugador 1 posee aura suficiente."); 
        System.out.println("Jugador 1 aplica efectos de sus cartas.");        
    }

    public void mensajes14(){
        System.out.println("Jugador 2 posee aura suficiente."); 
        System.out.println("Jugador 2 aplica efectos de sus cartas.");        
    }
}
