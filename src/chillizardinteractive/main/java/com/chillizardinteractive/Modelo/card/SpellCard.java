package src.chillizardinteractive.main.java.com.chillizardinteractive.Modelo.card;

public class SpellCard extends Card {
    private final String effect;

    public SpellCard(int nenCost, String description, String effect, Rarity rarity) {
        super(description, nenCost, rarity);
        this.effect = effect;
    }

    @Override
    public void playEffect() {
        System.out.println(getDescription() + ": " + effect);
    }
}