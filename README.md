# Todo List API

Esta é uma API simples de Todo List construída com Java Spring Boot. A API permite criar, listar, atualizar e deletar tarefas (todos).

## Passo a Passo para Usar a Aplicação

1. **Clone o repositório:**
    ```bash
    git clone <URL-do-repositorio>
    ```

2. **Navegue até o diretório do projeto:**
    ```bash
    cd todolist
    ```

3. **Certifique-se de que você possui o Java 17 instalado:**
    ```bash
    java -version
    ```

4. **Configure o banco de dados:**
    - A aplicação usa MySQL e H2 Database. Configure o arquivo `application.properties` para conectar ao seu banco de dados MySQL.

5. **Instale as dependências do projeto:**
    ```bash
    ./mvnw clean install
    ```

6. **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

7. **Acesse a API:**
    - A API estará disponível em [http://localhost:8080/todos](http://localhost:8080/todos)

## Tecnologias Utilizadas

- **Java 17:** Linguagem de programação usada para construir a aplicação.
- **Spring Boot 3.3.3:** Framework para criar aplicações Java standalone e de produção.
- **Spring Data JPA:** Usado para persistência de dados com JPA e Hibernate.
- **MySQL:** Banco de dados relacional usado para armazenar os dados da aplicação.
- **H2 Database:** Banco de dados em memória para testes.
- **Springdoc OpenAPI:** Usado para documentação e interação com a API através de uma interface gráfica.
- **Jakarta Validation:** Validação dos dados de entrada com Hibernate Validator.

## Endpoints

| Método | Endpoint         | Descrição                     |
|--------|------------------|-------------------------------|
| POST   | `/todos`          | Criar uma nova tarefa.        |
| GET    | `/todos`          | Listar todas as tarefas.      |
| PUT    | `/todos`          | Atualizar uma tarefa existente.|
| DELETE | `/todos/{id}`     | Deletar uma tarefa pelo ID.   |


### Exemplo de Requisição - Listar Tarefas
```bash
GET /todos
```
### Exemplo de Requisição - Criar uma Tarefa

```bash
POST /todos
Content-Type: application/json

{
  "nome": "Estudar Spring",
  "descricao": "Estudar o framework Spring Boot",
  "realizado": false,
  "prioridade": 1
}
```

### Exemplo de Requisição - Atualizar uma Tarefa

```bash
PUT /todos/1
Content-Type: application/json

{
  "nome": "Estudar Spring Boot",
  "descricao": "Aprofundar no framework Spring Boot",
  "realizado": true,
  "prioridade": 2
}
```

### Exemplo de Requisição - Deletar uma Tarefa

```bash
DELETE /todos/1
```