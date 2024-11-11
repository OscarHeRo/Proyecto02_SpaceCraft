package com.chillizardinteractive.modelo.board;

import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;
import com.chillizardinteractive.modelo.card.SpellCard;

public class Board {
    private MinionCard[] minions;
    private SpellCard[] spellSpaces;

    public Board() {
        this.minions = new MinionCard[5];
        this.spellSpaces = new SpellCard[5];
    }

    public MinionCard[] getMinions() {
        return minions;
    }

    public SpellCard[] getSpellSpaces() {
        return spellSpaces;
    }

    public boolean placeMinion(MinionCard minion, int position) {
        if (position < 0 || position >= minions.length || minions[position] != null) {
            return false;
        }
        minions[position] = minion;
        return true;
    }

    public boolean placeSpell(SpellCard spell, int position) {
        if (position < 0 || position >= spellSpaces.length || spellSpaces[position] != null) {
            return false;
        }
        spellSpaces[position] = spell;
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Minions]\n");
        for (int i = 0; i < minions.length; i++) {
            sb.append((i + 1) + "._ " + (minions[i] == null ? "vacio" : minions[i].getDescription()) + "\n");
        }
        sb.append("\n[SpellCards]\n");
        for (int i = 0; i < spellSpaces.length; i++) {
            sb.append((i + 1) + "._ " + (spellSpaces[i] == null ? "vacio" : spellSpaces[i].getDescription()) + "\n");
        }
        return sb.toString();
    }
}