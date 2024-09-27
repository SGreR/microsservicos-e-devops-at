package com.infnet.produtoservice.repository;

import com.infnet.produtoservice.model.Produto;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProdutoRepository extends R2dbcRepository<Produto, Long> {
}
