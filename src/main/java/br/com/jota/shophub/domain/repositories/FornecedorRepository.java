package br.com.jota.shophub.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jota.shophub.domain.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByEmailIgnoreCase(String email);
}
