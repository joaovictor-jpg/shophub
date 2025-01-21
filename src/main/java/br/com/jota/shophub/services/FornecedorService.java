package br.com.jota.shophub.services;

import org.springframework.stereotype.Service;

import br.com.jota.shophub.domain.entities.Fornecedor;
import br.com.jota.shophub.domain.repositories.FornecedorRepository;
import br.com.jota.shophub.dtos.fornecedor.DadosCadastroFornecedor;
import br.com.jota.shophub.exception.RegraDeNegorcioException;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;
    private final EmailService emailService;

    public FornecedorService(FornecedorRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public void cadastro(DadosCadastroFornecedor dados) {
        var forncedorOption = repository.findByEmailIgnoreCase(dados.email());

        if(forncedorOption.isPresent()) {
            throw new RegraDeNegorcioException("E-Mail já cadastrado");
        }

        Fornecedor fornecedor = new Fornecedor(dados);

        repository.save(fornecedor);

        emailService.enviarEmailVerificacao(fornecedor);
    }
}
