package br.com.jota.shophub.domain.repositories;

import br.com.jota.shophub.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
