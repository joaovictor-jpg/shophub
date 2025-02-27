package br.com.jota.shophub.controllers;

import br.com.jota.shophub.dtos.fornecedor.AtualizarDadosFornecedor;
import br.com.jota.shophub.dtos.fornecedor.DadosCadastroFornecedor;
import br.com.jota.shophub.dtos.fornecedor.ListaFornecedor;
import br.com.jota.shophub.services.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping()
    @Operation(description = "Cadastro de Fornecedor")
    public ResponseEntity<String> supplierCreate(@RequestBody @Valid DadosCadastroFornecedor dados, UriComponentsBuilder uri) {
        service.cadastro(dados);
        var url = uri.path("/{nomeFornecedor}").buildAndExpand(dados.nome()).toUri();
        return ResponseEntity.created(url).body("Cadastro de Fornecedo feito com sucesso");
    }

    @GetMapping()
    @Operation(description = "Criar e exibir uma lista de fornecedor")
    public ResponseEntity<List<ListaFornecedor>> supplierList() {
        return ResponseEntity.ok().body(service.listaFornecedors());
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualizar dados do cliente por id")
    public ResponseEntity<Void> supplierUpdate(@PathVariable Long id, @RequestBody @Valid AtualizarDadosFornecedor dados) {

        service.atualizarFornecedor(id, dados);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletar fornecedor por id")
    public ResponseEntity<Void> supplierDelete(@PathVariable Long id) {
        service.deletarFornecedor(id);
        return ResponseEntity.noContent().build();
    }

}
