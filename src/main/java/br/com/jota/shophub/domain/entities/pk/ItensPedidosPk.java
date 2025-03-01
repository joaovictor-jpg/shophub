package br.com.jota.shophub.domain.entities.pk;

import br.com.jota.shophub.domain.entities.Pedido;
import br.com.jota.shophub.domain.entities.Produto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItensPedidosPk implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    public ItensPedidosPk() {
    }

    public ItensPedidosPk(Produto id_produto, Pedido id_pedido) {
        this.produto = id_produto;
        this.pedido = id_pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedidosPk that = (ItensPedidosPk) o;
        return Objects.equals(produto, that.produto) && Objects.equals(pedido, that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, pedido);
    }
}
