package com.chillizardinteractive.modelo.state;

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

    public List<Card> getCartasEnMano() {
        return cartasEnMano;
    }
}
