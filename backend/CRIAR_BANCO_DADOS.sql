-- ========================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS
-- PDV POSTO DE COMBUSTÍVEL - PROJETO DO AMIGO
-- ========================================
-- Este banco é SEPARADO do projeto original
-- Nome: pdv_posto (não conflita com pdvpostocombustivel)
-- ========================================

-- 1. Criar o banco de dados
CREATE DATABASE pdv_posto
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- 2. Comentário descritivo
COMMENT ON DATABASE pdv_posto
    IS 'Banco de dados do sistema PDV - Posto de Combustível';

-- ========================================
-- INSTRUÇÕES DE USO:
-- ========================================
-- 1. Abra o pgAdmin 4
-- 2. Conecte no servidor PostgreSQL
-- 3. Clique com botão direito em "Databases"
-- 4. Selecione "Query Tool"
-- 5. Cole este script e execute (F5)
-- 6. O banco "pdv_posto" será criado
-- 7. As tabelas serão criadas automaticamente pelo Spring Boot
-- ========================================

-- OBSERVAÇÃO:
-- Este banco é INDEPENDENTE do "pdvpostocombustivel"
-- Você pode rodar os dois projetos simultaneamente:
-- - Projeto original: porta 8080, banco pdvpostocombustivel
-- - Projeto do amigo: porta 8081, banco pdv_posto
-- ========================================

