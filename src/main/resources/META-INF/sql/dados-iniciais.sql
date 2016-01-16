insert into Usuario (id, login, senha, nomeUsuario, matricula, rg, email, perfil, empresa) values(1, 'Administrador',   '123456', 'Administrador do sistema', '10001', '','josecarlostag@gmail.com','ADMIN','Especialista');

insert into Cidade (id, nomeCidade, estado, usuario_id ) values (1,'Taguatinga', 'DF',1);
insert into Cidade (id, nomeCidade, estado, usuario_id ) values (4,'Brasília', 'DF',1);
insert into Cidade (id, nomeCidade, estado, usuario_id ) values (8,'São Paulo', 'SP',1);
insert into Cidade (id, nomeCidade, estado, usuario_id ) values (10,'Rio de Janeiro', 'RJ',1);
insert into Cidade (id, nomeCidade, estado, usuario_id ) values (11,'Goiânia', 'GO',1);
insert into Cidade (id, nomeCidade, estado, usuario_id ) values (12,'São Luis', 'MA',1);
insert into Cidade (id, nomeCidade, estado, usuario_id ) values (13,'Caxias', 'MA',1);

INSERT INTO Cliente (id, email, endereco, codigo, nomeCliente, telefone, cidade_id, usuario_id ) VALUES (1,'josecarlostag@gmail.com', '', '1234', 'José Carlos Araújo da Silva', '(61)9636-3614',1,1);


INSERT INTO Solicitacao (id, data_solicitacao, descricao, ordemServico, statusSolicitacao, tipo, cliente_id, usuario_id ) VALUES (1, '2015-11-30','Entrega de equipamentos', 'E/0001','ABERTA', 'ENTRADA', 1, 1);

INSERT INTO Fornecedor (id, nomeFornecedor, telefone, email, endereco, cidade_id, usuario_id) values (1, 'DELL','4004-4444','dell@dell.com','Asa Norte' ,4 ,1);

INSERT INTO Entrada (id, descricao, data_entrada, solicitacao_id, fornecedor_id, usuario_id) values (1, 'Entregua de computadores DEll', '2015-12-04',1, 1, 1);

INSERT INTO Equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (1, 'Desktop','Computador CoreI5','Dell','I14-342','456767','440069126','Disponível', 1, 1);
INSERT INTO Equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (2, 'Desktop','Computador CoreI5','Dell','I14-342','456768','440068756','Disponível', 1, 1);
INSERT INTO Equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (3, 'Desktop','Computador CoreI5','Dell','I14-342','456769','440069126','Disponível', 1, 1);
INSERT INTO Equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (4, 'Desktop','Notbook CoreI5','Dell','I14-342','456770','445439126','Disponível', 1, 1);
INSERT INTO Equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (5, 'Notbook','Notbook CoreI5','Dell','I14-342','456771','440069126','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (6, 'Notbook','Notbook CoreI5','Dell','I14-342','456772','447644126','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (7, 'Notbook','Notbook CoreI5','Dell','I14-342','456773','440069126','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (8, 'Notbook','Notbook CoreI5','Dell','I14-342','456774','447809126','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (9, 'Monitor','Monitor CoreI5','Dell','I14-342','456775','440068965','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (10, 'Monitor','Monitor CoreI5','Dell','I14-342','456776','440069086','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (11, 'Monitor','Monitor CoreI5','Dell','I14-342','456777','440069935','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (12, 'Monitor','Monitor CoreI5','Dell','I14-342','456778','440069452','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (13, 'Monitor','Monitor CoreI5','Dell','I14-342','456779','440069421','Disponível', 1, 1);
INSERT INTO equipamento (id, categoria, descricao, marca, modelo, patrimonio, serie, statusEquipamento, entrada_id, usuario_id) values (14, 'Monitor','Monitor CoreI5','Dell','I14-342','456780','440069857','Disponível', 1, 1);