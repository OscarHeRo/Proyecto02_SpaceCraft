package com.chillizardinteractive.modelo.deck;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.chillizardinteractive.modelo.card.BasicMinionCard;
import com.chillizardinteractive.modelo.card.BattlecryDecorator;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.ChargeDecorator;
import com.chillizardinteractive.modelo.card.Rarity;
import com.chillizardinteractive.modelo.card.SpellCard;
import com.chillizardinteractive.modelo.card.TauntDecorator;
import com.chillizardinteractive.modelo.card.WeaponCard;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public static Deck generateDeck(String filePath) {
        Deck deck = new Deck();
        deck.initializeDeck(filePath);
        return deck;
    }

    public void initializeDeck(String filePath) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject cardsJson = (JSONObject) jsonObject.get("cards");

            List<Card> deck = new ArrayList<>();
            deck.addAll(loadCards((JSONArray) cardsJson.get("legendary"), 3));
            deck.addAll(loadCards((JSONArray) cardsJson.get("epic"), 7));
            deck.addAll(loadCards((JSONArray) cardsJson.get("rare"), 10));
            deck.addAll(loadCards((JSONArray) cardsJson.get("common"), 15));

            this.cards = deck;

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

    public List<Card> getCards() {
        return cards;
    }

    //metodo que barajea el deck en cuestion in situ
    public void barajear(){
        Collections.shuffle(cards);
    }

    public void showDeck() {
        System.out.println("Deck:");
        if (cards == null) {
            System.out.println("El mazo está vacío.");
            return;
        }
        for (Card card : cards) {
            if (card == null) {
                System.out.println("Carta nula encontrada en el mazo.");
            } else {
                System.out.println("- " + card.getDescription());
            }
        }
    }
}