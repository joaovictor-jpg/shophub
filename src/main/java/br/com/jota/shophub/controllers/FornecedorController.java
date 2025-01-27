package br.com.jota.shophub.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jota.shophub.dtos.fornecedor.AtualizarDadosFornecedor;
import br.com.jota.shophub.dtos.fornecedor.DadosCadastroFornecedor;
import br.com.jota.shophub.dtos.fornecedor.ListaFornecedor;
import br.com.jota.shophub.services.FornecedorService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<String> postMethodName(@RequestBody @Valid DadosCadastroFornecedor dados, UriComponentsBuilder uri) {
        service.cadastro(dados);
        var url = uri.path("/{nomeFornecedor}").buildAndExpand(dados.nome()).toUri();
        return ResponseEntity.created(url).body("Cadastro de Fornecedo feito com sucesso");
    }

    @GetMapping()
    public ResponseEntity<List<ListaFornecedor>> listaFornecedor() {
        return ResponseEntity.ok().body(service.listaFornecedors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarFornecedor(@PathVariable Long id, @RequestBody @Valid AtualizarDadosFornecedor dados) {

        service.atualizarFornecedor(id, dados);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
        service.deletarFornecedor(id);
        return ResponseEntity.noContent().build();
    }

}
