package com.chillizardinteractive.modelo.servidor;

import com.chillizardinteractive.controlador.AdminController;
import com.chillizardinteractive.modelo.cardFactory.CardFactory;

public class Servidor {
    public void modificarMazo(AdminController adminController) {
        // Lógica para modificar el mazo usando AdminController
        adminController.construirMazo();
    }

    public void agregarCartasNuevas(CardFactory cardFactory) {
        // Lógica para agregar cartas nuevas usando CardFactory
        System.out.println("Agregando nuevas cartas al mazo...");
        // Ejemplo de uso del factory para crear una carta
        cardFactory.createCard(null); // Deberías pasar un JSONObject válido para crear una carta
    }
}
