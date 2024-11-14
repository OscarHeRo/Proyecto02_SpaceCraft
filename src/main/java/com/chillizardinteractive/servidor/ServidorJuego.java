package com.chillizardinteractive.servidor;

import com.chillizardinteractive.broker.MessageBroker;
import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.controlador.PlayerController;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServidorJuego {
    private static final int PUERTO = 8080;
    private List<PrintWriter> clientes = new CopyOnWriteArrayList<>(); // CopyOnWriteArrayList para evitar ConcurrentModificationException
    private GameController gameController;
    private PlayerController playerController;
    private MessageBroker messageBroker;

    public static void main(String[] args) {
        new ServidorJuego().iniciarServidor();
    }

    public ServidorJuego() {
        Player player1 = new Player("Jugador1");
        Player player2 = new Player("Jugador2");
        GameView view = new GameView();
        GameContext context = new GameContext(player1, player2, view);
        this.gameController = new GameController(context);
        this.playerController = new PlayerController(context.getPlayers(), gameController, view);
        this.messageBroker = new MessageBroker(gameController, playerController);
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
                    messageBroker.procesarMensaje(inputLine, out);
                }
            } catch (IOException e) {
                System.out.println("Error en la conexión con el cliente: " + clientSocket.getInetAddress());
                e.printStackTrace();
            } finally {
                desconectarCliente();
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
            // Aquí podrías decidir si finalizar el juego o adaptar el flujo
            if (clientes.size() < 2) {
                System.out.println("No hay suficientes jugadores para continuar. Finalizando el juego...");
                gameController.finalizarJuego();
            }
        }
    }
}
