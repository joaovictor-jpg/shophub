package br.com.jota.shophub.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Pedido")
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;
    private LocalDate data;
    private BigDecimal total = BigDecimal.valueOf(0.0);
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensPedidos> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(LocalDate data, Cliente cliente) {
        this.data = data;
        this.cliente = cliente;
    }

    public void adicionarItem(Produto produto, Integer quantidade) {
        ItensPedidos newItem = new ItensPedidos(produto, this, quantidade, produto.getPreco());
        this.itens.add(newItem);
    }

    public void calcularTotal() {
        this.total = this.total.add(
                itens.stream()
                        .map(item -> BigDecimal.valueOf(item.getQuantidade()).multiply(item.getPrecoUnitario()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id_pedido, pedido.id_pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_pedido);
    }
}
