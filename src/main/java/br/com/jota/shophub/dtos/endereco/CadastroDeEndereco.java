package br.com.jota.shophub.dtos.endereco;

import jakarta.validation.constraints.NotBlank;

public record CadastroDeEndereco(
    @NotBlank
    String logradouro,
    Integer numero,
    String complemento,
    @NotBlank
    String bairro,
    @NotBlank
    String cidade,
    @NotBlank
    String uf,
    @NotBlank
    String cep
) {
    
}
