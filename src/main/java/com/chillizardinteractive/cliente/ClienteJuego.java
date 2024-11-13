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
            escucharServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarNombre() {
        out.println("nombre:" + nombre);
    }

    public void enviarMovimiento(String tipoMovimiento) {
        out.println(tipoMovimiento + ":" + nombre);
    }

    public void escucharServidor() {
        new Thread(() -> {
            try {
                String mensaje;
                while ((mensaje = in.readLine()) != null) {
                    System.out.println(mensaje);

                    // Procesar mensajes recibidos del servidor
                    if (mensaje.contains("[Mensaje Privado]")) {
                        if (mensaje.contains("Iniciando turno del jugador actual")) {
                            realizarAccion();
                        }
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
                enviarMovimiento("colocarCarta");
                break;
            } else if (accion == 2) {
                enviarMovimiento("atacar");
                break;
            } else if (accion == 3) {
                enviarMovimiento("terminarTurno");
                break;
            } else {
                System.out.println("Acción no válida. Intente de nuevo.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        ClienteJuego cliente = new ClienteJuego(nombre);
        cliente.conectarAlServidor("localhost", 8080);
    }
}
