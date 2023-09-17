-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into quartoHotel (numero, tipo, preco, diponivel) values(01, 'Quarto Simples', 85.00, true);
insert into quartoHotel (numero, tipo, preco, diponivel) values(02, 'Quarto Casal', 100.00, false);
insert into quartoHotel (numero, tipo, preco, diponivel) values(15, 'Quarto Suíte', 150.00, false);
insert into quartoHotel (numero, tipo, preco, diponivel) values(45, 'Quarto Presidencial', 250.00, true);
