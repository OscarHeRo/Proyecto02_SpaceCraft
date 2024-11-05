package com.chillizardinteractive.modelo.card;

public enum Rarity {
    COMMON("rgba(255, 255, 255, 0.43)"),  // Común (gema blanca)
    RARE("rgba(0, 112, 221, 0.43)"),      // Rara (gema azul)
    EPIC("rgba(163, 53, 238, 0.43)"),     // Épica (gema violeta)
    LEGENDARY("rgba(255, 128, 0, 0.43)"); // Legendaria (gema naranja)

    private final String cssColor;

    Rarity(String cssColor) {
        this.cssColor = cssColor;
    }

    public String getCssColor() {
        return cssColor;
    }
}
