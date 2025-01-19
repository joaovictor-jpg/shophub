CREATE TABLE enderecos (
    id_endereco BIGSERIAL PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero INTEGER,
    complemento VARCHAR(255),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf char(2) NOT NULL,
    cep char(9) NOT NULL
);