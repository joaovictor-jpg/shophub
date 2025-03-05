package br.com.jota.shophub.dtos.fornecedor;

import br.com.jota.shophub.domain.entities.Fornecedor;

public record ListaFornecedor(
        String nome,
        String email,
        String telefone,
        String cnpj) {
    public ListaFornecedor(Fornecedor fornecedor) {
        this(fornecedor.getNome(), fornecedor.getUsername(), fornecedor.getTelefone(), fornecedor.getCnpj());
    }
}
