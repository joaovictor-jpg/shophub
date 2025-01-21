package br.com.jota.shophub.domain.entities;

import br.com.jota.shophub.dtos.fornecedor.DadosCadastroFornecedor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "Fornecedor")
@Table(name = "fornecedores")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Long idFornecedor;
    private String nome;
    private String email;
    private String telefone;
    private String cnpj;
    private String senha;
    private Boolean ativo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id_endereco")
    private Endereco endereco;

    public Fornecedor() {
    }

    public Fornecedor(DadosCadastroFornecedor dados) {
        nome = dados.nome();
        email = dados.email();
        telefone = dados.telefone();
        cnpj = dados.cnpj();
        senha = dados.senha();
        ativo = false;
        endereco = new Endereco(dados.endereco());
    }

    public Long getIdFornecedor() {
        return idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

}
