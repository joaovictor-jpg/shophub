package br.com.jota.shophub.domain.repositories;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jota.shophub.domain.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Category, Long> {
    Categoria findByName(String name);
}
