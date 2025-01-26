package br.com.jota.shophub.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jota.shophub.domain.entities.Categoria;
import br.com.jota.shophub.domain.enums.CategoriaEnum;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(CategoriaEnum nome);
}
