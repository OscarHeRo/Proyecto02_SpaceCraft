package src.chillizardinteractive.main.java.com.chillizardinteractive.Modelo.hunter;


public enum NenTechnique {

    // Enumeraciones (Hatsu)

    // Gon
    JAJANKEN("Jajanken", "Gon concentra su aura en su puño y lo lanza."),
    // Kurapika
    EMPEROR("Emperor Time", "Kurapika se vuelve especialista."),
    // Kuroro Lucilfer
    LADRON("Skill Hunter", "Kuroro roba habilidades."),
    // Neferpitou
    TERPSICORA("Puppet Master", "Neferpitou controla a un esbirro."),
    // Hisoka
    BUNGEE_GUM("Bungee Gum", "Hisoka ataca con goma elástica."),
    // Leorio
    PUNCH("Warped Punch",
"Leorio concentra su aura en un puño que se extiende a través de un portal para golpear a su objetivo. Ignora Taunts.");

    // Campos privados
    private final String hatsuName;
    private final String description;

    // Constructor
    NenTechnique(String hatsuName, String description) {
        this.hatsuName = hatsuName;
        this.description = description;
    }

    // Métodos getter
    public String getName() {
        return hatsuName; // Cambiado para devolver hatsuName
    }

    public String getDescription() {
        return description;
    }

    // Sobrescribir toString para una representación más clara
    @Override
    public String toString() {
        return hatsuName + ": " + description;
    }

    // Habilidades específicas (opcional)
    public void efecto() {
        System.out.println(hatsuName + " lanza su ataque especial!");
    }


}