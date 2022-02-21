INSERT INTO Departamento(id, titulo) VALUES (1, 'Financeiro');
INSERT INTO Departamento(id, titulo) VALUES (2, 'Comercial');
INSERT INTO Departamento(id, titulo) VALUES (3, 'Desenvolvimento');

INSERT INTO Pessoa(id, nome, id_departamento) VALUES(1, 'Camila', 1);
INSERT INTO Pessoa(id, nome, id_departamento) VALUES(2, 'Pedro', 2);
INSERT INTO Pessoa(id, nome, id_departamento) VALUES(3, 'Fabiano', 3);
INSERT INTO Pessoa(id, nome, id_departamento) VALUES(4, 'Raquel', 3);
INSERT INTO Pessoa(id, nome, id_departamento) VALUES(5, 'Patricia', 3);
INSERT INTO Pessoa(id, nome, id_departamento) VALUES(6, 'Joaquim', 1);

INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1001, 'Validar NF Janeiro', 'Validar notas recebidas no mês de Janeiro', '15/02/2022', 1, 14, 1, true);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1002, 'Bug 352', 'Corrigir bug 352 na versão 1.25', '10/05/2022', 3, 25, null, false);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1003, 'Liberação da versão 1.24', 'Disponibilizar pacote para testes', '02/02/2022', 3, 2, 3, false);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1004, 'Reunião A', 'Reunião com cliente A para apresentação do produto', '05/02/2022', 2, 5, null, false);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1005, 'Reunião final', 'Fechamento contrato', '28/03/2022', 2, 6, null, false);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1006, 'Pagamento 01/2022', 'Realizar pagamento dos fornecedores', '31/01/2022', 1, 6, 1, true);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1007, 'Bug 401', 'Corrigir bug 401 na versão 1.20', '01/02/2022', 3, 2, 4, true);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1008, 'Bug 399', 'Corrigir bug 399 na versão 1.20', '28/01/2022', 3, 6, 5, true);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1009, 'Reunião B', 'Reunião com cliente B para apresentação do produto', '31/01/2022', 2, 5, 2, true);
INSERT INTO Tarefa(id, titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) VALUES(1010, 'Validar NF Fevereiro', 'Validar notas recebidas no mês de Fevereiro', '15/03/2022', 1, 14, 6, false);
