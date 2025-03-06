package br.com.jota.shophub.services;

import br.com.jota.shophub.domain.entities.Pedido;
import br.com.jota.shophub.domain.repositories.ClienteRepository;
import br.com.jota.shophub.domain.repositories.PedidoRepository;
import br.com.jota.shophub.domain.repositories.ProdutoRepository;
import br.com.jota.shophub.dtos.pedido.DadosCadastroPedido;
import br.com.jota.shophub.dtos.pedido.ListaPedido;
import br.com.jota.shophub.exception.RegraDeNegorcioException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public void criarPedido(DadosCadastroPedido dadosCadastroPedido, Long id) {
        var clienteOptional = clienteRepository.findById(id);
        var produtoOptional = produtoRepository.findById(dadosCadastroPedido.idProduto());

        if(clienteOptional.isEmpty() || produtoOptional.isEmpty()) {
            throw new RegraDeNegorcioException("Cliente não encontrado");
        }

        var cliente = clienteOptional.get();
        var produto = produtoOptional.get();

        if (produto.getEstoque() < dadosCadastroPedido.quantidade()) {
            throw new RegraDeNegorcioException("A quantidade solicitada não está disponível. Por favor, refaça o pedido com uma quantidade menor. A quantidade em estoque é " + produto.getEstoque());
        }

        Pedido pedido = new Pedido(LocalDate.now(), cliente);

        pedido.adicionarItem(produto, dadosCadastroPedido.quantidade());

        pedido.calcularTotal();

        produto.setEstoque(produto.getEstoque() - dadosCadastroPedido.quantidade());

        pedidoRepository.save(pedido);
    }

    @Transactional
    public List<ListaPedido> listaPedidoCliente(Long idCliente) {
        return pedidoRepository.findBiIdCliente(idCliente);
    }
}
