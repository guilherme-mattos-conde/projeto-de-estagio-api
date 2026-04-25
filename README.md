# Bem vindo(a) ao meu repositório ✌
API REST desenvolvida para gerenciamento de projetos e pranchas CAD.

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- Spring Security

## Como executar o projeto

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL
- Git

## 🗄️ Banco de Dados

1. Crie um banco no PostgreSQL:

```sql
CREATE DATABASE projeto_estagio;
```

2. Configure as variáveis de ambiente ou `application.properties`:

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/projeto_estagio
SPRING_DATASOURCE_USERNAME=seu_usuario
SPRING_DATASOURCE_PASSWORD=sua_senha
```

## Executando a API

### Acesse a pasta do backend:

```bash
cd backend
```

### Execute a aplicação:

```bash
./mvnw spring-boot:run
```

ou no Windows:

```bash
mvnw.cmd spring-boot:run
```

## Base URL

http://localhost:8080

## Estrutura do Projeto

- controller → endpoints REST
- service → regras de negócio
- repository → acesso ao banco de dados
- entity → entidades JPA
- dto → objetos de transferência de dados
- exception → tratamento global de erros

## Funcionalidades

- CRUD completo de projetos
- Paginação e ordenação
- Atualização de status
- Validação de dados
- Tratamento global de exceções
- Controle de datas (createdAt e updatedAt)

## Regras de Negócio

- Projetos não podem ser duplicados considerando:
  - name + clientName + city + state
- O campo updatedAt é atualizado automaticamente a cada modificação
- O campo createdAt é definido automaticamente na criação

## Segurança

Atualmente a API está com acesso liberado para desenvolvimento.

Futuramente será implementado:
- Autenticação com JWT
- Controle de acesso por usuário

## Observações

- As migrations do banco são gerenciadas pelo Flyway
- A ordenação e paginação são realizadas no backend
- O frontend deve consumir a API utilizando os parâmetros de paginação
