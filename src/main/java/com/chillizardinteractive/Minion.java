package com.chillizardinteractive;

public interface Minion extends Card {
    int getAttack();
    int getDefense();
    void playEffect();  // Cada Minion tendrá un efecto que se ejecuta al jugar
}
