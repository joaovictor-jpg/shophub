CREATE TABLE itens_pedidos(
    id_pedido BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 1,
    preco_unitario NUMERIC(12, 2) NOT NULL,
    PRIMARY KEY(id_pedido, id_produto),
    CONSTRAINT fk_pedido FOREIGN KEY (id_pedido) REFERENCES pedidos (id_pedido),
    CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES produtos (id_produto)
);