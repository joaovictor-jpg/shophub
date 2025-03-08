package br.com.jota.shophub.dtos.produto;

import br.com.jota.shophub.dtos.categoria.CategoriaDTO;

import java.math.BigDecimal;

public record AtualizarDadosProduto(
    String nome,
    String Descricao,
    Integer estoque,
    BigDecimal preco,
    CategoriaDTO categoria
) {
    
}
