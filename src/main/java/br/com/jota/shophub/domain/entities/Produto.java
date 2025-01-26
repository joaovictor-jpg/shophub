package br.com.jota.shophub.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    @JoinTable(name = "produtos_categorias", joinColumns = @JoinColumn(name = "id_produto", referencedColumnName = "id_produto"), inverseJoinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria"))
    private List<Categoria> categorias = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    public Produto() {
    }

    

    public Produto(Long idProduto, String nome, String descricao, BigDecimal preco, Integer estoque,
            Categoria categorias, Fornecedor fornecedor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.categorias.add(categorias);
        this.fornecedor = fornecedor;
    }



    public Long getId() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public List<Categoria> getCategorias() {
        List<Categoria> categoriasList = this.categorias;
        return categoriasList;
    }

}
