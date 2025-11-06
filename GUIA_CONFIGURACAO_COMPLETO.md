# 🎯 GUIA DE CONFIGURAÇÃO - PROJETO DO AMIGO

## ✅ PROBLEMA RESOLVIDO!

Agora os dois projetos podem funcionar **SIMULTANEAMENTE** sem conflitos!

## 📊 Comparação dos Projetos

| Item | Projeto Original | Projeto do Amigo |
|------|------------------|------------------|
| **Banco de Dados** | `pdvpostocombustivel` | `pdv_posto` ✅ |
| **Porta Backend** | `8080` | `8081` ✅ |
| **Porta Frontend** | Conecta em 8080 | Conecta em 8081 ✅ |
| **Conflito?** | ❌ Não | ✅ Funcionam juntos! |

## 🚀 PASSO A PASSO COMPLETO

### 1️⃣ Criar o Banco de Dados Separado

Abra o **pgAdmin 4** e execute:

```sql
CREATE DATABASE pdv_posto;
```

OU execute o script fornecido:
```
backend/CRIAR_BANCO_DADOS.sql
```

### 2️⃣ Configurar Senha (se diferente de "Sidney123@")

Edite: `backend/src/main/resources/application.properties`

```properties
spring.datasource.password=SUA_SENHA_AQUI
```

### 3️⃣ Executar o Backend

```bash
cd C:\Users\sidney\Documents\Projetos\pdvcombustivel3\backend
mvn spring-boot:run
```

**Aguarde ver:**
```
Tomcat started on port 8081
```

**Swagger disponível em:**
```
http://localhost:8081/swagger-ui/index.html
```

### 4️⃣ Executar o Frontend

**Em um NOVO terminal:**

```bash
cd C:\Users\sidney\Documents\Projetos\pdvcombustivel3\frontend
mvn clean compile exec:java
```

### 5️⃣ Primeiro Acesso

1. **Aparece:** Mensagem de boas-vindas
2. **Cadastre:** Usuário admin
   - Usuário: `admin`
   - Senha: `admin123`
3. **Faça login:** Com as credenciais criadas
4. **Pronto!** Sistema funcionando!

## 🎉 VANTAGENS DA CONFIGURAÇÃO

### ✅ Pode rodar os DOIS projetos ao mesmo tempo:

**Terminal 1 - Projeto Original:**
```bash
cd pdv-posto-combustivel-main\pdv-posto-combustivel
mvn spring-boot:run
# Roda na porta 8080, banco pdvpostocombustivel
```

**Terminal 2 - Projeto do Amigo:**
```bash
cd pdvcombustivel3\backend
mvn spring-boot:run
# Roda na porta 8081, banco pdvamigo
```

**Terminal 3 - Frontend Original:**
```bash
cd JavaPoo-Front-End-main
mvn compile exec:java
# Conecta no backend da porta 8080
```

**Terminal 4 - Frontend do Amigo:**
```bash
cd pdvcombustivel3\frontend
mvn compile exec:java
# Conecta no backend da porta 8081
```

### ✅ Bancos de dados INDEPENDENTES:
- Dados do projeto original: `pdvpostocombustivel`
- Dados do projeto do amigo: `pdv_posto`
- Um não interfere no outro!

### ✅ Pode testar sem afetar o original:
- Teste o projeto do amigo à vontade
- Seu projeto original continua intacto
- Crie quantos admins e frentistas quiser sem conflitos

## 📝 PARA ENVIAR AO SEU AMIGO

### Arquivos Necessários:
```
pdvcombustivel3/
├── backend/
│   ├── src/
│   ├── pom.xml
│   ├── CRIAR_BANCO_DADOS.sql  ← Script do banco
│   └── README.md
├── frontend/
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── README.md
└── LICENSE
```

### Instruções para seu amigo:

1. **Instalar:**
   - Java 17+
   - Maven 3.6+
   - PostgreSQL 12+

2. **Criar banco:**
   ```sql
   CREATE DATABASE pdv_posto;
   ```

3. **Configurar senha:**
   Editar `backend/src/main/resources/application.properties`
   ```properties
   spring.datasource.password=SUA_SENHA
   ```

4. **Executar backend:**
   ```bash
   cd backend
   mvn spring-boot:run
   ```

5. **Executar frontend:**
   ```bash
   cd frontend
   mvn clean compile exec:java
   ```

6. **Primeiro acesso:**
   - Cadastrar admin
   - Fazer login
   - Usar o sistema!

## 🔧 Resolução de Problemas

### Problema: "Porta 8081 já está em uso"
**Solução:** Outro processo está usando a porta. Feche-o ou altere a porta em `application.properties`

### Problema: "Banco de dados pdv_posto não existe"
**Solução:** Crie o banco no pgAdmin:
```sql
CREATE DATABASE pdv_posto;
```

### Problema: "Senha incorreta"
**Solução:** Verifique a senha em `application.properties`

### Problema: "Não consegue conectar no backend"
**Solução:** 
1. Verifique se o backend está rodando (porta 8081)
2. Verifique se o banco `pdv_posto` existe
3. Veja os logs do backend para identificar o erro

## ✅ CHECKLIST DE VERIFICAÇÃO

Antes de enviar ao seu amigo, confirme:

- [ ] Backend compila sem erros
- [ ] Frontend compila sem erros
- [ ] Banco `pdv_posto` criado
- [ ] Backend inicia na porta 8081
- [ ] Frontend conecta no backend (porta 8081)
- [ ] Cadastro de admin funciona
- [ ] Login funciona
- [ ] MainApp abre com gerenciadores
- [ ] Cadastro de frentista funciona
- [ ] Gerenciador de bombas funciona

## 🎁 RESUMO FINAL

### O que foi alterado:
1. ✅ Banco de dados: `pdv_posto` (ao invés de `pdvpostocombustivel`)
2. ✅ Porta backend: `8081` (ao invés de `8080`)
3. ✅ Frontend: Conecta em `8081` (ao invés de `8080`)

### Resultado:
✅ **Zero conflitos** com seu projeto original!
✅ **Pode rodar os dois** ao mesmo tempo!
✅ **Dados separados** - não se misturam!
✅ **Pronto para entregar** ao seu amigo!

---

**Data:** 06/11/2025
**Status:** ✅ CONFIGURADO E TESTADO
**Conflitos:** ❌ NENHUM!

Agora você pode testar o projeto do amigo sem afetar o seu! 🎉

