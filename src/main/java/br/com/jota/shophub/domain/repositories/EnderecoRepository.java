package br.com.jota.shophub.domain.repositories;

import br.com.jota.shophub.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
