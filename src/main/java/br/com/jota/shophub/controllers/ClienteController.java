package br.com.jota.shophub.controllers;

import br.com.jota.shophub.dtos.authentication.DadosLogin;
import br.com.jota.shophub.dtos.cliente.AtualizarDadosClientes;
import br.com.jota.shophub.dtos.cliente.CadastroDeClientes;
import br.com.jota.shophub.dtos.cliente.ListaClientes;
import br.com.jota.shophub.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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


    @PutMapping("/{id}")
    @Operation(description = "Atualizar dados do cliente")
    public ResponseEntity<ListaClientes> atualizar(@PathVariable Long id,
                                                   @RequestBody @Valid AtualizarDadosClientes dados) {
        return ResponseEntity.ok().body(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletar o cliente por id")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().body("Cliente deletado com sucesso");
    }

}
