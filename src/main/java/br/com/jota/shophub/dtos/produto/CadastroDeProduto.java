package br.com.jota.shophub.dtos.produto;

import java.math.BigDecimal;

import br.com.jota.shophub.domain.enums.CategoriaEnum;

public record CadastroDeProduto(
    String nome,
    String descricao,
    BigDecimal preco,
    Integer estoque,
    CategoriaEnum categoria,
    Long idFornecedor
) {
    
}
