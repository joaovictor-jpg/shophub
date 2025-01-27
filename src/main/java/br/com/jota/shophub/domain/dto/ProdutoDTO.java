package br.com.jota.shophub.domain.dto;

import java.math.BigDecimal;

public record ProdutoDTO(
                Long idProduto,
                String nomeProduto,
                String descricaoProduto,
                BigDecimal preco,
                Integer estoque,
                Boolean ativo,
                String nomeFornecedor,
                String categorias) {
}
