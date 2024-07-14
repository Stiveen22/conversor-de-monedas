package com.challengeConversorDeMonedas;

public class ConversorDeMonedas {
    private final ModeloRespuesta tasasDeCambio;

    public ConversorDeMonedas(ModeloRespuesta tasasDeCambio) {
        this.tasasDeCambio = tasasDeCambio;
    }

    public double convertir(double cantidad, String monedaOrigen, String monedaDestino) {
        if (monedaOrigen.equals("USD")) {
            return cantidad * tasasDeCambio.obtenerTasa(monedaDestino);
        } else if (monedaDestino.equals("USD")) {
            return cantidad / tasasDeCambio.obtenerTasa(monedaOrigen);
        } else {
            double cantidadEnUSD = cantidad / tasasDeCambio.obtenerTasa(monedaOrigen);
            return cantidadEnUSD * tasasDeCambio.obtenerTasa(monedaDestino);
        }
    }
}
