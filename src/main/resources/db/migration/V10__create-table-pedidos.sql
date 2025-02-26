CREATE TABLE pedidos(
    id_pedido BIGSERIAL PRIMARY KEY,
    data DATE NOT NULL,
    total NUMERIC(12, 2) NOT NULL,
    id_cliente BIGINT NOT NULL,
    CONSTRAINT fk_pedidos_clientes FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente)
);