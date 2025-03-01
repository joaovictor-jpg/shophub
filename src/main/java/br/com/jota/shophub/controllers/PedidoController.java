package br.com.jota.shophub.controllers;

import br.com.jota.shophub.dtos.pedido.DadosCadastroPedido;
import br.com.jota.shophub.services.PedidoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

}
