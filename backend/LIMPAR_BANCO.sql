-- ========================================
-- LIMPAR BANCO DE DADOS pdv_posto
-- Script para resetar e começar do zero
-- ========================================

-- 1. Limpar tabela de acessos (admins e frentistas)
DELETE FROM acessos;

-- 2. Limpar tabela de pessoas (incluindo admin padrão)
DELETE FROM pessoas;

-- 3. Resetar as sequences (IDs voltam para 1)
ALTER SEQUENCE acessos_id_seq RESTART WITH 1;
ALTER SEQUENCE pessoas_id_seq RESTART WITH 1;

-- 4. Limpar outras tabelas (opcional)
DELETE FROM bombas;
DELETE FROM contatos;
DELETE FROM custos;
DELETE FROM estoques;
DELETE FROM precos;
DELETE FROM produtos;

-- Resetar sequences das outras tabelas
ALTER SEQUENCE bombas_id_seq RESTART WITH 1;
ALTER SEQUENCE contatos_id_seq RESTART WITH 1;
ALTER SEQUENCE custos_id_seq RESTART WITH 1;
ALTER SEQUENCE estoques_id_seq RESTART WITH 1;
ALTER SEQUENCE precos_id_seq RESTART WITH 1;
ALTER SEQUENCE produtos_id_seq RESTART WITH 1;

-- ========================================
-- RESULTADO:
-- ========================================
-- O banco agora está completamente limpo!
-- Próxima execução do frontend irá:
-- 1. Detectar que não há admin
-- 2. Abrir tela de cadastro de admin
-- 3. Permitir cadastrar novo admin
-- ========================================

SELECT 'Banco de dados pdv_posto limpo com sucesso!' as status;

