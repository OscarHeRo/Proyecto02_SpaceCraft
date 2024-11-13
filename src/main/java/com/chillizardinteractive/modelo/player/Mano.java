package com.chillizardinteractive.modelo.player;

import com.chillizardinteractive.modelo.board.Board;
import com.chillizardinteractive.modelo.card.Card;
import com.chillizardinteractive.modelo.card.MinionCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mano {
    private List<Card> cartasEnMano;

    public Mano() {
        this.cartasEnMano = new ArrayList<>();
    }

    public void agregarCartasMano(Card carta) {
        if (carta == null) {
            throw new IllegalArgumentException("No se puede agregar una carta nula a la mano.");
        }
        cartasEnMano.add(carta);
    }

    public Card getCartaByIndex(int index) {
        if (index < 0 || index >= cartasEnMano.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        return cartasEnMano.get(index);
    }

    public void removerCartasByIndex(int index) {
        if (index < 0 || index >= cartasEnMano.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        cartasEnMano.remove(index);
    }

    public List<Card> getCartasEnMano() {
        return Collections.unmodifiableList(cartasEnMano);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mano del jugador:\n");
        for (int i = 0; i < cartasEnMano.size(); i++) {
            sb.append((i + 1) + "._ " + cartasEnMano.get(i).getDescription() + "\n");
        }
        return sb.toString();
    }
}
