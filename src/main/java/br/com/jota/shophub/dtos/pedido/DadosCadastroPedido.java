package br.com.jota.shophub.dtos.pedido;

import br.com.jota.shophub.domain.entities.Produto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroPedido(
        @NotNull Long idCliente,
        @NotNull Long idProduto,
        @NotNull Integer quantidade
) {
}
