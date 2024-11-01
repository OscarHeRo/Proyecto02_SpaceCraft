package com.chillizardinteractive.modelo.juegos;

public class Vida {
    private int vida;

    public Vida(int vida){
        this.vida = vida;
    }

    public int getVida(){
        return vida;
    }

    public void recibirDanio(int danio) {
        if (vida - danio < 0) {
            vida = 0;
        } else {
            vida -= danio;
        }
    }
}
