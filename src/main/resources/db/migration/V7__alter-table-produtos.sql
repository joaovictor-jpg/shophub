ALTER TABLE produtos
ADD COLUMN id_fornecedor BIGINT NOT NULL,
ADD CONSTRAINT fk_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedores (id_fornecedor) ON DELETE CASCADE;