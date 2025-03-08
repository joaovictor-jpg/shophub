package br.com.jota.shophub.dtos.cliente;

import jakarta.validation.constraints.Pattern;

public record AtualizarDadosClientes(
        String nome,
        @Pattern(regexp = "^(\\(?[0-9]{2}\\)?)? ?([0-9]{4,5})-?([0-9]{4})$") String telefone) {
}
