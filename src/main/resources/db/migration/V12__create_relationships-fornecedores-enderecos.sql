CREATE TABLE fornecedores_enderecos (
    id_fornecedor BIGINT NOT NULL,
    id_endereco BIGINT NOT NULL,
    PRIMARY KEY (id_fornecedor, id_endereco),
    CONSTRAINT fk_fornecedores_enderecos FOREIGN KEY (id_fornecedor) REFERENCES fornecedores (id_fornecedor),
    CONSTRAINT fk_enderecos_fornecedores FOREIGN KEY (id_endereco) REFERENCES enderecos (id_endereco)
);