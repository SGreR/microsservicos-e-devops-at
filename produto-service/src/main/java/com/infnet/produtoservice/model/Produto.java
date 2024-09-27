package com.infnet.produtoservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("produtos")
public class Produto {
    @Id
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
}
