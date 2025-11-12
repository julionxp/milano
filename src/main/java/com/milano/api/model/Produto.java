package com.milano.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;
    private String descricao;
    private String imagemUrl;
    private String categoria; // ex: "Brasileiras", "Internacionais", "Seleções"
}
