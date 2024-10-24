package com.chillizardinteractive;

public class EstadoComenzarTurno implements EstadoTurno {
    private Turno turno;

    public EstadoComenzarTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void comenzarTurno() {
        System.out.println("Roba una carta y gana un cristal de maná.");
        // Lógica para robar carta y sumar cristal de maná.
        // Cambiar al siguiente estado: Jugar Cartas.
        turno.setEstado(new EstadoJugarCartas(turno));
    }

    @Override
    public void jugarCartas() {
        System.out.println("No se puede jugar cartas en este estado.");
    }

    @Override
    public void faseCombate() {
        System.out.println("No se puede combatir en este estado.");
    }

    @Override
    public void finalizarTurno() {
        System.out.println("No se puede finalizar el turno aún.");
    }
}
