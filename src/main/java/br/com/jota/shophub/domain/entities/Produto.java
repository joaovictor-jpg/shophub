package br.com.jota.shophub.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.jota.shophub.dtos.categoria.CategoriaDTO;
import br.com.jota.shophub.dtos.produto.CadastroDeProduto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Produto")
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "produtos_categorias", joinColumns = @JoinColumn(name = "id_produto", referencedColumnName = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria"))
    private List<Categoria> categorias = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;
    private Boolean ativo;

    public Produto() {
    }

    public Produto(CadastroDeProduto dados, List<Categoria> categorias, Fornecedor fornecedor) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.preco = dados.preco();
        this.estoque = dados.estoque();
        this.categorias.addAll(categorias);
        this.fornecedor = fornecedor;
        ativo = true;
    }

    public Long getId() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public List<Categoria> getCategorias() {
        List<Categoria> categoriasList = this.categorias;
        return categoriasList;
    }

    public void adicionarCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }

    public void deletarCategoria(CategoriaDTO categoriaDTO) {
        this.categorias.removeIf(c -> c.getNome().equals(categoriaDTO.categoria().name()));
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
