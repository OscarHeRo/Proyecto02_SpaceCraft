package com.chillizardinteractive.modelo.state;

public class EstadoJugarCartas implements EstadoTurno {
    private Turno turno;

    public EstadoJugarCartas(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void comenzarTurno() {
        System.out.println("El turno ya ha comenzado.");
    }

    @Override
    public void jugarCartas() {
        System.out.println("Puedes jugar cartas, invocar esbirros o usar habilidades.");
        // Lógica para jugar cartas y usar maná.
        // Luego pasar a la fase de combate.
        turno.setEstado(new EstadoFaseCombate(turno));
    }

    @Override
    public void faseCombate() {
        System.out.println("No puedes combatir hasta que juegues cartas.");
    }

    @Override
    public void finalizarTurno() {
        System.out.println("Debes pasar por la fase de combate antes de finalizar.");
    }
}
