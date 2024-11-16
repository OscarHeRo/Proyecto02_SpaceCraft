package com.chillizardinteractive.controllador;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.servidor.ServidorSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    private final ServidorSocket servidorSocket;
    private final GameController gameController;

    public ServerController(ServidorSocket servidorSocket, GameController gameController) {
        this.servidorSocket = servidorSocket;
        this.gameController = gameController;
    }

    public void manejarConexionCliente() {
        try (ServerSocket serverSocket = new ServerSocket(servidorSocket.getPort())) {
            System.out.println("Esperando conexiones de clientes...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                new Thread(() -> manejarMensajes(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void manejarMensajes(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje recibido del cliente: " + inputLine);
                
                // Aquí se delega la lógica de los comandos al GameController según el mensaje recibido
                if ("INICIAR_PARTIDA".equalsIgnoreCase(inputLine)) {
                    gameController.iniciarPartida(null, null); // Aquí puedes establecer los jugadores
                    out.println("Partida iniciada.");
                } else if ("ROBAR_CARTA".equalsIgnoreCase(inputLine)) {
                    // Lógica para robar carta usando gameController
                    out.println("Carta robada.");
                } else if ("TERMINAR_TURNO".equalsIgnoreCase(inputLine)) {
                    gameController.terminarTurno();
                    out.println("Turno terminado.");
                } else {
                    out.println("Comando no reconocido.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
