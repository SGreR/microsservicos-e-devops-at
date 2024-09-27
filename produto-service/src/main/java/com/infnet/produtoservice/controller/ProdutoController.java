package com.infnet.produtoservice.controller;

import com.infnet.produtoservice.model.Produto;
import com.infnet.produtoservice.service.ProdutoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public Flux<Produto> getAll() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Produto> getById(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @PostMapping
    public Mono<Produto> save(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @PutMapping("/{id}")
    public Mono<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.update(id, produto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return produtoService.delete(id);
    }

}
