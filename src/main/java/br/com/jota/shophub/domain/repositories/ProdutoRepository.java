package br.com.jota.shophub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jota.shophub.domain.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
