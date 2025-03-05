package br.com.jota.shophub.dtos.cliente;

import br.com.jota.shophub.domain.entities.Cliente;

public record ListaClientes(
        String nome,
        String email,
        String telefone) {
    public ListaClientes(Cliente cliente) {
        this(cliente.getNome(), cliente.getUsername(), cliente.getTelefone());
    }
}
