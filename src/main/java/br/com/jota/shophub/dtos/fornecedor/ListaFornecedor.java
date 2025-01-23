package br.com.jota.shophub.dtos.fornecedor;

import br.com.jota.shophub.domain.entities.Fornecedor;

public record ListaFornecedor(
        String nome,
        String email,
        String telefone,
        String cnpj,
        String logradouro) {
    public ListaFornecedor(Fornecedor fornecedor) {
        this(fornecedor.getNome(), fornecedor.getEmail(), fornecedor.getTelefone(), fornecedor.getCnpj(),
                fornecedor.getEndereco().getLogradouro());
    }
}
