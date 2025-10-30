-- Criação da tabela acesso
CREATE TABLE IF NOT EXISTS acesso (
    id BIGSERIAL PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Criação da tabela contato
CREATE TABLE IF NOT EXISTS contato (
    id BIGSERIAL PRIMARY KEY,
    telefone VARCHAR(255),
    email VARCHAR(255),
    endereco VARCHAR(255)
);

-- Criação da tabela custo
CREATE TABLE IF NOT EXISTS custo (
    id BIGSERIAL PRIMARY KEY,
    imposto DOUBLE PRECISION,
    frete DOUBLE PRECISION,
    custo_variavel DOUBLE PRECISION,
    custo_fixo DOUBLE PRECISION,
    margem_lucro DOUBLE PRECISION,
    data_processamento TIMESTAMP
);

-- Criação da tabela estoque
CREATE TABLE IF NOT EXISTS estoque (
    id BIGSERIAL PRIMARY KEY,
    quantidade NUMERIC(19, 2),
    local_tanque VARCHAR(255),
    local_endereco VARCHAR(255),
    lote_fabricacao VARCHAR(255),
    data_validade TIMESTAMP
);

-- Criação da tabela pessoa
CREATE TABLE IF NOT EXISTS pessoa (
    id BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(200) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL UNIQUE,
    numero_ctps BIGINT,
    data_nascimento DATE,
    tipo_pessoa VARCHAR(255)
);

-- Criação da tabela preco
CREATE TABLE IF NOT EXISTS preco (
    id BIGSERIAL PRIMARY KEY,
    valor NUMERIC(19, 2),
    data_alteracao TIMESTAMP,
    hora_alteracao TIMESTAMP
);
