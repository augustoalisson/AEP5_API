-- script para criação do banco de dados 
CREATE TABLE tipo_produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(150) NOT NULL
);

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_produto VARCHAR(150) NOT NULL,
    tipo_produto_id INT,
    FOREIGN KEY (tipo_produto_id) REFERENCES tipo_produto(id)
);

CREATE TABLE estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT,
    quantidade DOUBLE(13,2) NOT NULL,
    valor DOUBLE(13,2) NOT NULL,
    data_validade DATE,
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

CREATE TABLE destino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(150) NOT NULL
);

CREATE TABLE tipo_movimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(150) NOT NULL
);

CREATE TABLE movimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_movimentacao_id INT,
    quantidade DOUBLE(13,2) NOT NULL,
    destino_id INT,
    orcamento VARCHAR(150),
    estoque_id INT,
    FOREIGN KEY (tipo_movimentacao_id) REFERENCES tipo_movimentacao(id),
    FOREIGN KEY (destino_id) REFERENCES destino(id),
    FOREIGN KEY (estoque_id) REFERENCES estoque(id)
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(150) NOT NULL,
    nome_usuario VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    data_criacao DATE,
    data_alteracao DATE,
    nivel_acesso VARCHAR(1),
    email VARCHAR(100)
);
