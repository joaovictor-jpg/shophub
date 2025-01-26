package br.com.jota.shophub.services;

import org.springframework.stereotype.Service;

import br.com.jota.shophub.domain.entities.Categoria;
import br.com.jota.shophub.domain.entities.Fornecedor;
import br.com.jota.shophub.domain.entities.Produto;
import br.com.jota.shophub.domain.repositories.CategoriaRepository;
import br.com.jota.shophub.domain.repositories.FornecedorRepository;
import br.com.jota.shophub.dtos.produto.CadastroDeProduto;

@Service
public class ProdutoService {

    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;

    public ProdutoService(CategoriaRepository categoriaRepository, FornecedorRepository fornecedorRepository) {
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public void cadastroProduto(CadastroDeProduto dados) {
        Categoria categoria = categoriaRepository.findByName(dados.categoria());
        Fornecedor fornecedor = fornecedorRepository.findById(dados.idFornecedor()).orElseThrow();
        Produto produto = new Produto(dados.nome(), dados.descricao(), dados.preco(), dados.estoque(), categoria, fornecedor);
    }
}
