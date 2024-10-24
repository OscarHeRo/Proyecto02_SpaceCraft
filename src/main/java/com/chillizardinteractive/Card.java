package com.chillizardinteractive;

public interface Card {
    int getNenCost();             // Costo de nen para jugar la carta
    String getDescription();      // Descripción de la carta
    Rarity getRarity();           // Rareza de la carta
    void playEffect();            // Efecto al jugar la carta
}
