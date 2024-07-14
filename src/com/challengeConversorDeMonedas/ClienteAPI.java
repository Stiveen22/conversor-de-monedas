package com.challengeConversorDeMonedas;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteAPI {
    private final String url;

    public ClienteAPI(String url) {
        this.url = url;
    }

    public ModeloRespuesta obtenerTasasDeCambio() {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            if (respuesta.statusCode() == 200) {
                return analizarRespuestaJSON(respuesta.body());
            } else {
                System.out.println("Error: " + respuesta.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ModeloRespuesta analizarRespuestaJSON(String respuestaJSON) {
        JsonObject jsonObject = JsonParser.parseString(respuestaJSON).getAsJsonObject();
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, ModeloRespuesta.class);
    }
}
