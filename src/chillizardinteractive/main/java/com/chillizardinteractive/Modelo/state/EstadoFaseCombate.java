package src.chillizardinteractive.main.java.com.chillizardinteractive.Modelo.state;

public class EstadoFaseCombate implements EstadoTurno {
    private Turno turno;

    public EstadoFaseCombate(Turno turno) {
        this.turno = turno;
    }

    @Override
    public void comenzarTurno() {
        System.out.println("El turno ya ha comenzado.");
    }

    @Override
    public void jugarCartas() {
        System.out.println("No puedes jugar más cartas ahora.");
    }

    @Override
    public void faseCombate() {
        System.out.println("Realizando ataques.");
        // Lógica para realizar ataques de esbirros y héroe.
        // Luego de la fase de ataque, pasar al siguiente estado.
        turno.setEstado(new EstadoFinalizarTurno(turno));
    }

    @Override
    public void finalizarTurno() {
        System.out.println("No se puede finalizar sin antes realizar el combate.");
    }
}
