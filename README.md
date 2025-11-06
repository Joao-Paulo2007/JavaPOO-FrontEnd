# Sistema PDV - Posto de Combustível

Sistema completo de gerenciamento para posto de combustível desenvolvido em Java, utilizando Spring Boot para o backend e Java Swing para o frontend.

## 📋 Descrição

Sistema de gerenciamento PDV (Ponto de Venda) para postos de combustível, com controle de:
- Bombas de combustível
- Pessoas (Funcionários, Fornecedores)
- Produtos e Preços
- Estoque e Custos
- Controle de Acesso (Admin/Frentista)
- Sistema de Login

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **PostgreSQL** (Banco de dados)
- **Swagger/OpenAPI** (Documentação da API)
- **Maven** (Gerenciador de dependências)

### Frontend
- **Java Swing**
- **Maven**
- **API REST** (Comunicação com backend)

## 📦 Estrutura do Projeto

```
pdvcombustivel3/
├── backend/           # API REST Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── frontend/          # Interface Swing
│   ├── src/
│   ├── pom.xml
│   └── README.md
└── README.md
```

## 🚀 Como Executar

### Pré-requisitos

- **Java JDK 17** ou superior
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Git** (opcional, para clonar o repositório)

### ⚠️ IMPORTANTE: Banco de Dados Separado

Este projeto usa um banco de dados **DIFERENTE** para evitar conflitos:
- **Banco:** `pdv_posto` (porta 8081)
- **Não conflita** com outros projetos PDV que usem `pdvpostocombustivel`

### 1️⃣ Configurar o Banco de Dados

1. Abra o **pgAdmin 4** ou seu cliente PostgreSQL
2. Execute o script `backend/CRIAR_BANCO_DADOS.sql` OU crie manualmente:
   ```sql
   CREATE DATABASE pdv_posto;
   ```

### 2️⃣ Configurar o Backend

1. Navegue até a pasta do backend:
   ```bash
   cd backend
   ```

2. O arquivo `application.properties` já está configurado com:
   - **Banco:** `pdv_posto`
   - **Porta:** `8081`
   - **Senha:** `postgres` (altere se necessário)

3. Se precisar alterar a senha, edite `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.password=SUA_SENHA_AQUI
   ```

4. Execute o backend:
   ```bash
   mvn spring-boot:run
   ```

   O backend estará rodando em: `http://localhost:8081`

5. Acesse a documentação Swagger:
   ```
   http://localhost:8081/swagger-ui/index.html
   ```

### 3️⃣ Executar o Frontend

1. Abra um **novo terminal** e navegue até a pasta do frontend:
   ```bash
   cd frontend
   ```

2. Execute o frontend:
   ```bash
   mvn clean javafx:run
   ```
   
   Ou compile e execute:
   ```bash
   mvn clean package
   java -jar target\PdvFrontEnd-1.0-SNAPSHOT.jar
   ```

### 4️⃣ Primeiro Acesso

1. Na primeira execução, o sistema solicitará o **cadastro de um Administrador**
2. Preencha os dados do primeiro usuário admin
3. Faça login com as credenciais criadas

## 👥 Tipos de Usuário

### Administrador
- Acesso completo a todos os módulos
- Pode cadastrar novos frentistas
- Gerencia: Pessoas, Produtos, Preços, Custos, Estoques, Acessos, Contatos e Bombas

### Frentista
- Acesso restrito
- Pode gerenciar apenas: **Bombas de Combustível**

## 📚 Funcionalidades

- ✅ **Sistema de Login** com controle de acesso
- ✅ **CRUD completo** para todas as entidades
- ✅ **Gerenciamento de Bombas** (Admin e Frentista)
- ✅ **Cadastro de Pessoas** (Frentista, Fornecedor)
- ✅ **Controle de Estoque**
- ✅ **Gerenciamento de Preços e Custos**
- ✅ **API REST** documentada com Swagger
- ✅ **Validação de dados**
- ✅ **Interface gráfica** intuitiva

## 🔧 Resolução de Problemas

### Erro de Conexão com Banco de Dados
- Verifique se o PostgreSQL está rodando
- Confirme usuário e senha no `application.properties`
- Verifique se o banco `pdvpostocombustivel` foi criado

### Erro ao Executar o Backend
```bash
# Limpar e recompilar
mvn clean install
mvn spring-boot:run
```

### Erro ao Executar o Frontend
```bash
# Limpar e recompilar
mvn clean package
```

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

Desenvolvido para fins educacionais como projeto de Programação Orientada a Objetos em Java.

---

**Nota:** Este é um sistema de demonstração para fins educacionais. Para uso em produção, considere implementar recursos adicionais de segurança e otimizações.

