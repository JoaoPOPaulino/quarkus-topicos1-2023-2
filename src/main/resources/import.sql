-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Inserir dados de Telefone
INSERT INTO telefone (codigoArea, numero) VALUES ('63', '5555-5555');
INSERT INTO telefone (codigoArea, numero) VALUES ('55', '1234-5678');
INSERT INTO telefone (codigoArea, numero) VALUES ('71', '9876-5432');
INSERT INTO telefone (codigoArea, numero) VALUES ('44', '2345-6789');
INSERT INTO Telefone (codigoArea, numero) VALUES ('11', '9999-8888');

-- Inserir dados de Endereco
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado1', 'Cidade1', 'Quadra1', 'Rua1', 123);
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado2', 'Cidade2', 'Quadra2', 'Rua2', 456);
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado3', 'Cidade3', 'Quadra3', 'Rua3', 789);
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado4', 'Cidade4', 'Quadra4', 'Rua4', 101);

-- Inserir dados de Usuario
INSERT INTO usuario (nome, login, senha, perfil) VALUES ('Usuário 1', 'usuario1', 'senha1', 2);
INSERT INTO usuario (nome, login, senha, perfil) VALUES ('Usuário 2', 'usuario2', 'senha2', 1);
INSERT INTO usuario (nome, login, senha, perfil) VALUES ('Usuário 3', 'usuario3', 'senha3', 1);
INSERT INTO usuario (nome, login, senha, perfil) VALUES ('Usuário 4', 'usuario4', 'senha4', 2);

-- Inserir dados de Quarto
INSERT INTO quarto (tipoQuarto, numero, preco, disponivel) VALUES (1, 101, 150.0, 1);
INSERT INTO quarto (tipoQuarto, numero, preco, disponivel) VALUES (2, 102, 200.0, 1);
INSERT INTO quarto (tipoQuarto, numero, preco, disponivel) VALUES (3, 103, 300.0, 0);
INSERT INTO quarto (tipoQuarto, numero, preco, disponivel) VALUES (4, 104, 450.0, 1);

-- Inserir Telefone no Usuario
INSERT INTO usuario_telefone(id_usuario, id_telefone) VALUES(1, 1);
INSERT INTO usuario_telefone(id_usuario, id_telefone) VALUES(2, 2);
INSERT INTO usuario_telefone(id_usuario, id_telefone) VALUES(3, 3);
INSERT INTO usuario_telefone(id_usuario, id_telefone) VALUES(4, 4);
INSERT INTO usuario_telefone(id_usuario, id_telefone) VALUES(1, 5);

-- Inserir Endereco no Usuario
INSERT INTO usuario_endereco(id_usuario, id_endereco) VALUES(1, 1);
INSERT INTO usuario_endereco(id_usuario, id_endereco) VALUES(2, 2);
INSERT INTO usuario_endereco(id_usuario, id_endereco) VALUES(3, 3);
INSERT INTO usuario_endereco(id_usuario, id_endereco) VALUES(4, 4);

