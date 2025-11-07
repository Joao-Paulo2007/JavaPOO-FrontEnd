# 🚀 GUIA DE INSTALAÇÃO - PDV POSTO DE COMBUSTÍVEL

## 📋 Pré-requisitos

Antes de começar, você precisa ter instalado:

- ☕ **Java JDK 17** ou superior ([Download aqui](https://www.oracle.com/java/technologies/downloads/))
- 📦 **Maven 3.6+** ([Download aqui](https://maven.apache.org/download.cgi))
- 🐘 **PostgreSQL 12+** ([Download aqui](https://www.postgresql.org/download/))
- 🔧 **pgAdmin 4** (geralmente vem junto com PostgreSQL)

---

## 🎯 PASSO A PASSO COMPLETO

### 1️⃣ Extrair o Projeto

1. Extraia o arquivo `pdvcombustivel3.zip`
2. Coloque a pasta em um local de fácil acesso (ex: `C:\Projetos\`)

---

### 2️⃣ Criar o Banco de Dados

1. Abra o **pgAdmin 4**
2. Conecte ao servidor PostgreSQL
3. Clique com botão direito em **Databases**
4. Selecione **Create** → **Database**
5. Digite o nome: `pdv_posto`
6. Clique em **Save**

**OU** execute este comando SQL:

```sql
CREATE DATABASE pdv_posto;
```

---

### 3️⃣ Configurar o Backend

1. Navegue até a pasta do backend:
   ```
   pdvcombustivel3\backend\src\main\resources\
   ```

2. Abra o arquivo **`application.properties`** com um editor de texto

3. **IMPORTANTE:** Altere a senha do PostgreSQL na linha:
   ```properties
   spring.datasource.password=12345
   ```
   
   Troque `12345` pela **SUA senha do PostgreSQL**
   
   Exemplo:
   ```properties
   spring.datasource.password=minhasenha123
   ```

4. Salve o arquivo

---

### 4️⃣ Executar o Backend

1. Abra o **Prompt de Comando** ou **PowerShell**

2. Navegue até a pasta do backend:
   ```bash
   cd C:\caminho\para\pdvcombustivel3\backend
   ```

3. Execute o comando:
   ```bash
   mvn spring-boot:run
   ```

4. **Aguarde** aparecer a mensagem:
   ```
   Tomcat started on port 8080
   ```

5. ✅ **Deixe esta janela aberta!** O backend precisa ficar rodando.

---

### 5️⃣ Executar o Frontend

1. Abra uma **NOVA** janela do **Prompt de Comando** ou **PowerShell**

2. Navegue até a pasta do frontend:
   ```bash
   cd C:\caminho\para\pdvcombustivel3\frontend
   ```

3. Execute o comando:
   ```bash
   mvn clean compile exec:java
   ```

4. Aguarde a interface gráfica abrir

---

### 6️⃣ Primeiro Acesso - Cadastrar Admin

Quando o sistema abrir pela primeira vez:

1. ✅ Aparecerá uma mensagem: **"Bem-vindo! Cadastre o Admin"**

2. ✅ Uma tela de **cadastro de administrador** será exibida

3. Preencha os campos:
   - **Usuário Admin:** `admin` (ou escolha um nome)
   - **Senha:** `admin123` (ou escolha uma senha segura)
   - **Confirmar Senha:** `admin123` (mesma senha)

4. Clique em **"Cadastrar Admin"**

5. ✅ Aguarde a mensagem de sucesso

---

### 7️⃣ Fazer Login

1. A tela de login será exibida

2. Selecione o tipo: **🔑 Admin** (radio button)

3. Preencha:
   - **Usuário:** `admin`
   - **Senha:** `admin123`

4. Clique em **"ENTRAR"**

5. 🎉 **Pronto!** O sistema abrirá com todos os gerenciadores!

---

## 🎮 Como Usar o Sistema

### Como Admin, você pode acessar:

- ⛽ **Gerenciar Bombas** - Controlar as bombas de combustível
- ➕ **Novo Frentista** - Cadastrar frentistas
- 👥 **Pessoas** - Gerenciar pessoas do sistema
- 💰 **Preços** - Controlar preços dos combustíveis
- 📦 **Produtos** - Gerenciar produtos
- 💳 **Custos** - Controlar custos
- 📊 **Estoques** - Gerenciar estoque
- 🔐 **Acessos** - Ver acessos do sistema
- 📞 **Contatos** - Gerenciar contatos

### Para Cadastrar um Frentista:

1. No menu principal, clique em **"➕ Novo Frentista"**
2. Primeiro, clique em **"Preencher Informações"** e cadastre os dados pessoais
3. Depois, preencha o usuário e senha de acesso
4. Clique em **"Criar Acesso"**
5. ✅ Pronto! O frentista pode fazer login

---

## 🔧 Resolução de Problemas

### Problema: "Porta 8081 já está em uso"
**Solução:** Feche qualquer aplicação que esteja usando a porta 8081

### Problema: "Banco de dados pdv_posto não existe"
**Solução:** Crie o banco de dados conforme o **Passo 2**

### Problema: "Senha incorreta ao conectar no banco"
**Solução:** Verifique se alterou a senha corretamente no `application.properties`

### Problema: "Maven não é reconhecido"
**Solução:** 
1. Instale o Maven
2. Adicione ao PATH do sistema
3. Reinicie o Prompt de Comando

### Problema: "Java não encontrado"
**Solução:**
1. Instale o JDK 17
2. Configure a variável JAVA_HOME
3. Reinicie o Prompt de Comando

---

## 📱 Documentação da API

Quando o backend estiver rodando, você pode acessar a documentação Swagger em:

```
http://localhost:8081/swagger-ui/index.html
```

---

## 🎨 Recursos do Sistema

✅ Sistema de Login com controle de acesso (Admin/Frentista)
✅ Gerenciamento completo de bombas de combustível
✅ CRUD completo de todas as entidades
✅ Interface gráfica moderna com Java Swing
✅ API REST documentada com Swagger
✅ Banco de dados PostgreSQL
✅ Cálculo automático de valores nas vendas

---

## 📞 Precisa de Ajuda?

Se encontrar algum problema:

1. Verifique se seguiu todos os passos corretamente
2. Confirme se o backend está rodando antes de abrir o frontend
3. Verifique os logs no terminal para identificar erros
4. Certifique-se de que o banco de dados foi criado

---

## 🎉 Pronto para Usar!

Agora você tem um sistema completo de PDV funcionando! 

**Credenciais padrão:**
- Usuário: `admin`
- Senha: `admin123`

**Importante:** Anote suas credenciais para não esquecer!

---

**Versão:** 2.0
**Data:** Novembro 2025
**Status:** ✅ Pronto para Produção

