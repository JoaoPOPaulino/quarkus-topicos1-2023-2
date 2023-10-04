-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Inserir dados de Telefone
INSERT INTO Telefone (codigoArea, numero) VALUES ('63', '5555-5555');
INSERT INTO Telefone (codigoArea, numero) VALUES ('55', '1234-5678');
INSERT INTO Telefone (codigoArea, numero) VALUES ('71', '9876-5432');
INSERT INTO Telefone (codigoArea, numero) VALUES ('44', '2345-6789');
INSERT INTO Telefone (codigoArea, numero) VALUES ('11', '9999-8888');

-- Inserir dados de Endereco
INSERT INTO Endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado1', 'Cidade1', 'Quadra1', 'Rua1', 123);
INSERT INTO Endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado2', 'Cidade2', 'Quadra2', 'Rua2', 456);
INSERT INTO Endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado3', 'Cidade3', 'Quadra3', 'Rua3', 789);
INSERT INTO Endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado4', 'Cidade4', 'Quadra4', 'Rua4', 101);
INSERT INTO Endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado5', 'Cidade5', 'Quadra5', 'Rua5', 202);

-- Inserir dados de Usuario
INSERT INTO Usuario (nome, login, senha) VALUES ('Usuário 1', 'usuario1', 'senha1');
INSERT INTO Usuario (nome, login, senha) VALUES ('Usuário 2', 'usuario2', 'senha2');
INSERT INTO Usuario (nome, login, senha) VALUES ('Usuário 3', 'usuario3', 'senha3');
INSERT INTO Usuario (nome, login, senha) VALUES ('Usuário 4', 'usuario4', 'senha4');
INSERT INTO Usuario (nome, login, senha) VALUES ('Usuário 5', 'usuario5', 'senha5');

-- Inserir dados de Pagamento
INSERT INTO Pagamento (tipoPagamento, reservaId) VALUES ('CARTAO_CREDITO', 1);
INSERT INTO Pagamento (tipoPagamento, reservaId) VALUES ('BOLETO', 2);
INSERT INTO Pagamento (tipoPagamento, reservaId) VALUES ('CARTAO_DEBITO', 3);
INSERT INTO Pagamento (tipoPagamento, reservaId) VALUES ('DINHEIRO', 4);
INSERT INTO Pagamento (tipoPagamento, reservaId) VALUES ('CARTAO_CREDITO', 5);

