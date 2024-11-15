package com.chillizardinteractive.servidor;

import java.io.*;
import java.net.*;

public class ServidorMain {
    private int port;
    private ServerSocket serverSocket;

    public ServidorMain(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto: " + port);
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    public void esperarConexion() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Crear un nuevo hilo para gestionar cada cliente de manera independiente
                new Thread(new ClienteHandler(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error al esperar conexión: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión del servidor: " + e.getMessage());
        }
    }
}

// Clase ClienteHandler para gestionar las conexiones con los clientes
class ClienteHandler implements Runnable {
    private Socket socket;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            String mensaje;
            while ((mensaje = input.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                output.println("Respuesta del servidor: " + mensaje);
            }
        } catch (IOException e) {
            System.err.println("Error en la conexión con el cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
            }
        }
    }
}
