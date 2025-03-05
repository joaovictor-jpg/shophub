package br.com.jota.shophub.domain.entities;

import br.com.jota.shophub.domain.enums.CategoriaEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
