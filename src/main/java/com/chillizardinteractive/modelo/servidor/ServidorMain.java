package com.chillizardinteractive.servidor;

public class ServidorMain {
    public static void main(String[] args) {
        int port = 8080; // Puerto que utilizará el servidor
        ServidorSocket servidorSocket = new ServidorSocket(port);
        
        // Iniciar la espera de conexiones
        servidorSocket.esperarConexion();
    }
}
