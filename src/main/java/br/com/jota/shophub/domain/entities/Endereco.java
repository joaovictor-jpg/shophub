package br.com.jota.shophub.domain.entities;

import br.com.jota.shophub.dtos.endereco.AtualizarDadosEndereco;
import br.com.jota.shophub.dtos.endereco.CadastroDeEndereco;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Endereco")
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;


    public Endereco() {
    }

    public Endereco(CadastroDeEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.cep = endereco.cep();
    }

    public void atualizarInformacoes(AtualizarDadosEndereco dados) {
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(idEndereco, endereco.idEndereco);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idEndereco);
    }
}
