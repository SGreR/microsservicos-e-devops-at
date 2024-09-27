package com.infnet.climaservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.climaservice.model.ClimaAtual;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClimaService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ClimaService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://api.open-meteo.com/v1/forecast").build();
        this.objectMapper = objectMapper;
    }

    public Mono<ClimaAtual> getClimaData(){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", "-30.0328")
                        .queryParam("longitude", "-51.2302")
                        .queryParam("current", "temperature_2m,apparent_temperature,precipitation,rain,showers")
                        .queryParam("timezone", "America/Sao_Paulo")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(responseBody -> {
                    try {
                        JsonNode rootNode = objectMapper.readTree(responseBody);

                        JsonNode currentNode = rootNode.path("current");
                        double temperatura = currentNode.path("temperature_2m").asDouble();
                        double temperaturaAparente = currentNode.path("apparent_temperature").asDouble();
                        double precipitacao = currentNode.path("precipitation").asDouble();
                        double chuva = currentNode.path("rain").asDouble();
                        double pancadas = currentNode.path("showers").asDouble();

                        ClimaAtual climaAtual = new ClimaAtual();
                        climaAtual.setTemperatura(temperatura);
                        climaAtual.setTemperaturaAparente(temperaturaAparente);
                        climaAtual.setPrecipitacao(precipitacao);
                        climaAtual.setChuva(chuva);
                        climaAtual.setPancadas(pancadas);

                        return climaAtual;
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse JSON response", e);
                    }
                });
    }
}
