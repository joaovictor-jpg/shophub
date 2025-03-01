package br.com.jota.shophub.domain.entities;

import br.com.jota.shophub.domain.entities.pk.ItensPedidosPk;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "ItensPedidos")
@Table(name = "itens_pedidos")
public class ItensPedidos {
    @EmbeddedId
    private ItensPedidosPk id = new ItensPedidosPk();
    @ManyToOne
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false, nullable = false)
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "id_produto", insertable = false, updatable = false, nullable = false)
    private Produto produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;

    public ItensPedidos() {
    }

    public ItensPedidos(Produto produto, Pedido pedido, Integer quantidade, BigDecimal precoUnitario) {
        this.id.setProduto(produto);
        this.id.setPedido(pedido);
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public ItensPedidosPk getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedidos that = (ItensPedidos) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
