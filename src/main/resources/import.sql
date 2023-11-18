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

-- Inserir dados de Comentario
INSERT INTO comentario (conteudo, dataCriacao, id_usuarios) VALUES ('Comentário 1', '2023-09-01 10:00:00', 1);
INSERT INTO comentario (conteudo, dataCriacao, id_usuarios) VALUES ('Comentário 2', '2023-09-02 11:00:00', 2);
INSERT INTO comentario (conteudo, dataCriacao, id_usuarios) VALUES ('Comentário 3', '2023-09-03 12:00:00', 3);
INSERT INTO comentario (conteudo, dataCriacao, id_usuarios) VALUES ('Comentário 4', '2023-09-04 13:00:00', 4);

-- Inserir dados de Reserva
INSERT INTO reserva (dataIncio, dataFim, preco, quantidade, id_quarto, id_usuario) VALUES ('2023-09-10', '2023-09-15', 500.0, 2, 101, 1);
INSERT INTO reserva (dataIncio, dataFim, preco, quantidade, id_quarto, id_usuario) VALUES ('2023-10-01', '2023-10-05', 600.0, 1, 102, 2);
INSERT INTO reserva (dataIncio, dataFim, preco, quantidade, id_quarto, id_usuario) VALUES ('2023-10-10', '2023-10-15', 700.0, 1, 103, 3);

-- Inserir dados de Servico
INSERT INTO servico (nome, descricao, horaInicio, horaFim) VALUES ('Serviço 1', 'Descrição 1', '08:00:00', '10:00:00');
INSERT INTO servico (nome, descricao, horaInicio, horaFim) VALUES ('Serviço 2', 'Descrição 2', '10:00:00', '12:00:00');
INSERT INTO servico (nome, descricao, horaInicio, horaFim) VALUES ('Serviço 3', 'Descrição 3', '12:00:00', '14:00:00');

-- Inserir dados de Pagamento
INSERT INTO pagamento (dataPagamento, valor, tipoPagamento, id_reserva) VALUES ('2023-09-05 15:00:00', 500.0, 1, 1);
INSERT INTO pagamento (dataPagamento, valor, tipoPagamento, id_reserva) VALUES ('2023-10-06 16:00:00', 600.0, 2, 2);
INSERT INTO pagamento (dataPagamento, valor, tipoPagamento, id_reserva) VALUES ('2023-10-16 17:00:00', 700.0, 3, 3);

