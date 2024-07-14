package com.challengeConversorDeMonedas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiKey = "fffa1b1ec19f7ec29134c82c";
        String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        ClienteAPI clienteAPI = new ClienteAPI(baseUrl);
        ModeloRespuesta respuesta = clienteAPI.obtenerTasasDeCambio();

        if (respuesta != null) {
            iniciarMenu(respuesta);
        } else {
            System.out.println("No se pudo obtener la tasa de cambio.");
        }
    }

    private static void iniciarMenu(ModeloRespuesta respuesta) {
        Scanner scanner = new Scanner(System.in);
        ConversorDeMonedas conversor = new ConversorDeMonedas(respuesta);

        while (true) {
            System.out.println("\nConversor de Monedas");
            System.out.println("1. Convertir de USD a otra moneda");
            System.out.println("2. Convertir de otra moneda a USD");
            System.out.println("3. Convertir entre dos monedas diferentes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 4) {
                System.out.println("Saliendo del programa...");
                break;
            }

            System.out.print("Ingrese la cantidad: ");
            double cantidad = scanner.nextDouble();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el código de la moneda de destino (ej. ARS, BOB, BRL): ");
                    String monedaDestino1 = scanner.next().toUpperCase();
                    double resultado1 = conversor.convertir(cantidad, "USD", monedaDestino1);
                    System.out.println(cantidad + " USD a " + monedaDestino1 + ": " + resultado1);
                    break;
                case 2:
                    System.out.print("Ingrese el código de la moneda de origen (ej. ARS, BOB, BRL): ");
                    String monedaOrigen2 = scanner.next().toUpperCase();
                    double resultado2 = conversor.convertir(cantidad, monedaOrigen2, "USD");
                    System.out.println(cantidad + " " + monedaOrigen2 + " a USD: " + resultado2);
                    break;
                case 3:
                    System.out.print("Ingrese el código de la moneda de origen (ej. ARS, BOB, BRL): ");
                    String monedaOrigen3 = scanner.next().toUpperCase();
                    System.out.print("Ingrese el código de la moneda de destino (ej. ARS, BOB, BRL): ");
                    String monedaDestino3 = scanner.next().toUpperCase();
                    double resultado3 = conversor.convertir(cantidad, monedaOrigen3, monedaDestino3);
                    System.out.println(cantidad + " " + monedaOrigen3 + " a " + monedaDestino3 + ": " + resultado3);
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        }

        scanner.close();
    }
}
