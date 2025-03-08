package br.com.jota.shophub.dtos.produto;

import br.com.jota.shophub.domain.enums.CategoriaEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CadastroDeProduto(
    @NotBlank
    String nome,
    @NotBlank
    String descricao,
    @NotEmpty
    @NotNull
    BigDecimal preco,
    @NotNull
    Integer estoque,
    @Valid
    List<CategoriaEnum> categorias
) {
    
}
