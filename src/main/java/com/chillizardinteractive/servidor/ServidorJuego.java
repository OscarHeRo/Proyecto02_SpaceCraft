package com.chillizardinteractive.servidor;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.controlador.PlayerController;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServidorJuego {
    private static final int PUERTO = 8080;
    private List<PrintWriter> clientes = new CopyOnWriteArrayList<>();
    private GameController gameController;
    private PlayerController playerController;
    private GameContext context;
    private GameView view;

    public static void main(String[] args) {
        new ServidorJuego().iniciarServidor();
    }

    public ServidorJuego() {
        view = new GameView(this);
        List<Player> jugadores = new ArrayList<>();
        context = new GameContext(jugadores, view); // Usar una lista de jugadores dinámica
        this.gameController = new GameController(context, view);
        this.playerController = new PlayerController(context.getPlayers(), gameController, view);
    }

    public void iniciarServidor() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clientSocket.getInetAddress());
                new Thread(new ManejadorCliente(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ManejadorCliente implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ManejadorCliente(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                synchronized (clientes) {
                    clientes.add(out);
                }

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Mensaje del cliente: " + inputLine);
                    procesarMensaje(inputLine, out);
                }
            } catch (IOException e) {
                System.out.println("Error en la conexión con el cliente: " + clientSocket.getInetAddress());
                e.printStackTrace();
            } finally {
                desconectarCliente();
            }
        }

        private void procesarMensaje(String mensaje, PrintWriter out) {
            String[] partes = mensaje.split(":");
            String accion = partes[0];

            switch (accion) {
                case "nombre":
                    if (partes.length == 2) {
                        playerController.procesarNombre(partes[1], out);
                    } else {
                        out.println("Error: Formato incorrecto. Use nombre:jugadorNombre");
                    }
                    break;
                case "hunter":
                    if (partes.length == 3) {
                        String nombreJugador = partes[1];
                        int hunterNumber = Integer.parseInt(partes[2]);
                        playerController.procesarHunter(nombreJugador, hunterNumber, out);
                    } else {
                        out.println("Error: Formato incorrecto. Use hunter:jugadorNombre:hunterNumber");
                    }
                    break;
                case "listo":
                    if (partes.length == 2) {
                        String nombreJugador = partes[1];
                        playerController.procesarListo(nombreJugador, out, gameController::iniciarJuego);
                    } else {
                        out.println("Error: Formato incorrecto. Use listo:jugadorNombre");
                    }
                    break;
                case "movimiento":
                    if (partes.length >= 3) {
                        String jugador = partes[1];
                        String accionMovimiento = partes[2];
                        gameController.procesarMovimiento(jugador, accionMovimiento);
                    } else {
                        out.println("Error: Formato incorrecto. Use movimiento:jugador:accion");
                    }
                    break;
                default:
                    out.println("Error: Acción no reconocida.");
            }
        }

        private void desconectarCliente() {
            try {
                if (out != null) {
                    synchronized (clientes) {
                        clientes.remove(out);
                    }
                }
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
                System.out.println("Cliente desconectado: " + clientSocket.getInetAddress());
                notificarDesconexion();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void notificarDesconexion() {
            for (PrintWriter cliente : clientes) {
                cliente.println("Un jugador se ha desconectado.");
            }
            if (clientes.size() < 2) {
                System.out.println("No hay suficientes jugadores para continuar. Finalizando el juego...");
                gameController.finalizarJuego();
            }
        }
    }

    public void notificarInicioJuego() {
        for (PrintWriter cliente : clientes) {
            cliente.println("comenzar");
        }
    }

    public void notificarMensajePrivado(String jugador, String mensaje) {
        for (PrintWriter cliente : clientes) {
            cliente.println("[Mensaje Privado para " + jugador + "] " + mensaje);
        }
    }

    public void notificarMensajePublico(String mensaje) {
        for (PrintWriter cliente : clientes) {
            cliente.println("[Mensaje Público] " + mensaje);
        }
    }

    public List<PrintWriter> getClientes() {
        return clientes;
    }
}