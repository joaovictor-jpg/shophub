package br.com.jota.shophub.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jota.shophub.domain.entities.Fornecedor;
import br.com.jota.shophub.domain.repositories.FornecedorRepository;
import br.com.jota.shophub.dtos.fornecedor.AtualizarDadosFornecedor;
import br.com.jota.shophub.dtos.fornecedor.DadosCadastroFornecedor;
import br.com.jota.shophub.dtos.fornecedor.ListaFornecedor;
import br.com.jota.shophub.exception.RegraDeNegorcioException;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;
    private final EmailService emailService;

    public FornecedorService(FornecedorRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Transactional
    public void cadastro(DadosCadastroFornecedor dados) {
        var forncedorOption = repository.findByEmailIgnoreCase(dados.email());

        if (forncedorOption.isPresent()) {
            throw new RegraDeNegorcioException("E-Mail já cadastrado");
        }

        Fornecedor fornecedor = new Fornecedor(dados);

        repository.save(fornecedor);

        emailService.enviarEmailVerificacao(fornecedor);
    }

    public List<ListaFornecedor> listaFornecedors() {
        return repository.findAll()
                .stream()
                .map(ListaFornecedor::new)
                .toList();
    }

    public void atualizarFornecedor(Long id, AtualizarDadosFornecedor dados) {
        Fornecedor fornecedor = repository.findById(id).orElseThrow();

        fornecedor.atualizarDadosFornecedor(dados);

        repository.save(fornecedor);
    }

    public void deletarFornecedor(Long id) {
        Fornecedor fornecedor = repository.findById(id).orElseThrow();

        fornecedor.setAtivo(false);

        repository.save(fornecedor);
    }
}
