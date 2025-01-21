CREATE TABLE fornecedores(
    id_fornecedor BIGSERIAL PRIMARY KEY,
    nome VARCHAR(70) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    CNPJ VARCHAR(19) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    ativo BOOLEAN NOT NULL,
    endereco_id BIGINT,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES enderecos(id_endereco) ON DELETE CASCADE
)