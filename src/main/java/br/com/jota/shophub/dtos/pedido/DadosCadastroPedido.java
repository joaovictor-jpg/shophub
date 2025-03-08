package br.com.jota.shophub.dtos.pedido;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroPedido(
        @NotNull Long idProduto,
        @NotNull Integer quantidade
) {
}
