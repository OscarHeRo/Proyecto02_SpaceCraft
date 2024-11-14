package com.chillizardinteractive.modelo.hunter;

public enum Hunter {
    GON(1, "Gon Freecss", "Enhancer", NenTechnique.JAJANKEN),
    KURAPIKA(2, "Kurapika", "Conjurer", NenTechnique.EMPEROR),
    KURORO(3, "Kuroro Lucilfer", "Specialist", NenTechnique.LADRON),
    PITOU(4, "Neferpitou", "Manipulator", NenTechnique.TERPSICORA),
    HISOKA(5, "Hisoka", "Specialist", NenTechnique.BUNGEE_GUM),
    LEORIO(6, "Leorio", "Emitter", NenTechnique.PUNCH);

    private final int number;
    private final String name;
    private final String nenType;

    Hunter(int number, String name, String nenType, NenTechnique nenTechniques) {
        this.number = number;
        this.name = name;
        this.nenType = nenType;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getNenType() {
        return nenType;
    }

    public static Hunter getHunterByNumber(int number) {
        for (Hunter hunter : values()) {
            if (hunter.getNumber() == number) {
                return hunter;
            }
        }
        throw new IllegalArgumentException("Número de Hunter inválido: " + number);
    }
}
