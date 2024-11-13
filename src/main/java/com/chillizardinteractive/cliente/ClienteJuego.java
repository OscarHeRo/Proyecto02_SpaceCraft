package com.chillizardinteractive.cliente;

import java.io.*;
import java.net.*;
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
        try {
            System.out.println("Respuesta del servidor: " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarMovimiento(String tipoMovimiento) {
        out.println(tipoMovimiento + ":" + nombre);
        try {
            System.out.println("Respuesta del servidor: " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escucharServidor() {
        new Thread(() -> {
            try {
                String mensaje;
                while ((mensaje = in.readLine()) != null) {
                    System.out.println(mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        ClienteJuego cliente = new ClienteJuego(nombre);
        cliente.conectarAlServidor("localhost", 8080);
        System.out.println("Cliente iniciado");

        while (true) {
            System.out.println("Seleccione una acción: 1. Colocar carta, 2. Atacar, 3. Terminar turno");
            int accion = scanner.nextInt();
            if (accion == 1) {
                cliente.enviarMovimiento("colocarCarta");
            } else if (accion == 2) {
                cliente.enviarMovimiento("atacar");
            } else if (accion == 3) {
                cliente.enviarMovimiento("terminarTurno");
                break;
            } else {
                System.out.println("Acción no válida. Intente de nuevo.");
            }
        }
    }
}