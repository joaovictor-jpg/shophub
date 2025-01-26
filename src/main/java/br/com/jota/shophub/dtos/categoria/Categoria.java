package br.com.jota.shophub.dtos.categoria;

import br.com.jota.shophub.domain.enums.CategoriaEnum;
import jakarta.validation.constraints.NotNull;

public record Categoria(
    @NotNull
    CategoriaEnum categoria
) {
}
