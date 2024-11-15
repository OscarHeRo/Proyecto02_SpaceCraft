package com.chillizardinteractive.modelo.deckProxy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chillizardinteractive.modelo.Decorator.*;
import com.chillizardinteractive.modelo.cardFactory.Card;
import com.chillizardinteractive.modelo.cardFactory.CardFactory;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private String deckName;
    private final Stack<Card> cardStack;
    private final List<Card> originalDeck;

    public Deck(String deckName) {
        this.deckName = deckName;
        this.cardStack = new Stack<>();
        this.originalDeck = new ArrayList<>();
    }

    public void initializeDeck(String filePath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject cardsJson = (JSONObject) jsonObject.get("cards");

            List<Card> cards = new ArrayList<>();
            cards.addAll(loadCards((JSONArray) cardsJson.get("legendary"), 3));
            cards.addAll(loadCards((JSONArray) cardsJson.get("epic"), 7));
            cards.addAll(loadCards((JSONArray) cardsJson.get("rare"), 10));
            cards.addAll(loadCards((JSONArray) cardsJson.get("common"), 15));

            Collections.shuffle(cards);
            this.cardStack.addAll(cards);
            this.originalDeck.addAll(cards);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Card> loadCards(JSONArray cardsArray, int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count && i < cardsArray.size(); i++) {
            JSONObject cardJson = (JSONObject) cardsArray.get(i);
            String name = (String) cardJson.get("name");
            String description = (String) cardJson.get("description");
            int nenCost = ((Long) cardJson.get("cost")).intValue();
            String type = (String) cardJson.get("type");
            int attack = cardJson.containsKey("attack") ? ((Long) cardJson.get("attack")).intValue() : 0;
            int defense = cardJson.containsKey("defense") ? ((Long) cardJson.get("defense")).intValue() : 0;
            Rarity rarity = Rarity.valueOf((String) cardJson.get("rarity"));
            Card card = CardFactory.crearCarta(type, name, description, nenCost, attack, defense, rarity);

            String effect = (String) cardJson.get("effect");
            if (effect != null) {
                switch (effect) {
                    case "Taunt":
                        card = new TauntDecorator(card);
                        break;
                    case "Charge":
                        card = new ChargeDecorator(card);
                        break;
                    // Agregar más decoradores según sea necesario
                }
            }
            cards.add(card);
        }
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cardStack);
    }

    public Card sacarCarta() {
        if (cardStack.isEmpty()) {
            System.out.println("El mazo está vacío. Barajando el mazo original...");
            cardStack.addAll(originalDeck);
            shuffle();
        }
        return cardStack.isEmpty() ? null : cardStack.pop();
    }

    public void showDeck() {
        System.out.println("Deck:");
        if (cardStack.isEmpty()) {
            System.out.println("El mazo está vacío.");
            return;
        }
        for (Card card : cardStack) {
            if (card == null) {
                System.out.println("Carta nula encontrada en el mazo.");
            } else {
                System.out.println("- " + card.getDescription());
            }
        }
    }

    public List<Card> getCardList() {
        return new ArrayList<>(cardStack);
    }

    public List<Card> getCards() {
        return originalDeck;
    }

    public String  deckToString() {

        String deckString = "";
        for (Card card : cardStack) {
            deckString += card.getDescription() + "\n";
        }
        return deckString;
    }
}