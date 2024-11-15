package com.chillizardinteractive.controlador;

import com.chillizardinteractive.modelo.builder.DeckManager;
import com.chillizardinteractive.modelo.cardFactory.CardBuilder;
import com.chillizardinteractive.vista.VistaConsola;
import com.chillizardinteractive.modelo.Card;

public class AdminController {
    private CardBuilder cardBuilder;
    private DeckManager deckManager;
    private VistaConsola vista;

    public AdminController(CardBuilder cardBuilder, DeckManager deckManager, VistaConsola vista) {
        this.cardBuilder = cardBuilder;
        this.deckManager = deckManager;
        this.vista = vista;
    }

    // Crear una nueva carta usando CardBuilder
    public void crearCarta() {
        vista.mostrarMensaje("Creando una nueva carta...");
        String nombre = vista.solicitarTexto("Nombre de la carta: ");
        int costo = vista.solicitarNumero("Costo de la carta: ");
        int ataque = vista.solicitarNumero("Ataque (0 si es un hechizo): ");
        int vida = vista.solicitarNumero("Vida (0 si es un hechizo): ");
        String tipo = vista.solicitarTexto("Tipo (MINION, SPELL, WEAPON): ");

        Card carta = cardBuilder
            .setNombre(nombre)
            .setCosto(costo)
            .setAtaque(ataque)
            .setVida(vida)
            .setTipo(tipo)
            .build();

        vista.mostrarMensaje("Carta creada: " + carta.getDescription());
        deckManager.agregarCartaDisponible(carta);
    }

    // Construir un nuevo mazo carta por carta
    public void construirMazo() {
        vista.mostrarMensaje("Construyendo un nuevo mazo...");
        Deck deck = deckManager.crearNuevoMazo();

        while (true) {
            vista.mostrarMensaje("1. Agregar carta al mazo");
            vista.mostrarMensaje("2. Finalizar mazo");
            int opcion = vista.solicitarNumero("Selecciona una opción: ");

            if (opcion == 1) {
                String cartaId = vista.solicitarTexto("ID de la carta a agregar: ");
                Card carta = deckManager.obtenerCartaPorId(cartaId);
                if (carta != null) {
                    deck.agregarCarta(carta);
                    vista.mostrarMensaje("Carta agregada: " + carta.getDescription());
                } else {
                    vista.mostrarMensaje("Carta no encontrada.");
                }
            } else if (opcion == 2) {
                deckManager.guardarMazo(deck);
                vista.mostrarMensaje("Mazo guardado exitosamente.");
                break;
            } else {
                vista.mostrarMensaje("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}
