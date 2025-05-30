package br.com.jota.shophub.domain.entities;

import br.com.jota.shophub.domain.enums.RotaEnum;
import br.com.jota.shophub.domain.interfaces.EntidadeComEmail;
import br.com.jota.shophub.dtos.fornecedor.AtualizarDadosFornecedor;
import br.com.jota.shophub.dtos.fornecedor.DadosCadastroFornecedor;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "Fornecedor")
@Table(name = "fornecedores")
public class Fornecedor implements UserDetails, EntidadeComEmail {
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
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "fornecedores_enderecos", joinColumns = @JoinColumn(name = "id_fornecedor", referencedColumnName = "id_fornecedor"),
            inverseJoinColumns = @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco"))
    private List<Endereco> enderecos = new ArrayList<>();
    private String role;

    public Fornecedor() {
    }

    public Fornecedor(DadosCadastroFornecedor dados, String senha) {
        nome = dados.nome();
        email = dados.email();
        telefone = dados.telefone();
        cnpj = dados.cnpj();
        this.senha = senha;
        ativo = false;
        this.enderecos.add(new Endereco(dados.endereco()));
        this.role = "FORNECEDOR";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public void atualizarDadosFornecedor(AtualizarDadosFornecedor dados) {
        if (dados.nome() != null) {
            nome = dados.nome();
        }
        if (dados.telefone() != null) {
            telefone = dados.telefone();
        }
    }

    public Long getId() {
        return idFornecedor;
    }

    @Override
    public RotaEnum getRotaVerificacao() {
        return RotaEnum.FORNECEDOR;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(idFornecedor, that.idFornecedor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idFornecedor);
    }
}
