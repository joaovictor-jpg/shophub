package br.com.jota.shophub.services;

import br.com.jota.shophub.domain.entities.Pedido;
import br.com.jota.shophub.domain.repositories.ClienteRepository;
import br.com.jota.shophub.domain.repositories.PedidoRepository;
import br.com.jota.shophub.domain.repositories.ProdutoRepository;
import br.com.jota.shophub.dtos.pedido.DadosCadastroPedido;
import br.com.jota.shophub.exception.RegraDeNegorcioException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public void criarPedido(DadosCadastroPedido dadosCadastroPedido) {
        var clienteOptional = clienteRepository.findById(dadosCadastroPedido.idCliente());
        var produtoOptional = produtoRepository.findById(dadosCadastroPedido.idProduto());

        if(clienteOptional.isEmpty() || produtoOptional.isEmpty()) {
            throw new RegraDeNegorcioException("Cliente não encontrado");
        }

        var cliente = clienteOptional.get();
        var produto = produtoOptional.get();

        Pedido pedido = new Pedido(LocalDate.now(), cliente);

        pedido.adicionarItem(produto, dadosCadastroPedido.quantidade());

        pedido.calcularTotal();

        produto.setEstoque(produto.getEstoque() - dadosCadastroPedido.quantidade());

        pedidoRepository.save(pedido);
    }
}
