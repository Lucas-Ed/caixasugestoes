# API de Caixa de Sugestões -- API Spring Boot - Fatec Araras

Este projeto é uma API REST desenvolvida em **Spring Boot 3.5.7** para
gerenciar sugestões e categorias, incluindo funcionalidades de CRUD,
validações, tratamento global de exceções e documentação automática com
**Springdoc OpenAPI**.

------------------------------------------------------------------------

## 🚀 Funcionalidades

-   CRUD de **Categorias**
-   CRUD de **Sugestões**
-   Validações com *Jakarta Validation*
-   Tratamento global de erros via **GlobalExceptionHandler**
-   Exceção customizada: `RegraDeNegocioException`
-   Documentação automática via **Swagger / OpenAPI**
-   Log automático de rotas (`EndpointLogger`)
-   Banco em memória **H2** (ambiente local)

------------------------------------------------------------------------

## 📦 Tecnologias Utilizadas

-   Java 17
-   Spring Boot 3.5.7
-   Spring Web
-   Spring Data JPA
-   Spring Validation
-   Springdoc OpenAPI
-   Lombok
-   ModelMapper
-   H2 Database
-   MySQL Driver
-   Gradle (com *gradlew wrapper*)

------------------------------------------------------------------------

## ▶️ Como Rodar o Projeto

``` bash
./gradlew bootRun
```

A API iniciará em:

    http://localhost:9000

------------------------------------------------------------------------

## 📄 Documentação da API

Após iniciar a aplicação:

-   Swagger UI:\
    `http://localhost:9000/openapi/swagger-ui.html`

-   OpenAPI JSON:\
    `http://localhost:9000/openapi/v3/api-docs`

-   OpenAPI YAML:\
    `http://localhost:9000/openapi/v3/api-docs.yaml`

------------------------------------------------------------------------

## 🧪 Testes das Rotas

Um arquivo `./main/.../resources/api.http` foi criado para uso com o **VS Code REST Client**
permitindo testar rapidamente todas as rotas da API.

------------------------------------------------------------------------

## 📁 Estrutura do Projeto

    📁 src/main/java/..../caixasugestoes/
     ├── 📄 categorias/
     ├── 📄 sugestoes/
     ├── 📄 playground/
     ├── 📄 config/
     │    └── EndpointLogger.java
     ├── 📄 exceptions/
     │    ├── 📄 GlobalExceptionHandler.java
     │    └── 📄 RegraDeNegocioException.java
     └── 📄 CaixasugestoesApplication.java

------------------------------------------------------------------------

## ⚠️ Regras de Negócio

-   Não é permitido excluir uma categoria que ainda possui sugestões
    associadas.
-   Essa regra lança `RegraDeNegocioException`.

------------------------------------------------------------------------

## 🛠 Construção e Empacotamento

Gerar JAR final:

``` bash
./gradlew build
```

JAR será criado em:

    build/libs/

------------------------------------------------------------------------

## 🙋 Sobre

Projeto criado como parte de estudo acadêmico e demonstração de arquitetura REST
com Spring Boot.

------------------------------------------------------------------------

## 📜 Licença

Este projeto é distribuído sob licença MIT.
