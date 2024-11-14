package com.chillizardinteractive.broker;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.controlador.PlayerController;
import com.chillizardinteractive.modelo.hunter.Hunter;

import java.io.PrintWriter;

public class MessageBroker {
    private GameController gameController;
    private PlayerController playerController;

    public MessageBroker(GameController gameController, PlayerController playerController) {
        this.gameController = gameController;
        this.playerController = playerController;
    }

    public void procesarMensaje(String mensaje, PrintWriter out) {
        System.out.println("Procesando mensaje: " + mensaje); // Mensaje de depuración
        String[] partes = mensaje.split(":");
        String accion = partes[0];

        try {
            switch (accion) {
                case "nombre":
                    if (partes.length >= 2) {
                        playerController.procesarNombre(partes[1], out);
                    } else {
                        out.println("Error: Falta información para procesar el nombre.");
                    }
                    break;

                case "hunter":
                    if (partes.length >= 3) {
                        String nombreJugador = partes[1];
                        int hunterNumber = Integer.parseInt(partes[2]);
                        playerController.procesarHunter(nombreJugador, hunterNumber, out);
                    } else {
                        out.println("Error: Formato incorrecto para seleccionar Hunter.");
                    }
                    break;

                case "listo":
                    if (partes.length >= 2) {
                        String nombreJugador = partes[1];
                        playerController.procesarListo(nombreJugador, out, gameController::iniciarJuego);
                    } else {
                        out.println("Error: Formato incorrecto para procesar jugador listo.");
                    }
                    break;

                case "movimiento":
                    if (partes.length >= 3) {
                        String jugador = partes[1];
                        String accionMovimiento = partes[2];
                        gameController.procesarMovimiento(jugador, accionMovimiento);
                    } else {
                        out.println("Error: Formato incorrecto para procesar movimiento.");
                    }
                    break;

                default:
                    out.println("Error: Acción no reconocida.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            out.println("Error: Número de formato incorrecto.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            out.println("Error al procesar el mensaje.");
        }
    }
}
