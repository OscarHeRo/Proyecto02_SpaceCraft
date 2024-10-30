package src.chillizardinteractive.main.java.com.chillizardinteractive.Modelo.state;

public class Turno {
    private EstadoTurno estadoActual;

    public void setEstado(EstadoTurno estado) {
        this.estadoActual = estado;
    }

    public void comenzarTurno() {
        estadoActual.comenzarTurno();
    }

    public void jugarCartas() {
        estadoActual.jugarCartas();
    }

    public void faseCombate() {
        estadoActual.faseCombate();
    }

    public void finalizarTurno() {
        estadoActual.finalizarTurno();
    }
}
