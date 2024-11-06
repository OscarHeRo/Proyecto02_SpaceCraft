package com.chillizardinteractive.modelo.deck;

import com.chillizardinteractive.modelo.card.BasicMinionCard;
import com.chillizardinteractive.modelo.card.BattlecryDecorator;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.ChargeDecorator;
import com.chillizardinteractive.modelo.card.Rarity;
import com.chillizardinteractive.modelo.card.SpellCard;
import com.chillizardinteractive.modelo.card.TauntDecorator;
import com.chillizardinteractive.modelo.card.WeaponCard;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Deck {
    private String deckName;
    private final Stack<Card> cardStack;
    private final List<Card> originalDeck;

    public Deck(String deckName) {
        this.deckName = deckName;
        this.cardStack = new Stack<>();
        this.originalDeck = new ArrayList<>();
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public List<Card> getCardList() {
        return new ArrayList<>(cardStack);
    }

    public void addCard(Card card) {
        if (cardStack.size() < 20 && cardStack.stream().filter(x -> x.getDescription().equals(card.getDescription())).count() < 3) {
            cardStack.push(card);
        }
    }

    public void deleteCard(String cardDescription) {
        cardStack.removeIf(card -> card.getDescription().equals(cardDescription));
    }

    public String getExportValueAsYdkFile() {
        StringBuilder text = new StringBuilder("#main\n");
        for (Card card : cardStack) {
            text.append(card.getDescription()).append("\n");
        }
        text.append("\n#extra\n!side\n");
        return text.toString();
    }

    public static Deck generateDeck(String filePath) {
        Deck deck = new Deck("Default Deck");
        deck.initializeDeck(filePath);
        return deck;
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
            int nenCost = ((Long) cardJson.get("cost")).intValue();
            Rarity rarity = Rarity.valueOf((String) cardJson.get("rarity"));
            String type = (String) cardJson.get("type");

            Card card;
            switch (type) {
                case "Spell":
                    String description = (String) cardJson.get("description");
                    card = new SpellCard(nenCost, name, description, rarity);
                    break;
                case "Weapon":
                    int weaponAttack = ((Long) cardJson.get("attack")).intValue();
                    int durability = ((Long) cardJson.get("durability")).intValue();
                    card = new WeaponCard(weaponAttack, durability, nenCost, name, rarity);
                    break;
                case "Minion":
                    int minionAttack = ((Long) cardJson.get("attack")).intValue();
                    int defense = ((Long) cardJson.get("defense")).intValue();
                    card = new BasicMinionCard(name, nenCost, rarity, minionAttack, defense);

                    String effect = (String) cardJson.get("effect");
                    if (effect != null) {
                        switch (effect) {
                            case "Taunt":
                                card = new TauntDecorator(card);
                                break;
                            case "Battlecry":
                                card = new BattlecryDecorator(card, "Grito de Batalla");
                                break;
                            case "Charge":
                                card = new ChargeDecorator(card);
                                break;
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de carta desconocido: " + type);
            }

            cards.add(card);
        }
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cardStack);
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

    /**
     * Saca una carta del mazo. Si el mazo está vacío, lo vuelve a llenar con todas las cartas del mazo original y las baraja.
     *
     * @return La carta en la cima del mazo, o null si no hay cartas.
     */
    public Card sacarCarta() {
        if (cardStack.isEmpty()) {
            System.out.println("El mazo está vacío. Barajando el mazo original...");
            cardStack.addAll(originalDeck);
            shuffle();
        }
        return cardStack.isEmpty() ? null : cardStack.pop();
    }
}