package com.milano.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoItem {

    private Produto produto;
    private int quantidade;
    
}
