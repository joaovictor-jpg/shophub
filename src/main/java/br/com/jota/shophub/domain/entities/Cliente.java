package br.com.jota.shophub.domain.entities;

import br.com.jota.shophub.domain.enums.RotaEnum;
import br.com.jota.shophub.domain.interfaces.EntidadeComEmail;
import br.com.jota.shophub.dtos.cliente.CadastroDeClientes;
import br.com.jota.shophub.dtos.endereco.CadastroDeEndereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Entity(name = "Cliente")
@Table(name = "clientes")
public class Cliente implements UserDetails, EntidadeComEmail {
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
    private String role;

    public Cliente() {
    }

    public Cliente(CadastroDeClientes dados, String senha) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.senha = senha;
        this.cpf = dados.cpf();
        this.ativo = false;
        this.enderecos.add(new Endereco(dados.endereco()));
        this.role = "CLIENTE";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public Long getId() {
        return idCliente;
    }

    @Override
    public RotaEnum getRotaVerificacao() {
        return RotaEnum.CLIENTE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getUsername() {
        return this.email;
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

    public void setRole(String role) {
        this.role = role;
    }

    public List<Endereco> getEndereco() {
        List<Endereco> endereco = this.enderecos;
        return endereco;
    }

    public void addEndereco(@Valid CadastroDeEndereco endereco) {
        this.enderecos.add(new Endereco(endereco));
    }

    public Boolean removeEndereco(String cep) {
        Iterator<Endereco> iterator = this.enderecos.iterator();
        while (iterator.hasNext()) {
            Endereco endereco = iterator.next();
            if (endereco.getCep().equals(cep)) {
                iterator.remove();
                return true; // Retorna true se o endereço foi removido
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                ", ativo=" + ativo +
                ", enderecos=" + enderecos +
                ", role='" + role + '\'' +
                '}';
    }
}
