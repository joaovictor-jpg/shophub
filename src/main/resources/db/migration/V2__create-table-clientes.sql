CREATE TABLE clientes (
    id_cliente BIGSERIAL PRIMARY KEY,
    nome VARCHAR(70) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
    ativo BOOLEAN NOT NULL
);