package br.com.jota.shophub.dtos.pedido;

import java.math.BigDecimal;

public record ListaPedido(
        Long idPedido,
        BigDecimal total,
        String nomeCliente,
        String nomeProduto,
        String descricao
) {

}
