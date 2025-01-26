package br.com.jota.shophub.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jota.shophub.dtos.produto.CadastroDeProduto;
import br.com.jota.shophub.services.ProdutoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Void> cadastroProduto(@RequestBody CadastroDeProduto dados, UriComponentsBuilder uri) {
        service.cadastroProduto(dados);
        var url = uri.path("/{nomeProduto}").buildAndExpand(dados.nome()).toUri();
        return ResponseEntity.created(url).build();
    }

}
