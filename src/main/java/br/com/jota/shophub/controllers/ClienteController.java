package br.com.jota.shophub.controllers;

import br.com.jota.shophub.domain.entities.Cliente;
import br.com.jota.shophub.dtos.authentication.DadosLogin;
import br.com.jota.shophub.dtos.cliente.AtualizarDadosClientes;
import br.com.jota.shophub.dtos.cliente.CadastroDeClientes;
import br.com.jota.shophub.dtos.cliente.ListaClientes;
import br.com.jota.shophub.dtos.endereco.CadastroDeEndereco;
import br.com.jota.shophub.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;


    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody DadosLogin dados) {
        return ResponseEntity.ok().body(service.login(dados));
    }

    @PostMapping()
    @Operation(description = "Cadastro de cliente")
    public ResponseEntity<String> createClient(@RequestBody @Valid CadastroDeClientes dados, UriComponentsBuilder uri) {
        var url = uri.path("/{nomeCliente}").buildAndExpand(dados.nome()).toUri();
        service.cadastrar(dados);
        return ResponseEntity.created(url).body("Cliente cadastrado com sucesso");
    }

    @GetMapping()
    @Operation(description = "Visualizar uma lista de clientes")
    public ResponseEntity<Page<ListaClientes>> clientList(Pageable pageable) {
        return ResponseEntity.ok().body(service.listaCliente(pageable));
    }

    @GetMapping("/verificar/{id}")
    @Operation(description = "Buscar cliente por Id")
    public ResponseEntity<String> findById(@PathVariable Long id) {
        service.ativar(id);
        return ResponseEntity.ok().body("Cliente autenticado");
    }


    @PutMapping()
    @Operation(description = "Atualizar dados do cliente")
    public ResponseEntity<ListaClientes> atualizar(@AuthenticationPrincipal Cliente cliente,
                                                   @RequestBody @Valid AtualizarDadosClientes dados) {
        return ResponseEntity.ok().body(service.atualizar(cliente.getIdCliente(), dados));
    }

    @PatchMapping()
    @Operation(description = "Adicionar novo endereço")
    public ResponseEntity<String> adicionarEndereco(@AuthenticationPrincipal Cliente cliente, @Valid @RequestBody CadastroDeEndereco endereco) {
        service.adicionarEndereco(cliente.getIdCliente(), endereco);
        return ResponseEntity.ok().body("Endereço cadastrado com sucesso");
    }

    @DeleteMapping("/deletar/endereco/{cep}")
    @Operation(description = "Deletar endereço de cliente")
    public ResponseEntity<String> deletarEndereco(@AuthenticationPrincipal Cliente cliente, @PathVariable String cep) {
        service.removerEndereco(cliente.getIdCliente(), cep);
        return ResponseEntity.ok().body("Endereço removido com sucesso");
    }

    @DeleteMapping()
    @Operation(description = "Deletar o cliente por id")
    public ResponseEntity<String> deletar(@AuthenticationPrincipal Cliente cliente) {
        service.deletar(cliente.getIdCliente());
        return ResponseEntity.ok().body("Cliente deletado com sucesso");
    }

}
