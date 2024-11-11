package com.chillizardinteractive.modelo.board;

import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;
import com.chillizardinteractive.modelo.card.SpellCard;

public class Board {
    private MinionCard[] minionSpaces;
    private SpellCard[] spellSpaces;

    public Board() {
        this.minionSpaces = new MinionCard[5];
        this.spellSpaces = new SpellCard[5];
    }

    public MinionCard[] getMinionSpaces() {
        return minionSpaces;
    }

    public SpellCard[] getSpellSpaces() {
        return spellSpaces;
    }

    public boolean placeMinion(MinionCard minion, int position) {
        if (position < 0 || position >= minionSpaces.length || minionSpaces[position] != null) {
            return false;
        }
        minionSpaces[position] = minion;
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
        for (int i = 0; i < minionSpaces.length; i++) {
            sb.append((i + 1) + "._ " + (minionSpaces[i] == null ? "vacio" : minionSpaces[i].getDescription()) + "\n");
        }
        sb.append("\n[SpellCards]\n");
        for (int i = 0; i < spellSpaces.length; i++) {
            sb.append((i + 1) + "._ " + (spellSpaces[i] == null ? "vacio" : spellSpaces[i].getDescription()) + "\n");
        }
        return sb.toString();
    }
}