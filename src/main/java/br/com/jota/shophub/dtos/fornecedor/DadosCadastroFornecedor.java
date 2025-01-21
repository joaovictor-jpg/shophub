package br.com.jota.shophub.dtos.fornecedor;

import br.com.jota.shophub.dtos.endereco.CadastroDeEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroFornecedor(
        @NotBlank String nome,
        @NotBlank @Pattern(regexp = "^(\\(?[0-9]{2}\\)?)? ?([0-9]{4,5})-?([0-9]{4})$") String telefone,
        @NotBlank @Pattern(regexp = "^[0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2}$") String cnpj,
        @NotBlank @Email String email,
        @NotBlank String senha,
        CadastroDeEndereco endereco) {
}
