package br.com.jota.shophub.dtos.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String senha
) {
}
