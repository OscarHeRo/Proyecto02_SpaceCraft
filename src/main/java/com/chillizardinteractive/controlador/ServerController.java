package com.chillizardinteractive.controlador;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.modelo.Player;
import com.chillizardinteractive.servidor.ServidorSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerController {
    private final ServidorSocket servidorSocket;
    private final GameController gameController;
    private final Map<String, Player> jugadores;

    public ServerController(ServidorSocket servidorSocket, GameController gameController) {
        this.servidorSocket = servidorSocket;
        this.gameController = gameController;
        this.jugadores = new HashMap<>();
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
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje recibido del cliente: " + inputLine);
                
                // Aquí se delega la lógica de los comandos al GameController según el mensaje recibido
                if (inputLine.startsWith("INICIAR_PARTIDA")) {
                    String[] partes = inputLine.split(":");
                    if (partes.length == 3) {
                        String jugador1Nombre = partes[1];
                        String jugador2Nombre = partes[2];
                        Player jugador1 = jugadores.get(jugador1Nombre);
                        Player jugador2 = jugadores.get(jugador2Nombre);
                        if (jugador1 != null && jugador2 != null) {
                            gameController.iniciarPartida(jugador1, jugador2);
                            out.println("Partida iniciada.");
                        } else {
                            out.println("Error: Uno o ambos jugadores no encontrados.");
                        }
                    } else {
                        out.println("Error: Formato incorrecto. Use INICIAR_PARTIDA:jugador1:jugador2");
                    }
                } else if (inputLine.startsWith("REGISTRAR_JUGADOR")) {
                    String[] partes = inputLine.split(":");
                    if (partes.length == 2) {
                        String jugadorNombre = partes[1];
                        Player jugador = new Player(jugadorNombre);
                        jugadores.put(jugadorNombre, jugador);
                        out.println("Jugador registrado: " + jugadorNombre);
                        verificarInicioPartida(out);
                    } else {
                        out.println("Error: Formato incorrecto. Use REGISTRAR_JUGADOR:jugadorNombre");
                    }
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

    private void verificarInicioPartida(PrintWriter out) {
        if (jugadores.size() == 2) {
            Player[] jugadoresArray = jugadores.values().toArray(new Player[0]);
            gameController.iniciarPartida(jugadoresArray[0], jugadoresArray[1]);
            out.println("Partida iniciada automáticamente.");
        }
    }
}
