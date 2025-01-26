package br.com.jota.shophub.dtos.produto;

import java.math.BigDecimal;

import br.com.jota.shophub.domain.entities.Categoria;

public record CadastroDeProduto(
    String nome,
    String descricao,
    BigDecimal preco,
    Integer estoque,
    Categoria categoria,
    Long idFornecedor
) {
    
}
