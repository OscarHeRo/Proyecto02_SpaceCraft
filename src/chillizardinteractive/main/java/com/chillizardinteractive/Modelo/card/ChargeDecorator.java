package src.chillizardinteractive.main.java.com.chillizardinteractive.Modelo.card;

public class ChargeDecorator extends CardDecorator {
    public ChargeDecorator(Card card) {
        super(card);
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescription() + " (Charge)";
    }

    @Override
    public void playEffect() {
        decoratedCard.playEffect();
        System.out.println("Este minion puede atacar inmediatamente.");
    }
}