package com.chillizardinteractive.servidor;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServidorMain {
    public static void main(String[] args) {
        System.out.println("Escoge tu opción");
        System.out.println("1. Inicia servidor");
        System.out.println("2. Crear mazo");
        Scanner scan = new Scanner(System.in);
        int scanner = scan.nextInt();

        switch (scanner) {
            case 1:
                
                break;

                case 2:
                
                break;
        
            default:
            System.out.println("Escriba un numero correcto");
                break;
        }
        scan.close();
    }
}
