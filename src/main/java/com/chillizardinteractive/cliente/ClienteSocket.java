package com.chillizardinteractive.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteSocket {
    private String ip;
    private int port;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClienteSocket(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            this.socket = new Socket(ip, port);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensaje(String mensaje) {
        out.println(mensaje);
    }

    public String recibirMensaje() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cerrarConexion() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
