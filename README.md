# API JP Capacitação - Simulação de E-commerce

Esta é uma API REST desenvolvida em Java com Spring Boot, criada com o intuito de simular o funcionamento do back-end de um sistema de e-commerce. O projeto abrange diversas funcionalidades fundamentais do comércio eletrónico, incluindo gestão de produtos, controlo de stocks, carrinho de compras, aplicação de cupons e processamento de pedidos.

## Funcionalidades Principais

* **Autenticação e Utilizadores (`/user`)**: Registo de utilizadores e login com autenticação baseada em tokens JWT.
* **Catálogo de Produtos (`/products`)**: Criação, edição, remoção e listagem de produtos. Registo histórico de alterações de preços.
* **Categorias (`/categories`)**: Gestão de categorias de produtos (suporta subcategorias hierárquicas).
* **Estoque/Inventário (`/inventory`)**: Adição e remoção de stock de produtos, com registo de transações (compra e venda) e alertas de stock baixo.
* **Carrinho de Compras (`/cart`)**: Criação de carrinhos, adição/remoção de produtos e aplicação de cupons de desconto (fixos ou percentuais).
* **Pedidos (`/order`)**: Checkout do carrinho transformando-o num pedido, consulta de histórico de pedidos e cancelamento.
* **Cupons (`/coupons`)**: Criação e listagem de cupons no sistema, sendo o cupom umm valor fixo ou uma porcentagem de desconto.

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot** (Web, Data JPA, Security, Validation)
* **Autenticação JWT** (`java-jwt`)
* **Oracle Database**
* **Docker & Docker Compose** (para instanciar o banco de dados Oracle)
* **Lombok**
* **Swagger / OpenAPI 3** (documentação interativa da API)

## Documentação da API (Swagger)

A API possui documentação automática gerada pelo Swagger. Sendo possível acessar a interface visual através do seu browser:
[Link do Swagger](http://localhost:9090/swagger-ui/index.html#/)

Aqui você irá encontrar os detalhes de todos os endpoints, parâmetros requeridos e respostas possíveis de todos os Controllers (ProductsController, CartController, OrderController, etc.).

## Autenticação e Segurança
Grande parte da aplicação é protegida por Spring Security utilizando Tokens JWT.
Para aceder a rotas protegidas (como as do Carrinho de Compras /cart/** e Pedidos /order/**), é necessário:

1. Criar uma requisição POST para /user/new enviando um JSON com name, email e password.

2. Fazer login POST para /user/login com as credenciais para receber o Token JWT.

3. Incluir o token no Header de autorização das próximas requisições HTTP no formato:

`Authorization: Bearer <seu_token_jwt_aqui>`
