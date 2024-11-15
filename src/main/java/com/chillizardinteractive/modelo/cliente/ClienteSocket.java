package com.chillizardinteractive.modelo.cliente;

// ClienteSocket.java
public class ClienteSocket {
    private String ip;
    private int port;

    public ClienteSocket(String ip, int port) {
        // Configurar la conexión al servidor
    }

    public void enviarMensaje(String mensaje) {
        // Lógica para enviar un mensaje al servidor
    }

    public String recibirMensaje() {
        // Lógica para recibir mensaje del servidor
        return "";
    }

    public void cerrarConexion() {
        // Lógica para cerrar la conexión
    }
}

// Cliente.java
public class Cliente {
    public void listoParaPartida() {
        // Implementación para que el cliente esté listo
    }

    public void introducirNombreYElegirCazador() {
        // Introducir nombre y elegir cazador
    }

    public void robarCarta() {
        // Robar carta
    }

    public void jugarCarta() {
        // Jugar una carta
    }

    public void atacarConMinion() {
        // Atacar con minion
    }

    public void verCartasDisponibles() {
        // Ver las cartas disponibles
    }

    public void terminarTurno() {
        // Terminar turno
    }
}
