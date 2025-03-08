package br.com.jota.shophub.dtos.produto;

import br.com.jota.shophub.domain.dto.ProdutoDTO;

import java.math.BigDecimal;
import java.util.List;

public record ListaProduto(
        Long idProduto,
        String nomeProduto,
        String descricaoProduto,
        BigDecimal preco,
        Integer estoque,
        Boolean ativo,
        String nomeFornecedor,
        List<String> categorias) {

    public ListaProduto(ProdutoDTO dados, List<String> categorias) {
        this(dados.idProduto(), dados.nomeProduto(), dados.descricaoProduto(), dados.preco(), dados.estoque(), dados.ativo(), dados.nomeFornecedor(), categorias);
    }
}
