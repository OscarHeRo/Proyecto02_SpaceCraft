package com.chillizardinteractive.cliente;

import java.util.Scanner;

public class Cliente {
    private ClienteSocket clienteSocket;
    private Scanner scanner;

    public Cliente() {
        this.clienteSocket = new ClienteSocket("localhost", 8080);
        this.scanner = new Scanner(System.in);
    }

    public void listoParaPartida() {
        // Implementación para que el cliente esté listo
        clienteSocket.enviarMensaje("LISTO");
    }

    public void introducirNombreYElegirCazador() {
        System.out.println("Bienvenido a Duelo de Cazadores");
        System.out.print("Escribe tu nombre: ");
        String nombre = scanner.nextLine();
        clienteSocket.enviarMensaje("REGISTRAR_JUGADOR:" + nombre);

        System.out.println("Elige tu cazador:");
        System.out.println("1. Gon Freecss");
        System.out.println("2. Kurapika");
        System.out.println("3. Kuroro Lucilfer");
        System.out.println("4. Neferpitou");
        System.out.println("5. Hisoka");
        System.out.println("6. Leorio");

        int cazadorNumero = 0;
        while (cazadorNumero < 1 || cazadorNumero > 6) {
            try {
                System.out.print("Ingresa un número del 1 al 6: ");
                cazadorNumero = Integer.parseInt(scanner.nextLine());
                if (cazadorNumero < 1 || cazadorNumero > 6) {
                    System.out.println("Número inválido. Por favor, ingresa un número del 1 al 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número del 1 al 6.");
            }
        }

        clienteSocket.enviarMensaje("ELEGIR_CAZADOR:" + nombre + ":" + cazadorNumero);
    }

    public void robarCarta() {
        // Robar carta
        clienteSocket.enviarMensaje("ROBAR_CARTA");
    }

    public void jugarCarta() {
        // Jugar una carta
        clienteSocket.enviarMensaje("JUGAR_CARTA");
    }

    public void atacarConMinion() {
        // Atacar con minion
        clienteSocket.enviarMensaje("ATACAR_CON_MINION");
    }

    public void verCartasDisponibles() {
        // Ver las cartas disponibles
        clienteSocket.enviarMensaje("VER_CARTAS_DISPONIBLES");
    }

    public void terminarTurno() {
        // Terminar turno
        clienteSocket.enviarMensaje("TERMINAR_TURNO");
    }
}