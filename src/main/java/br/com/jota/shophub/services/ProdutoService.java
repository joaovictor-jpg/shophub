package br.com.jota.shophub.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jota.shophub.domain.dto.ProdutoDTO;
import br.com.jota.shophub.domain.entities.Categoria;
import br.com.jota.shophub.domain.entities.Fornecedor;
import br.com.jota.shophub.domain.entities.Produto;
import br.com.jota.shophub.domain.repositories.CategoriaRepository;
import br.com.jota.shophub.domain.repositories.FornecedorRepository;
import br.com.jota.shophub.domain.repositories.ProdutoRepository;
import br.com.jota.shophub.dtos.categoria.CategoriaDTO;
import br.com.jota.shophub.dtos.produto.AtualizarDadosProduto;
import br.com.jota.shophub.dtos.produto.CadastroDeProduto;
import br.com.jota.shophub.dtos.produto.ListaProduto;

@Service
public class ProdutoService {

    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository repository;

    public ProdutoService(CategoriaRepository categoriaRepository, FornecedorRepository fornecedorRepository,
            ProdutoRepository repository) {
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.repository = repository;
    }

    @Transactional
    public void cadastroProduto(CadastroDeProduto dados) {
        List<Categoria> categorias = dados.categorias().stream()
                .map(categoria -> categoriaRepository.findByNome(categoria).orElseThrow()).toList();
        Fornecedor fornecedor = fornecedorRepository.findById(dados.idFornecedor()).orElseThrow();
        Produto produto = new Produto(dados, categorias, fornecedor);
        repository.save(produto);
    }

    @Transactional
    public Page<ListaProduto> lista(Pageable pageable) {
        Page<ProdutoDTO> produtosPage = repository.listaProdutos(pageable);

        List<ListaProduto> dto = produtosPage.getContent().stream()
                .map(produtodto -> {
                    String categoriasString = produtodto.categorias();
                    List<String> categorias = List.of(categoriasString.split(",")).stream()
                            .map(String::trim)
                            .toList();

                    return new ListaProduto(produtodto, categorias);
                }).toList();

        return new PageImpl<>(dto, pageable, produtosPage.getTotalElements());
    }

    @Transactional
    public void atualizar(Long id, AtualizarDadosProduto dados) {
        Produto produto = repository.findById(id).orElseThrow();
        repository.save(atualizarProduto(dados, produto));
    }

    @Transactional
    public void atualizarCategoria(Long id, CategoriaDTO categoria) {
        Produto produto = repository.findById(id).orElseThrow();
        repository.save(categorias(produto, categoria));
    }

    @Transactional
    public void delete(Long id) {
        Produto produto = repository.findById(id).orElseThrow();
        produto.setAtivo(false);
        repository.save(produto);
    }

    private Produto atualizarProduto(AtualizarDadosProduto dados, Produto produto) {
        if (dados.nome() != null) {
            produto.setNome(dados.nome());
        }
        if (dados.Descricao() != null) {
            produto.setDescricao(dados.Descricao());
        }
        if (dados.preco() != null) {
            produto.setPreco(dados.preco());
        }
        if (dados.estoque() != null) {
            produto.setEstoque(dados.estoque());
        }
        if (dados.categoria() != null) {
            categorias(produto, dados.categoria());
        }

        return produto;
    }

    private Produto categorias(Produto produto, CategoriaDTO categoriaDTO) {
        boolean categoriaExiste = produto.getCategorias().stream()
                .anyMatch(c -> c.getNome().equals(categoriaDTO.categoria().name()));

        if (categoriaExiste) {
            produto.deletarCategoria(categoriaDTO);
        } else {
            Categoria categoria = categoriaRepository.findByNome(categoriaDTO.categoria())
                    .orElseThrow();
            produto.adicionarCategoria(categoria);
        }

        return produto;
    }
}
