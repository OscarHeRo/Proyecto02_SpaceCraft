package com.chillizardinteractive;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Deck {
    protected List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
    }

    protected void initializeDeck(String filePath, String deckName) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONObject decks = (JSONObject) jsonObject.get("decks");
            JSONObject deckJson = (JSONObject) decks.get(deckName);

            List<Card> minions = loadMinions((JSONArray) deckJson.get("minions"));
            List<Card> spells = loadSpells((JSONArray) deckJson.get("spells"));
            List<Card> weapons = loadWeapons((JSONArray) deckJson.get("weapons"));

            deck.addAll(minions);
            deck.addAll(spells);
            deck.addAll(weapons);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Card> loadMinions(JSONArray minionsArray) {
        List<Card> minions = new ArrayList<>();
        for (Object obj : minionsArray) {
            JSONObject minionJson = (JSONObject) obj;
            String name = (String) minionJson.get("name");
            int attack = ((Long) minionJson.get("attack")).intValue();
            int defense = ((Long) minionJson.get("defense")).intValue();
            int nenCost = ((Long) minionJson.get("cost")).intValue();
            String rarity = (String) minionJson.get("rarity");
            BasicMinionCard minion = new BasicMinionCard(attack, defense, nenCost, name, Rarity.valueOf(rarity));
            minions.add(minion);
        }
        return minions;
    }

    private List<Card> loadSpells(JSONArray spellsArray) {
        List<Card> spells = new ArrayList<>();
        for (Object obj : spellsArray) {
            JSONObject spellJson = (JSONObject) obj;
            String name = (String) spellJson.get("name");
            int nenCost = ((Long) spellJson.get("nenCost")).intValue();
            String description = (String) spellJson.get("description");
            String effect = (String) spellJson.get("effect");
            String rarity = (String) spellJson.get("rarity");
            SpellCard spell = new SpellCard(nenCost, description, effect, Rarity.valueOf(rarity));
            spells.add(spell);
        }
        return spells;
    }

    private List<Card> loadWeapons(JSONArray weaponsArray) {
        List<Card> weapons = new ArrayList<>();
        for (Object obj : weaponsArray) {
            JSONObject weaponJson = (JSONObject) obj;
            String name = (String) weaponJson.get("name");
            int attack = ((Long) weaponJson.get("attack")).intValue();
            int durability = ((Long) weaponJson.get("durability")).intValue();
            int nenCost = ((Long) weaponJson.get("cost")).intValue();
            String rarity = (String) weaponJson.get("rarity");
            WeaponCard weapon = new WeaponCard(attack, durability, nenCost, name, Rarity.valueOf(rarity));
            weapons.add(weapon);
        }
        return weapons;
    }

    public void showDeck() {
        System.out.println("Deck:");
        for (Card card : deck) {
            System.out.println("- " + card.getDescription());
        }
    }
}