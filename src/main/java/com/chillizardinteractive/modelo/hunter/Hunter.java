package com.chillizardinteractive.modelo.hunter;

public enum Hunter {

    //enumeraciones

    // gon
    GON("Gon Freecss", "Enhancer", NenTechnique.JAJANKEN ),
    // kurapika
    KURAPIKA("Kurapika", "Conjurer", NenTechnique.EMPEROR),
    // kurollo lucilfer
    KURORO("Kuroro Lucilfer", "Specialist", NenTechnique.LADRON ),
    // Neferpitou
    PITOU("Neferpitou", "Manipulator", NenTechnique.TERPSICORA),
    // hisoka
    HISOKA("Hisoka", "Specialist", NenTechnique.BUNGEE_GUM),
    // leorio
    LEORIO("Leorio", "Emitter", NenTechnique.PUNCH );

    // campos privados
    private final String name;
    private final String nenType;

    // Constructor
    Hunter(String name, String nenType, NenTechnique nenTechniques) {
        this.name = name;
        this.nenType = nenType;
    }

    // métodos públicos
    public String getName() {
        return name;
    }

    public String getNenType() {
        return nenType;
    }
    
}