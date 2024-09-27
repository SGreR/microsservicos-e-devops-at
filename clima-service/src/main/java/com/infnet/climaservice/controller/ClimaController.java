package com.infnet.climaservice.controller;

import com.infnet.climaservice.model.ClimaAtual;
import com.infnet.climaservice.service.ClimaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clima")
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping
    public Mono<ClimaAtual> getClimaAtual() {
        return climaService.getClimaData();
    }
}
