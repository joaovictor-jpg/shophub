# ShopHub

![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache](https://img.shields.io/badge/apache-%23D42029.svg?style=for-the-badge&logo=apache&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

## _DESCRIÇÃO_
### **O que faz:**

Essa API RESTful permite a gestão de fornecedores, clientes, produtos e pedidos. Ela oferece endpoints para cadastro, atualização, remoção e consulta dessas entidades, garantindo uma comunicação eficiente com o banco de dados PostgreSQL.

Fornecedores: Cadastro e gerenciamento de empresas ou indivíduos que fornecem produtos.

Clientes: Registro de clientes que podem realizar pedidos na plataforma.

Produtos: Controle de produtos disponíveis, incluindo informações como nome, descrição, preço e estoque.

Pedidos: Criação e gerenciamento de pedidos, associando clientes e produtos, além de registrar status e histórico de compras.

A API utiliza autenticação com JSON Web Token (JWT) para segurança e integração do Flyway para versionamento do banco de dados.
---
### **Tecnologias:**
- Spring Boot
- Flyway
- PostgreSQL 
- Spring Validation
- Swagger
- Spring Boot Email
- Spring JPA
---
### **Por que?**
Essa aplicação facilita a conexão entre o fornecedor e o cliente, reduzindo custos e proporcionando segurança e eficiência. Com uma plataforma intuitiva, fornecedores podem expandir o alcance de suas mercadorias para um público maior, enquanto os clientes têm acesso a uma ampla variedade de produtos com os melhores preços, tudo sem precisar sair de casa.

## _INSTRUÇÃO DE INSTALAÇÃO_

### **Pré Requisitos**
- Java version 21
- Docker
- Postgres
- Git
---
### **Etapas**
**DOCKER-COMPOSE**
```dockerfile
version: "3.5"

services:
  postgres:
    image: postgres:16.1
    environment:
      POSTGRES_USER: ${DB_USER}
      POSTGRES_PASSWORD: ${DB_PASSWORD}
    volumes:
      - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - postgres
    restart: unless-stopped

  pgadmin:
    image: dpage/pgadmin4
    environment:
      PGADMIN_DEFAULT_EMAIL: ${DB_EMAIL}
      PGADMIN_DEFAULT_PASSWORD: ${DB_PASSWORD}
    ports:
      - "8081:80"
    depends_on:
      - postgres
    networks:
      - postgres

networks:
  postgres:
    driver: bridge

volumes:
  postgres:
```
```shell

- run: docker-compose up -d
- stop: docker-compose stop
- destruir imagens: docker-compose down
```

**ENV**
```dotenv
DB_USER=${DB_USER}
DB_PASSWORD=${DB_PASSWORD}
DB_EMAIL=${DB_EMAIL}
DB_HOST=${DB_HOST}
DB_NAME=${DB_NAME}

EMAIL_LOGIN=${EMAIL_LOGIN}
EMAIL_PASSWORD=${EMAIL_PASSWORD}
```

## _Instrução de uso_
1. Suba um container do PostgreSQL:
Execute o comando abaixo para subir o container do PostgreSQL com Docker:

```bash
  docker-compose up -d
```
2. Criar um banco de dados no pgAdmin:

- Acesse o pgAdmin no endereço http://localhost:8081/.
- Faça login com as credenciais fornecidas (usuário e senha configurados no Docker).
- No painel do pgAdmin, crie um novo banco de dados para a aplicação.

---

3. Escolha sua IDE de preferência:
Você pode escolher entre as seguintes IDEs para rodar o código:
- IntelliJ IDEA
- VS Code
- Eclipse

---

4. Execute a aplicação:

Após configurar seu ambiente, execute a aplicação a partir da sua IDE ou usando a linha de comando, dependendo da sua preferência.

---

5. Acesse a documentação da API:
Para testar as funcionalidades da API, acesse a interface do Swagger no seguinte endereço:
http://localhost:8080/swagger-ui/index.html#/.

## GITFLOW
 * Main -> principal
 * DEV -> desenvolvimento
 * FEATURE -> Para funcionalidades específicas

## ESTRUTURA:
 ```mermaid
erDiagram
    enderecos {
        string id_endereco PK
        string logradouro
        integer numero
        string complemento
        string bairro
        string cidade
        string uf
        string cep
    }

    clientes {
        string id_cliente PK
        string nome
        string email UK
        string telefone
        string senha
        string cpf
        boolean ativo
    }

    fornecedores {
        string id_fornecedor PK
        string nome
        string email UK
        string telefone
        string CNPJ UK
        string senha
        boolean ativo
    }

    categorias {
        string id_categoria PK
        string nome
        string descricao
    }

    produtos {
        string id_produto PK
        string nome
        string descricao
        decimal preco
        integer estoque
        boolean ativo
        string id_fornecedor FK
    }

    produtos_categorias {
        string id_produto FK
        string id_categoria FK
    }

    pedidos {
        string id_pedido PK
        date data
        decimal total
        string id_cliente FK
    }

    itens_pedidos {
        string id_pedido FK
        string id_produto FK
        integer quantidade
        decimal preco_unitario
    }

    fornecedores_enderecos {
        string id_fornecedor FK
        string id_endereco FK
    }

    clientes_enderecos {
        string id_cliente FK
        string id_endereco FK
    }

    clientes ||--o{ pedidos : "realiza"
    pedidos ||--|{ itens_pedidos : "contém"
    produtos ||--o{ itens_pedidos : "está em"
    fornecedores ||--o{ produtos : "oferece"
    produtos ||--o{ produtos_categorias : "pertence"
    categorias ||--o{ produtos_categorias : "contém"
    fornecedores ||--o{ fornecedores_enderecos : "possui"
    clientes ||--o{ clientes_enderecos : "reside"
    enderecos ||--|{ fornecedores_enderecos : "localiza"
    enderecos ||--|{ clientes_enderecos : "localiza"

```

