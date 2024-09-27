package com.infnet.veiculosservice.controller;

import com.infnet.veiculosservice.model.Veiculo;
import com.infnet.veiculosservice.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listVeiculos() {
        List<Veiculo> veiculos = veiculoService.findAll();
        if(veiculos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findVeiculoById(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.findById(id);
        if(veiculo.isPresent()) {
            return ResponseEntity.ok(veiculo.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> postVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo savedVeiculo = veiculoService.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVeiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoOptional = veiculoService.findById(id);
        if(veiculoOptional.isPresent()) {
            veiculo.setId(id);
            veiculoService.save(veiculo);
            return ResponseEntity.ok(veiculo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVeiculo(@PathVariable Long id) {
        veiculoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
