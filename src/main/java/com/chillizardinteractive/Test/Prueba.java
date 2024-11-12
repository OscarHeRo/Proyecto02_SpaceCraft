package com.chillizardinteractive.Test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.chillizardinteractive.DueloDeCazadores;
import com.chillizardinteractive.modelo.player.Player;

import static org.junit.Assert.*;

/**
 * Clase donde se realiza la prueba unitaria
 * 
 * @author Emilio Durán Tapia
 * @author 
 */
public class Prueba extends DueloDeCazadores{
    /**
     * Test
     */
    @Test
    void prueba1(){
        System.out.println("Prueba: ");
        Player player1 = new Player("Jugador 1");
        Player player2 = new Player("Jugador 2");
    }

    @Test
    void prueba2(){
        System.out.println("Prueba: ");
        Player player1 = new Player("Jugador 1");
        Player player2 = new Player("Jugador 2");
    }

    @Test
    void prueba3(){
        System.out.println("Prueba: ");
    }
}