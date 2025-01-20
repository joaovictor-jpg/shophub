package br.com.jota.shophub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jota.shophub.domain.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
