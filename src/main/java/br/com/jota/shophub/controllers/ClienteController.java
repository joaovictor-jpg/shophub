package br.com.jota.shophub.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jota.shophub.dtos.cliente.CadastroDeClientes;
import br.com.jota.shophub.services.ClienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
