package src.chillizardinteractive.main.java.com.chillizardinteractive.Modelo.card;

public class TauntDecorator extends CardDecorator {
    public TauntDecorator(Card card) {
        super(card);
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescription() + " (Taunt)";
    }

    @Override
    public void playEffect() {
        decoratedCard.playEffect();
        System.out.println("Este minion debe ser atacado primero.");
    }
}