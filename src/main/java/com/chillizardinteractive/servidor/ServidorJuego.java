package com.chillizardinteractive.servidor;

import com.chillizardinteractive.broker.MessageBroker;
import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.controlador.PlayerController;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServidorJuego {
    private static final int PUERTO = 8080;
    private Map<Player, PrintWriter> clienteMap = new ConcurrentHashMap<>(); // Mapeo de jugadores a clientes para enviar mensajes
    private GameController gameController;
    private PlayerController playerController;
    private MessageBroker messageBroker;
    private GameContext context;

    public static void main(String[] args) {
        new ServidorJuego().iniciarServidor();
    }

    public ServidorJuego() {
        GameView view = new GameView();
        List<Player> jugadores = new ArrayList<>();
        this.context = new GameContext(jugadores, view); // Usar una lista de jugadores dinámica
        this.gameController = new GameController(context, this); // Pasar una referencia del servidor al controlador del juego
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

    public void enviarMensajeAlJugador(Player jugador, String mensaje) {
        PrintWriter clienteOut = clienteMap.get(jugador);
        if (clienteOut != null) {
            clienteOut.println(mensaje);
        }
    }

    private class ManejadorCliente implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private Player jugador;

        public ManejadorCliente(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Mensaje del cliente: " + inputLine);
                    procesarMensaje(inputLine);
                }
            } catch (IOException e) {
                System.out.println("Error en la conexión con el cliente: " + clientSocket.getInetAddress());
                e.printStackTrace();
            } finally {
                desconectarCliente();
            }
        }

        private void procesarMensaje(String mensaje) {
            String[] partes = mensaje.split(":");
            String accion = partes[0];

            switch (accion) {
                case "nombre":
                    String nombre = partes[1];
                    jugador = new Player(nombre);
                    context.getPlayers().add(jugador);
                    clienteMap.put(jugador, out); // Mapear el jugador con su `PrintWriter`
                    out.println("Nombre recibido: " + nombre);
                    break;
                case "hunter":
                    int hunterNumber = Integer.parseInt(partes[2]);
                    playerController.procesarHunter(jugador.getName(), hunterNumber, out);
                    break;
                case "listo":
                    playerController.procesarListo(jugador.getName(), out, gameController::iniciarJuego);
                    break;
                default:
                    messageBroker.procesarMensaje(mensaje, out);
            }
        }

        private void desconectarCliente() {
            try {
                if (jugador != null) {
                    clienteMap.remove(jugador);
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
            for (PrintWriter cliente : clienteMap.values()) {
                cliente.println("Un jugador se ha desconectado.");
            }
            // Aquí podrías decidir si finalizar el juego o adaptar el flujo
            if (clienteMap.size() < 2) {
                System.out.println("No hay suficientes jugadores para continuar. Finalizando el juego...");
                gameController.finalizarJuego();
            }
        }
    }
}
