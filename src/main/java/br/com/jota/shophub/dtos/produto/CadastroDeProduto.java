package br.com.jota.shophub.dtos.produto;

import java.math.BigDecimal;
import java.util.List;

import br.com.jota.shophub.domain.enums.CategoriaEnum;

public record CadastroDeProduto(
    String nome,
    String descricao,
    BigDecimal preco,
    Integer estoque,
    List<CategoriaEnum> categorias,
    Long idFornecedor
) {
    
}
