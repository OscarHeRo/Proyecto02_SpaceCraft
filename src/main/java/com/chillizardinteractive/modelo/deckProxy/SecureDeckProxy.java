package com.chillizardinteractive.modelo.deckProxy;

import java.util.List;

import com.chillizardinteractive.modelo.factory.Card;

public class SecureDeckProxy implements InterfazDeck {
    private Deck deck;
    private String authorizedUser;

    public SecureDeckProxy(String jsonFilePath, String authorizedUser) {
        // Cargar el mazo desde un archivo JSON y asignar al campo deck
        this.deck = loadDeckFromJson(jsonFilePath);
        this.authorizedUser = authorizedUser;
    }

    @Override
    public List<Card> getCards() {
        auditAccess();
        return deck.getCards();
    }

    public void agregarCarta(Card card) {
        if (!isAdminUser()) {
            throw new SecurityException("Solo el administrador puede agregar cartas.");
        }
        deck.getCards().add(card);
    }

    private boolean isAdminUser() {
        // Validar si el usuario actual tiene privilegios de administrador
        return "admin".equalsIgnoreCase(authorizedUser);
    }

    private Deck loadDeckFromJson(String jsonFilePath) {
        // Lógica para cargar un mazo desde un archivo JSON
        // Por ejemplo, leer el archivo, parsear el JSON y crear un objeto Deck
        return new Deck(); // Esto es solo un ejemplo. Debes implementar la lógica real de carga.
    }

    private void auditAccess() {
        // Registrar los accesos al mazo para auditoría
        System.out.println("Acceso al mazo registrado por: " + authorizedUser);
    }
}
