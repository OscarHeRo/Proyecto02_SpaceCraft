package com.chillizardinteractive.modelo.player;

import com.chillizardinteractive.modelo.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Mano {
    private List<Card> cartasEnMano;

    public Mano() {
        this.cartasEnMano = new ArrayList<>();
    }

    public void agregarCartasMano(Card carta) {
        cartasEnMano.add(carta);
    }

    public Card getCartaByIndex(int index) {
        return cartasEnMano.get(index);
    }

    public void removerCartasByIndex(int index) {
        cartasEnMano.remove(index);
    }

    public List<Card> getCartasEnMano() {
        return cartasEnMano;
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