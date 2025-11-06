# Backend - Sistema PDV Posto de Combustível

API REST desenvolvida com Spring Boot para gerenciar o sistema de PDV de um posto de combustível.

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger/OpenAPI**
- **Maven**

## 📋 Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.6+
- PostgreSQL 12+

## ⚠️ IMPORTANTE: Configuração Separada

Este projeto usa configurações **SEPARADAS** para não conflitar com outros projetos:

| Configuração | Projeto Original | Este Projeto (Amigo) |
|--------------|------------------|----------------------|
| **Banco de Dados** | `pdvpostocombustivel` | `pdv_posto` |
| **Porta** | `8080` | `8081` |
| **Conflito?** | ❌ Não | ✅ Funcionam juntos |

## 🚀 Como Executar

### 1. Criar o Banco de Dados

**Opção 1 - Script SQL:**
Execute o arquivo `CRIAR_BANCO_DADOS.sql` no pgAdmin

**Opção 2 - Manual:**
```sql
CREATE DATABASE pdv_posto;
```

### 2. Configurar a Senha (se necessário)

O arquivo `application.properties` já está configurado com:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pdv_posto
spring.datasource.username=postgres
spring.datasource.password=postgres
server.port=8081
```

Se sua senha for diferente, edite:
```bash
src/main/resources/application.properties
```

### 3. Executar a Aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8081`

### 4. Acessar a Documentação

Swagger UI: `http://localhost:8081/swagger-ui/index.html`

## 📚 Endpoints Principais

- `/api/pessoas` - Gerenciamento de pessoas
- `/api/bombas` - Gerenciamento de bombas
- `/api/produtos` - Gerenciamento de produtos
- `/api/precos` - Gerenciamento de preços
- `/api/custos` - Gerenciamento de custos
- `/api/estoques` - Gerenciamento de estoques
- `/api/acessos` - Controle de acesso
- `/api/contatos` - Gerenciamento de contatos

## 🔧 Limpeza e Rebuild

```bash
mvn clean install
```

