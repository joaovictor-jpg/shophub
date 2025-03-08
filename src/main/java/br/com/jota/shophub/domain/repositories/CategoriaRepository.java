package br.com.jota.shophub.domain.repositories;

import br.com.jota.shophub.domain.entities.Categoria;
import br.com.jota.shophub.domain.enums.CategoriaEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(CategoriaEnum nome);
}
