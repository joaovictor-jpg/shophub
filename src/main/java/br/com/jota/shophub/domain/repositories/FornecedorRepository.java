package br.com.jota.shophub.domain.repositories;

import br.com.jota.shophub.domain.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByEmailIgnoreCase(String email);
}
