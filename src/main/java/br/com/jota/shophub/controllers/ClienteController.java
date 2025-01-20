package br.com.jota.shophub.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jota.shophub.dtos.cliente.AtualizarDadosClientes;
import br.com.jota.shophub.dtos.cliente.CadastroDeClientes;
import br.com.jota.shophub.dtos.cliente.ListaClientes;
import br.com.jota.shophub.services.ClienteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<String> postMethodName(@RequestBody CadastroDeClientes dados, UriComponentsBuilder uri) {
        var url = uri.path("/{nomeUsuario}").buildAndExpand(dados.nome()).toUri();
        service.cadastrar(dados);
        return ResponseEntity.created(url).body("Cliente cadastrado com sucesso");
    }

    @GetMapping()
    public ResponseEntity<Page<ListaClientes>> getMethodName(Pageable pageable) {
        return ResponseEntity.ok().body(service.listaCliente(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaClientes> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarDadosClientes dados) {
        return ResponseEntity.ok().body(service.atualizar(id, dados));
    }

}
