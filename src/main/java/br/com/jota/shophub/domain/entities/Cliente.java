package br.com.jota.shophub.domain.entities;

import br.com.jota.shophub.dtos.cliente.AtualizarDadosClientes;
import br.com.jota.shophub.dtos.cliente.CadastroDeClientes;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Cliente")
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String cpf;
    private Boolean ativo;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "clientes_enderecos", joinColumns = @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco"))
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(CadastroDeClientes dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.senha = dados.senha();
        this.cpf = dados.cpf();
        this.ativo = false;
        this.enderecos.add(new Endereco(dados.endereco()));
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Endereco> getEndereco() {
        List<Endereco> endereco = this.enderecos;
        return endereco;
    }

    /*public void setEndereco(AtualizarDadosClientes dados) {
        var endereco = enderecos.stream().map(endereco -> endereco.equals(dados.endereco()));
    }*/

}
