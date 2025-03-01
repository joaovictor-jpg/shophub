package br.com.jota.shophub.domain.repositories;

import br.com.jota.shophub.domain.entities.Pedido;
import br.com.jota.shophub.dtos.pedido.ListaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(nativeQuery = true, value = """
            SELECT\s
                p.id_pedido AS "ID Pedido",\s
                p.total AS "Total do Pedido",\s
                c.nome AS "Nome do Cliente",\s
                pr.nome AS "Nome do Produto",\s
                pr.descricao AS "Descrição do Produto"
            FROM pedidos p
            INNER JOIN clientes c ON c.id_cliente = p.id_cliente
            INNER JOIN itens_pedidos i ON i.id_pedido = p.id_pedido
            INNER JOIN produtos pr ON i.id_produto = pr.id_produto
            WHERE p.id_cliente = :idCliente;
            """)
    List<ListaPedido> findBiIdCliente(Long idCliente);
}
