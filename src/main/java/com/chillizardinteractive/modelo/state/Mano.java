package com.chillizardinteractive.modelo.state;

import java.util.ArrayList;

import com.chillizardinteractive.modelo.card.Card;

public class Mano {
    private ArrayList<Card> mano;

    public ArrayList<Card> getMano(){
        return mano;
    }

    public void agregarCartasMano(Card carta){
        mano.add(carta);
    }

    public void descartarCartaMano(Card carta){
        //descartar la primera aparición de la carta
        mano.remove(carta);
    }
}
