package com.chillizardinteractive.servidor;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.controlador.GestorDeTurnos;
import com.chillizardinteractive.modelo.deck.Deck;
import com.chillizardinteractive.modelo.gameState.GameContext;
import com.chillizardinteractive.modelo.player.Player;
import com.chillizardinteractive.vista.GameView;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorJuego {
    private static List<Player> jugadores = new ArrayList<>();
    private static GameController gameController;
    private static GameView view = new GameView();
    private static GestorDeTurnos gestorDeTurnos;
    private static boolean juegoIniciado = false;
    private static List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Manejar al cliente en un hilo separado
                new Thread(new ClienteHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void iniciarJuego() {
        if (jugadores.size() == 2 && !juegoIniciado) {
            juegoIniciado = true;
            gestorDeTurnos = new GestorDeTurnos(jugadores);
            GameContext context = new GameContext(jugadores.get(0), jugadores.get(1), view);
            gameController = new GameController(context, view);
            gameController.iniciarJuego();
            gameController.lanzarMoneda();
            cicloDelJuego();
        }
    }

    private static void cicloDelJuego() {
        do {
            Player jugadorActual = gestorDeTurnos.obtenerJugadorEnTurno();
            enviarActualizacion("[Mensaje Público] Iniciando turno del jugador: " + jugadorActual.getName());
            gameController.iniciarTurno();

            // Esperar la acción del jugador actual
            while (true) {
                try {
                    Thread.sleep(1000); // Espera para permitir que el cliente envíe su acción
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Verificar si el jugador terminó su turno
                if (!gestorDeTurnos.esTurnoDe(jugadorActual)) {
                    break;
                }
            }

            // Revisar si alguno de los jugadores ha perdido
            if (gameController.getContext().getPlayer1().getHealth() <= 0) {
                enviarActualizacion("[Mensaje Público] El jugador " + gameController.getContext().getPlayer2().getName() + " ha ganado la partida.");
                                break;
                            } else if (gameController.getContext().getPlayer2().getHealth() <= 0) {
                                enviarActualizacion("[Mensaje Público] El jugador " + gameController.getContext().getPlayer1().getName() + " ha ganado la partida.");
                                break;
                            }
                
                            // Avanzar al siguiente jugador
                            gestorDeTurnos.avanzarTurno();
                        } while (true);
                
                        // Finalizar el juego cuando haya un ganador
                        gameController.finalizarJuego();
                        enviarActualizacion("[Mensaje Público] El juego ha terminado.");
                    }
                
                    private static void enviarActualizacion(String string) {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException("Unimplemented method 'enviarActualizacion'");
                    }
                
                    static class ClienteHandler implements Runnable {
        private Socket clientSocket;

        public ClienteHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                clientes.add(out);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Mensaje del cliente: " + inputLine);
                    procesarMensaje(inputLine, out);
                    if ("bye".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void procesarMensaje(String mensaje, PrintWriter out) {
            String[] partes = mensaje.split(":");
            String accion = partes[0];
            String jugador = partes[1];

            switch (accion) {
                case "nombre":
                    Player player = new Player(jugador);
                    jugadores.add(player);
                    out.println("Nombre recibido: " + jugador);
                    if (jugadores.size() == 2) {
                        iniciarJuego();
                    }
                    break;
                case "colocarCarta":
                case "atacar":
                case "terminarTurno":
                    Player jugadorActual = jugadores.stream()
                            .filter(j -> j.getName().equals(jugador))
                            .findFirst()
                            .orElse(null);
                    if (jugadorActual == null) {
                        out.println("Jugador no encontrado.");
                        return;
                    }
                    if (!gestorDeTurnos.esTurnoDe(jugadorActual)) {
                        out.println("No es tu turno.");
                        return;
                    }
                    switch (accion) {
                        case "colocarCarta":
                            gameController.colocarCartaEnTablero(jugadorActual, gameController.getContext().getBoard());
                            enviarActualizacion("Carta colocada.");
                            gestorDeTurnos.avanzarTurno();
                            break;
                        case "atacar":
                            gameController.atacarConMinion(jugadorActual, gameController.getContext().getBoard());
                            enviarActualizacion("Ataque realizado.");
                            gestorDeTurnos.avanzarTurno();
                            break;
                        case "terminarTurno":
                            gameController.terminarTurno();
                            enviarActualizacion("Turno terminado.");
                            gestorDeTurnos.avanzarTurno();
                            break;
                    }
                    break;
                default:
                    out.println("Acción no válida.");
                    break;
            }
        }

        private void enviarActualizacion(String mensaje) {
            for (PrintWriter cliente : clientes) {
                cliente.println(mensaje);
            }
        }
    }
}