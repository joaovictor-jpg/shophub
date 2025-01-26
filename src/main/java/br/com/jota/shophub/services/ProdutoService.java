package br.com.jota.shophub.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jota.shophub.domain.entities.Categoria;
import br.com.jota.shophub.domain.entities.Fornecedor;
import br.com.jota.shophub.domain.entities.Produto;
import br.com.jota.shophub.domain.repositories.CategoriaRepository;
import br.com.jota.shophub.domain.repositories.FornecedorRepository;
import br.com.jota.shophub.domain.repositories.ProdutoRepository;
import br.com.jota.shophub.dtos.produto.CadastroDeProduto;

@Service
public class ProdutoService {

    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository repository;

    public ProdutoService(CategoriaRepository categoriaRepository, FornecedorRepository fornecedorRepository, ProdutoRepository repository) {
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.repository = repository;
    }

    @Transactional
    public void cadastroProduto(CadastroDeProduto dados) {
        Categoria categoria = categoriaRepository.findByNome(dados.categoria()).orElseThrow();
        Fornecedor fornecedor = fornecedorRepository.findById(dados.idFornecedor()).orElseThrow();
        Produto produto = new Produto(dados, categoria, fornecedor);
        repository.save(produto);
    }
}
