# Frontend - Sistema PDV Posto de Combustível

Interface gráfica desenvolvida em Java Swing para o sistema de gerenciamento PDV.

## 🛠️ Tecnologias

- **Java 17**
- **Java Swing**
- **Maven**
- **HTTP Client** (para comunicação com API)

## 📋 Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.6+
- Backend rodando em `http://localhost:8080`

## 🚀 Como Executar

### Método 1: Via Maven

```bash
mvn clean javafx:run
```

### Método 2: Via JAR

```bash
mvn clean package
java -jar target\PdvFrontEnd-1.0-SNAPSHOT.jar
```

## 👥 Tipos de Usuário

### 🔑 Administrador
- Acesso completo ao sistema
- Pode cadastrar novos frentistas
- Gerencia todas as entidades

### 👤 Frentista
- Acesso limitado
- Apenas gerenciamento de bombas

## 🎨 Funcionalidades

- ✅ Sistema de login com validação
- ✅ Cadastro de novos frentistas (Admin)
- ✅ Preenchimento de informações pessoais
- ✅ CRUD completo para todas as entidades
- ✅ Interface moderna e responsiva
- ✅ Gerenciamento de bombas de combustível

## 🔧 Resolução de Problemas

### Erro de Conexão
Verifique se o backend está rodando em `http://localhost:8080`

### Erro de Compilação
```bash
mvn clean install -U
```

## 📝 Primeiro Acesso

1. Execute a aplicação
2. Se não houver usuários, cadastre um administrador
3. Faça login com as credenciais criadas
4. Comece a usar o sistema!

