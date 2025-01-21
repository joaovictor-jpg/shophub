package br.com.jota.shophub.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jota.shophub.dtos.fornecedor.DadosCadastroFornecedor;
import br.com.jota.shophub.services.FornecedorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<String> postMethodName(@RequestBody DadosCadastroFornecedor dados, UriComponentsBuilder uri) {
        service.cadastro(dados);
        var url = uri.path("/{nomeFornecedor}").buildAndExpand(dados.nome()).toUri();
        return ResponseEntity.created(url).body("Cadastro de Fornecedo feito com sucesso");
    }

}
