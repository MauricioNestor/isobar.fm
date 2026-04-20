# Isobar FM - Api de bandas

API REST desenvolvida em SpringBoot, que consome dados de bandas de um serviço externo e expõe através de endpoits padronizados, com cache em memória para otimização de performance


# Tecnologias utilizadas:
- Java 25
- Spring Boot 4.0.5
- Spring Web(restTemplate)
- Spring Cache(@Cachable, in-memory)
- Springdoc OpenAPI 2.8.6 (Swagger UI)
- Hibernate Validator

  ## EndPoints:
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | /demo/bands | Lista todas as bandas |
| GET | /demo/bands/{id} | Busca banda por ID |
| GET | /demo/bands/search?name={name} | Busca banda por nome |
| GET | /demo/bands/genre/{genre} | Filtra bandas por gênero |

## Exemplos de uso

Listar todas as bandas:
GET http://localhost:8080/demo/bands

Buscar banda por ID:
GET http://localhost:8080/demo/bands/bc710bcf-8815-42cf-bad2-3f1d12246aeb

Buscar por nome:
GET http://localhost:8080/demo/bands/search?name=U2

Filtrar por gênero:
GET http://localhost:8080/demo/bands/genre/rock

## Arquitetura:

O projeto segue arquitetura em camadas:

- **controller** — recebe as requisições HTTP e devolve as respostas
- **service** — contém a lógica de negócio e o cache
- **model** — representação dos dados
- **config** — configurações técnicas (RestTemplate, Cache)

## Cache

A camada de cache é implementada com `@Cacheable` do Spring.
Na primeira requisição, os dados são buscados na API externa e 
armazenados em memória. Nas requisições seguintes, os dados são 
retornados diretamente do cache, sem nenhuma chamada externa.

## Documentação

A documentação interativa da API está disponível via Swagger UI após subir a aplicação:

http://localhost:8080/swagger-ui/index.html

## Como rodar

### Pré-requisitos
- Java 
- Maven

### Executando o projeto

Clone o repositório:
```bash
git clone https://github.com/MauricioNestor/isobar.fm.git
```

Acesse a pasta do projeto:
```bash
cd demo
```

Execute a aplicação:
```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`
