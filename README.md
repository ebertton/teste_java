# Teste API Java

## Pré-requisitos

Os pré-requisitos para executar o projeto são:

1. Java: O Java deve estar instalado no seu sistema. Baseado no arquivo pom.xml, é preciso usar a versão 17 do Java (Java SE Development Kit 17).
2. Maven: A ferramenta de build Maven deve estar instalada em seu sistema. Ele é usado para gerenciar dependências, build, teste e rodar o projeto.
3. IDE ou Editor de Texto: Uma IDE Java (como IntelliJ IDEA, Eclipse, etc.) ou um editor de texto (como Visual Studio Code, Sublime Text, etc.) com suporte Java é necessário para visualizar e editar o código.
4. Lombok Plugin: O projeto utiliza Lombok, então a IDE ou o Editor de Texto precisa ter o plugin Lombok instalado.
5. Git: Git é necessário para controle de versões e para clonar o Projeto do repositório remoto.
6. Conexão com a Internet: Uma conexão com a internet é necessária para baixar dependências do Maven e clone do repositório do projeto

## Instruções de execução

Para executar este projeto via terminal, siga estas etapas:

1. Navegue até o diretório raiz `projeto_api\api` do projeto usando o comando `cd`.
2. Use o Maven (ferramenta de compilação do projeto) para compilar e executar o projeto.
`mvn clean install`
`mvn spring-boot:run`

## Dependências do projeto

Este projeto usa as seguintes bibliotecas e Marcos:

1. spring-boot-starter-data-jpa: Este starter inclui as dependências para usar Spring Data JPA com Hibernate.
2. spring-boot-starter-validation: Este starter inclui dependências para o Bean Validation, a API de validação de método do Hibernate.
3. spring-boot-starter-web: Starter para construção de aplicações web, incluindo RESTful, usando Spring MVC. Usa Tomcat como o servidor embutido padrão.
4. flyway-core: O Flyway ajuda a gerenciar migrações de banco de dados para Java.
5. spring-boot-devtools: O Spring Boot Devtools é uma dependência do Spring Boot que oferece vários recursos benéficos para desenvolvimento e depuração.
6. h2: H2 é uma base de dados SQL em Java de código aberto, extremamente leve e que permite um acesso muito rápido
7. lombok: O Project Lombok é uma biblioteca java que se conecta automaticamente ao seu editor e ferramentas de construção, gerando métodos, como getters, setters e outros componentes comuns automaticamente.
8. spring-boot-starter-test: Starter para testar aplicativos Spring Boot com bibliotecas, incluindo JUnit, Hamcrest e Mockito


## Documentação API Postman
![image](https://github.com/ebertton/angular_laravel/assets/16254922/fd70d41c-1927-4d49-8cdc-81c4f6590c78)

Documentação da API URL [https://documenter.getpostman.com/view/20378449/2sA3QpBsrq](https://documenter.getpostman.com/view/20378449/2sA3QpBsrq).\
