package com.chillizardinteractive.modelo.juegos;

import java.util.Random;

public class Moneda {
    public static String lanzarMoneda() {
        Random random = new Random();
        int resultado = random.nextInt(2); // Genera un número aleatorio entre 0 y 1

        if (resultado == 0) {
            return "cara";
        } else {
            return "cruz";
        }
    }
}
