package br.com.jota.shophub.controllers;

import br.com.jota.shophub.domain.entities.Cliente;
import br.com.jota.shophub.dtos.pedido.DadosCadastroPedido;
import br.com.jota.shophub.dtos.pedido.ListaPedido;
import br.com.jota.shophub.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<Void> criarPedido(@RequestBody DadosCadastroPedido dadosCadastroPedido, @AuthenticationPrincipal Cliente cliente, UriComponentsBuilder uri) {
        service.criarPedido(dadosCadastroPedido, cliente.getIdCliente());
        var url = uri.path("/{nomeCliente}").buildAndExpand(cliente.getIdCliente()).toUri();
        return ResponseEntity.created(url).build();
    }

    @GetMapping()
    public ResponseEntity<List<ListaPedido>> listaPedidoPorCliente(@AuthenticationPrincipal Cliente cliente) {
        return ResponseEntity.ok().body(service.listaPedidoCliente(cliente.getIdCliente()));
    }

}
