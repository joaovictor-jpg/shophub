package br.com.jota.shophub.services;

import br.com.jota.shophub.dtos.authentication.DadosLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jota.shophub.domain.entities.Cliente;
import br.com.jota.shophub.domain.repositories.ClienteRepository;
import br.com.jota.shophub.dtos.cliente.AtualizarDadosClientes;
import br.com.jota.shophub.dtos.cliente.CadastroDeClientes;
import br.com.jota.shophub.dtos.cliente.ListaClientes;
import br.com.jota.shophub.exception.RegraDeNegorcioException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService implements UserDetailsService {

    private final ClienteRepository repository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ClienteService(ClienteRepository repository, EmailService emailService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.repository = repository;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public String login(DadosLogin dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var cliente = (UserDetails) authentication.getPrincipal();

        var tokenAcesso = tokenService.gerarToken(cliente.getUsername());
        return  tokenAcesso;
    }

    @Transactional
    public void cadastrar(CadastroDeClientes dados) {
        var clienteOptional = repository.findByEmailIgnoreCase(dados.email());

        if (clienteOptional.isPresent()) {
            throw new RegraDeNegorcioException("E-Mail já está sendo usado");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        Cliente cliente = new Cliente(dados, senhaCriptografada);

        repository.save(cliente);

        emailService.enviarEmailVerificacao(cliente);
    }

    public void ativar(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow();

        cliente.setAtivo(true);

        repository.save(cliente);
    }

    public Page<ListaClientes> listaCliente(Pageable pageable) {
        return repository.findAll(pageable).map(ListaClientes::new);
    }

    @Transactional
    public ListaClientes atualizar(Long id, AtualizarDadosClientes dados) {
        Cliente clienteOptional = repository.findById(id)
                .orElseThrow();

        Cliente cliente = converso(clienteOptional, dados);

        repository.save(cliente);

        return new ListaClientes(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow();

        cliente.setAtivo(false);

        repository.save(cliente);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException ("Cliente não encontrado"));
    }

    private Cliente converso(Cliente cliente, AtualizarDadosClientes dados) {
        
        if (dados.nome() != null) {
            cliente.setNome(dados.nome());
        }
        if (dados.telefone() != null) {
            cliente.setTelefone(dados.telefone());
        }

        return cliente;
    }
}
