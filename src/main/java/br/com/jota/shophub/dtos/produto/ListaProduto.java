package br.com.jota.shophub.dtos.produto;

import java.math.BigDecimal;

public record ListaProduto(
        Long id_produto,
        String nome_produto,
        String descricao_produto,
        BigDecimal preco,
        Integer estoque,
        String nome_fornecedor,
        String nome_categoria) {

}
