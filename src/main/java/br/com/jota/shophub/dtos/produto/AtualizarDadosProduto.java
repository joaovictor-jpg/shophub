package br.com.jota.shophub.dtos.produto;

import java.math.BigDecimal;

import br.com.jota.shophub.dtos.categoria.CategoriaDTO;

public record AtualizarDadosProduto(
    String nome,
    String Descricao,
    Integer estoque,
    BigDecimal preco,
    CategoriaDTO categoria
) {
    
}
