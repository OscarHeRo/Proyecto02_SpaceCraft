package com.chillizardinteractive.modelo.state;

public class EstadoFinalizarTurno implements EstadoTurno {
    private Turno turno;

    public EstadoFinalizarTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void comenzarTurno() {
        System.out.println("El turno ya ha comenzado.");
    }

    @Override
    public void jugarCartas() {
        System.out.println("No puedes jugar cartas ahora.");
    }

    @Override
    public void faseCombate() {
        System.out.println("El combate ya ha terminado.");
    }

    @Override
    public void finalizarTurno() {
        System.out.println("Finalizando el turno.");
        // Lógica para finalizar el turno y pasar al siguiente jugador.
        // Cambiar de nuevo al estado de Comenzar Turno.
        turno.setEstado(new EstadoComenzarTurno(turno));
    }
}
