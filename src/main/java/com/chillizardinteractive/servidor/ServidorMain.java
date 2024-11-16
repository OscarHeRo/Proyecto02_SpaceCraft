package com.chillizardinteractive.servidor;

import com.chillizardinteractive.controlador.GameController;
import com.chillizardinteractive.controlador.ServerController;
import com.chillizardinteractive.controlador.GameContext;
import com.chillizardinteractive.vista.GameView;

import java.util.Scanner;

public class ServidorMain {
    public static void main(String[] args) {
        System.out.println("Escoge tu opción");
        System.out.println("1. Inicia servidor");
        System.out.println("2. Crear mazo");
        Scanner scan = new Scanner(System.in);
        int opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                iniciarServidor();
                break;
            case 2:
                crearMazo();
                break;
            default:
                System.out.println("Escriba un número correcto");
                break;
        }
        scan.close();
    }

    private static void iniciarServidor() {
        GameView gameView = new GameView();
        GameContext gameContext = new GameContext(gameView);
        GameController gameController = new GameController(gameContext, gameView);
        ServerController serverController = new ServerController(new ServidorSocket(8080), gameController);
        serverController.manejarConexionCliente();
    }

    private static void crearMazo() {
        // Lógica para crear un mazo
        System.out.println("Funcionalidad para crear un mazo aún no implementada.");
    }
}
