package br.com.jota.shophub.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jota.shophub.dtos.categoria.CategoriaDTO;
import br.com.jota.shophub.dtos.produto.AtualizarDadosProduto;
import br.com.jota.shophub.dtos.produto.CadastroDeProduto;
import br.com.jota.shophub.dtos.produto.ListaProduto;
import br.com.jota.shophub.services.ProdutoService;

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

    @GetMapping()
    public ResponseEntity<Page<ListaProduto>> getMethodName(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(service.lista(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AtualizarDadosProduto dados) {
        service.atualizar(id, dados);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoria) {
        service.atualizarCategoria(id, categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
