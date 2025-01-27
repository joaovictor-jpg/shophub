package br.com.jota.shophub.domain.entities;

import java.util.ArrayList;
import java.util.List;

import br.com.jota.shophub.domain.enums.CategoriaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name = "Categoria")
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;
    @Enumerated(EnumType.STRING)
    private CategoriaEnum nome;
    private String descricao;
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome.name();
    }

    public String getDescricao() {
        return descricao;
    }

}
