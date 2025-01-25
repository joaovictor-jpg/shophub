create table produtos_categorias(
    id_produto BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    PRIMARY KEy (id_produto, id_categoria),
    CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES produtos (id_produto) ON DELETE CASCADE,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria) ON DELETE CASCADE
);