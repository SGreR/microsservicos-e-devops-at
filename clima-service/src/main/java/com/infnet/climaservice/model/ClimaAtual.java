package com.infnet.climaservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClimaAtual {
    @JsonProperty("temperature_2m")
    private double temperatura;

    @JsonProperty("apparent_temperature")
    private double temperaturaAparente;

    @JsonProperty("precipitation")
    private double precipitacao;

    @JsonProperty("rain")
    private double chuva;

    @JsonProperty("showers")
    private double pancadas;
}
