package br.com.jota.shophub.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jota.shophub.domain.entitys.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
