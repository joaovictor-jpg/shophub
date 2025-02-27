CREATE TABLE clientes_enderecos (
    id_cliente BIGINT NOT NULL,
    id_endereco BIGINT NOT NULL,
    PRIMARY KEY (id_cliente, id_endereco),
    CONSTRAINT fk_clientes_enderecos FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente),
    CONSTRAINT fk_enderecos_clientes FOREIGN KEY (id_endereco) REFERENCES enderecos (id_endereco)
);