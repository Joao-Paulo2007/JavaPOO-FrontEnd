# 🔄 RESETAR SISTEMA - PRIMEIRO ACESSO

## 🎯 Objetivo

Limpar todos os dados para forçar o cadastro de admin no primeiro acesso.

## ⚠️ Quando Usar

Use este procedimento quando:
- Esqueceu a senha do admin
- Quer começar do zero
- Vai entregar o projeto para outra pessoa
- Quer fazer testes limpos

## 🚀 PASSO A PASSO

### 1️⃣ Limpar Arquivos de Configuração Local

**Execute na pasta do frontend:**

```bash
cd C:\Users\sidney\Documents\Projetos\pdvcombustivel3\frontend
del admin_config.properties
del user_config.properties
```

### 2️⃣ Limpar Banco de Dados

**No pgAdmin 4:**

1. Conecte ao banco `pdv_posto`
2. Abra o Query Tool (F5)
3. Execute o script: `backend/LIMPAR_BANCO.sql`

OU execute manualmente:

```sql
-- Limpar dados
DELETE FROM acessos;
DELETE FROM pessoas;

-- Resetar IDs
ALTER SEQUENCE acessos_id_seq RESTART WITH 1;
ALTER SEQUENCE pessoas_id_seq RESTART WITH 1;
```

### 3️⃣ Executar o Sistema

**Backend:**
```bash
cd C:\Users\sidney\Documents\Projetos\pdvcombustivel3\backend
mvn spring-boot:run
```

**Frontend (novo terminal):**
```bash
cd C:\Users\sidney\Documents\Projetos\pdvcombustivel3\frontend
mvn clean compile exec:java
```

### 4️⃣ Resultado Esperado

✅ **Aparece mensagem:** "Bem-vindo! Cadastre o Admin"
✅ **Tela:** RegisterAdminView (🔑 Cadastro Administrador)
✅ **Você pode:** Cadastrar novo admin do zero!

## 📝 Cadastrar Novo Admin

Na tela que abrir:

1. **Preencha:**
   - Usuário Admin: `admin` (ou qualquer nome)
   - Senha: `admin123` (mínimo 4 caracteres)
   - Confirmar Senha: `admin123`

2. **Clique:** "Cadastrar Admin"

3. **Faça login:** Com as novas credenciais

4. **Pronto!** Sistema funcionando do zero! 🎉

## 🔧 Resolução de Problemas

### Problema: Ainda aparece tela de login ao invés de cadastro

**Causa:** Arquivos de configuração ainda existem

**Solução:**
```bash
cd frontend
del *.properties
```

### Problema: Erro ao limpar banco

**Causa:** Restrições de chave estrangeira

**Solução:** Execute as queries na ordem correta:
1. Primeiro tabelas dependentes (bombas, contatos, etc)
2. Depois acessos
3. Por último pessoas

### Problema: "Usuário já existe"

**Causa:** Banco não foi limpo corretamente

**Solução:** Execute novamente o script LIMPAR_BANCO.sql

## ✅ VERIFICAÇÃO

Confirme que tudo foi limpo:

```sql
-- Ver se há acessos
SELECT COUNT(*) FROM acessos;  -- Deve retornar 0

-- Ver se há pessoas
SELECT COUNT(*) FROM pessoas;  -- Deve retornar 0
```

## 💡 DICA

Salve as novas credenciais em um lugar seguro para não esquecer novamente! 😉

Sugestão de credenciais fáceis de lembrar:
- Usuário: `admin`
- Senha: `admin123`

---

**Status:** ✅ PRONTO PARA USAR
**Data:** 06/11/2025

Execute os passos acima e o sistema voltará ao primeiro acesso! 🎊

