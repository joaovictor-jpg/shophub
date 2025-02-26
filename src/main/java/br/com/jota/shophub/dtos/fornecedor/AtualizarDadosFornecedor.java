package br.com.jota.shophub.dtos.fornecedor;

import br.com.jota.shophub.dtos.endereco.AtualizarDadosEndereco;
import jakarta.validation.constraints.Pattern;

public record AtualizarDadosFornecedor(
        String nome,
        @Pattern(regexp = "^(\\(?[0-9]{2}\\)?)? ?([0-9]{4,5})-?([0-9]{4})$") String telefone,
        AtualizarDadosEndereco endereco) {
}
