package com.chillizardinteractive.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJuego {
    private String nombre;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClienteJuego(String nombre) {
        this.nombre = nombre;
    }

    public void conectarAlServidor(String hostname, int port) {
        try {
            socket = new Socket(hostname, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Conectado al servidor en " + hostname + ":" + port);
            enviarNombre();
            seleccionarHunter();
            escucharServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarNombre() {
        out.println("nombre:" + nombre);
    }

    public void seleccionarHunter() {
        Scanner scanner = new Scanner(System.in);
        int hunterNumber;
        do {
            System.out.println("Seleccione un Hunter (1-6):");
            System.out.println("1. GON\n2. KURAPIKA\n3. KURORO\n4. PITOU\n5. HISOKA\n6. LEORIO");
            System.out.print("Ingrese el número correspondiente: ");
            hunterNumber = scanner.nextInt();
            if (hunterNumber >= 1 && hunterNumber <= 6) {
                // Enviar el nombre del jugador y el número del Hunter seleccionado
                out.println("hunter:" + nombre + ":" + hunterNumber);
                break;
            } else {
                System.out.println("Número de Hunter inválido. Seleccione un número del 1 al 6.");
            }
        } while (true);
    }

    public void escucharServidor() {
        new Thread(() -> {
            try {
                String mensaje;
                while ((mensaje = in.readLine()) != null) {
                    System.out.println(mensaje);

                    // Detectar señal de inicio del juego
                    if (mensaje.equals("comenzar")) {
                        System.out.println("¡El juego ha comenzado!");
                        realizarAccion();
                    } else if (mensaje.contains("Iniciando turno del jugador")) {
                        realizarAccion();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void realizarAccion() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una acción: 1. Colocar carta, 2. Atacar, 3. Terminar turno");
            int accion = scanner.nextInt();
            if (accion == 1) {
                System.out.print("Ingrese el índice de la carta: ");
                int cartaIndex = scanner.nextInt();
                System.out.print("Ingrese la posición en el tablero: ");
                int posicion = scanner.nextInt();
                enviarMovimiento("colocarCarta:" + cartaIndex + ":" + posicion);
                break;
            } else if (accion == 2) {
                System.out.print("Ingrese el índice del atacante: ");
                int atacanteIndex = scanner.nextInt();
                System.out.print("Ingrese el índice del objetivo: ");
                int objetivoIndex = scanner.nextInt();
                enviarMovimiento("atacar:" + atacanteIndex + ":" + objetivoIndex);
                break;
            } else if (accion == 3) {
                enviarMovimiento("terminarTurno");
                break;
            } else {
                System.out.println("Acción no válida. Intente de nuevo.");
            }
        }
    }

    public void enviarMovimiento(String tipoMovimiento) {
        out.println(tipoMovimiento);
    }

    public void enviarListo() {
        // Enviar el mensaje "listo" con el nombre del jugador para que sea reconocido correctamente
        out.println("listo:" + nombre);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        ClienteJuego cliente = new ClienteJuego(nombre);
        cliente.conectarAlServidor("localhost", 8080);

        System.out.println("Presione Enter cuando esté listo...");
        scanner.nextLine();
        cliente.enviarListo(); // Cambiado para enviar el nombre del jugador junto con el mensaje "listo"
    }
}
