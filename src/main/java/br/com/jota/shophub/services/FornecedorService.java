package br.com.jota.shophub.services;

import br.com.jota.shophub.domain.entities.Fornecedor;
import br.com.jota.shophub.domain.repositories.FornecedorRepository;
import br.com.jota.shophub.dtos.authentication.DadosLogin;
import br.com.jota.shophub.dtos.fornecedor.AtualizarDadosFornecedor;
import br.com.jota.shophub.dtos.fornecedor.DadosCadastroFornecedor;
import br.com.jota.shophub.dtos.fornecedor.ListaFornecedor;
import br.com.jota.shophub.exception.RegraDeNegorcioException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FornecedorService implements UserDetailsService {

    private final FornecedorRepository repository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public FornecedorService(FornecedorRepository repository, EmailService emailService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.repository = repository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("Fornecedor não encontrado"));
    }

    public String login(DadosLogin dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var fornecedor = (UserDetails) authentication.getPrincipal();
        var token = tokenService.gerarToken(fornecedor.getUsername(), fornecedor.getAuthorities());
        return token;
    }

    @Transactional
    public void cadastro(DadosCadastroFornecedor dados) {
        var forncedorOption = repository.findByEmailIgnoreCase(dados.email());

        if (forncedorOption.isPresent()) {
            throw new RegraDeNegorcioException("E-Mail já cadastrado");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());

        Fornecedor fornecedor = new Fornecedor(dados, senhaCriptografada);

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
