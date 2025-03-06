package br.com.jota.shophub.controllers;

import br.com.jota.shophub.domain.entities.Fornecedor;
import br.com.jota.shophub.dtos.categoria.CategoriaDTO;
import br.com.jota.shophub.dtos.produto.AtualizarDadosProduto;
import br.com.jota.shophub.dtos.produto.CadastroDeProduto;
import br.com.jota.shophub.dtos.produto.ListaProduto;
import br.com.jota.shophub.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping()
    @Operation(description = "Cadastro de produto deve possuir sempre um fornecedor")
    public ResponseEntity<Void> createProduct(@RequestBody CadastroDeProduto dados, @AuthenticationPrincipal Fornecedor fornecedor, UriComponentsBuilder uri) {
        service.cadastroProduto(dados, fornecedor.getIdFornecedor());
        var url = uri.path("/{nomeProduto}").buildAndExpand(dados.nome()).toUri();
        return ResponseEntity.created(url).build();
    }

    @GetMapping()
    @Operation(description = "Exibir uma lista de produtos")
    public ResponseEntity<Page<ListaProduto>> productList(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(service.lista(pageable));
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualizar dados do produto por id")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody AtualizarDadosProduto dados) {
        service.atualizar(id, dados);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(description = "Adicionar e atualizar categorias de produtos por id")
    public ResponseEntity<Void> updateCategoryProduct(@PathVariable Long id, @RequestBody CategoriaDTO categoria) {
        service.atualizarCategoria(id, categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletar produto por ud")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
