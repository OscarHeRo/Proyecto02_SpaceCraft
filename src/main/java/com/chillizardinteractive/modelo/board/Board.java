package com.chillizardinteractive.modelo.board;

import com.chillizardinteractive.modelo.Player;
import com.chillizardinteractive.modelo.cardFactory.MinionCard;
import com.chillizardinteractive.modelo.cardFactory.SpellCard;

public class Board {
    private MinionCard[] minionSpaces;
    private SpellCard[] spellSpaces;

    public Board() {
        this.minionSpaces = new MinionCard[5];
        this.spellSpaces = new SpellCard[5];
    }

    public MinionCard[] getMinions() {
        return minionSpaces.clone();
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
            sb.append((i + 1) + "._ " + (minionSpaces[i] == null ? "vacio" : minionSpaces[i].getDescripcion()) + "\n");
        }
        sb.append("\n[SpellCards]\n");
        for (int i = 0; i < spellSpaces.length; i++) {
            sb.append((i + 1) + "._ " + (spellSpaces[i] == null ? "vacio" : spellSpaces[i].getDescripcion()) + "\n");
        }
        return sb.toString();
    }

    public MinionCard getMinion(int atacanteIndex) {
        return minionSpaces[atacanteIndex];
    }

    public boolean hasTauntMinion(Player opponentPlayer) {
        for (MinionCard minion : minionSpaces) {
            if (minion != null && minion.hasTaunt()) {
                return true;
            }
        }
        return false;
    }

    public void removeMinion(int objetivoIndex) {
        minionSpaces[objetivoIndex] = null;
    }
}