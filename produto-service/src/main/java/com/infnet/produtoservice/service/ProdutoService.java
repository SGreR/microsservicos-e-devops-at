package com.infnet.produtoservice.service;

import com.infnet.produtoservice.model.Produto;
import com.infnet.produtoservice.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Flux<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Mono<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Mono<Produto> save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Mono<Produto> update(Long id, Produto produto) {
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public Mono<Void> delete(Long id) {
        return produtoRepository.deleteById(id);
    }
}
