package br.com.jota.shophub.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jota.shophub.domain.entities.Cliente;
import br.com.jota.shophub.domain.repositories.ClienteRepository;
import br.com.jota.shophub.dtos.cliente.CadastroDeClientes;
import br.com.jota.shophub.dtos.cliente.ListaClientes;
import br.com.jota.shophub.exception.RegraDeNegorcioException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final EmailService emailService;

    public ClienteService(ClienteRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Transactional
    public void cadastrar(CadastroDeClientes dados) {
        var clienteOptional = repository.findByEmailIgnoreCase(dados.email());

        if (clienteOptional.isPresent()) {
            throw new RegraDeNegorcioException("E-Mail já está sendo usado");
        }
        
        Cliente cliente = new Cliente(dados);

        repository.save(cliente);

        emailService.enviarEmailVerificacao(cliente);
    }

    public Page<ListaClientes> listaCliente(Pageable pageable) {
        return repository.findAll(pageable).map(ListaClientes::new); 
    }
}
