package br.com.jota.shophub.dtos.produto;

import java.math.BigDecimal;
import java.util.List;

import br.com.jota.shophub.domain.dto.ProdutoDTO;

public record ListaProduto(
        Long idProduto,
        String nomeProduto,
        String descricaoProduto,
        BigDecimal preco,
        Integer estoque,
        String nomeFornecedor,
        List<String> categorias) {

    public ListaProduto(ProdutoDTO dados, List<String> categorias) {
        this(dados.idProduto(), dados.nomeProduto(), dados.descricaoProduto(), dados.preco(), dados.estoque(),
                dados.nomeFornecedor(), categorias);
    }
}
