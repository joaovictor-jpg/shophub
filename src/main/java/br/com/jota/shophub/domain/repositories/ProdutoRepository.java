package br.com.jota.shophub.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jota.shophub.domain.dto.ProdutoDTO;
import br.com.jota.shophub.domain.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(nativeQuery = true, value = """
            SELECT  
                p.id_produto,
                p.nome AS nome_produto,
                p.descricao AS descricao_produto,
                p.preco,
                p.estoque,
                p.ativo,
                f.nome AS nome_fornecedor,
                string_agg(c.nome, ', ' ORDER BY c.nome) AS categorias
            FROM 
                produtos p
            JOIN 
                fornecedores f ON p.id_fornecedor = f.id_fornecedor
            JOIN 
                produtos_categorias pc ON p.id_produto = pc.id_produto
            JOIN 
                categorias c ON pc.id_categoria = c.id_categoria
            GROUP BY 
                p.id_produto, p.nome, p.descricao, p.preco, f.nome
            ORDER BY 
                p.id_produto;
        """)
    Page<ProdutoDTO> listaProdutos(Pageable pageable);
}
