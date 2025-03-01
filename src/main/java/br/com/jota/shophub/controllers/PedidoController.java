package br.com.jota.shophub.controllers;

import br.com.jota.shophub.dtos.pedido.DadosCadastroPedido;
import br.com.jota.shophub.dtos.pedido.ListaPedido;
import br.com.jota.shophub.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criarPedido(@RequestBody DadosCadastroPedido dadosCadastroPedido, UriComponentsBuilder uri) {
        service.criarPedido(dadosCadastroPedido);
        var url = uri.path("/{nomeCliente}").buildAndExpand(dadosCadastroPedido.idCliente()).toUri();
        return ResponseEntity.created(url).build();
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<List<ListaPedido>> listaPedidoPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok().body(service.listaPedidoCliente(idCliente));
    }

}
