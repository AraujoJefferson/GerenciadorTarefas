DROP TABLE IF EXISTS Departamento CASCADE;
DROP TABLE IF EXISTS Pessoa CASCADE;
DROP TABLE IF EXISTS Tarefa CASCADE;
DROP SEQUENCE IF EXISTS hibernate_sequence CASCADE;

CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE Departamento(id BIGINT GENERATED BY DEFAULT AS IDENTITY
					(START WITH 4 INCREMENT BY 1),
							titulo VARCHAR(255), 
							PRIMARY KEY(id));

CREATE TABLE Pessoa(id BIGINT GENERATED BY DEFAULT AS IDENTITY
					(START WITH 7 INCREMENT BY 1),
					id_departamento BIGINT,
					nome VARCHAR(255), 
					PRIMARY KEY(id),
					CONSTRAINT fk_departamento
				    	FOREIGN KEY(id_departamento) 
					  		REFERENCES Departamento(id)
					  			ON DELETE SET NULL
					);

CREATE TABLE Tarefa(id BIGINT GENERATED BY DEFAULT AS IDENTITY
					(START WITH 1011 INCREMENT BY 1),
					titulo VARCHAR(255), 
					descricao VARCHAR(255),
					prazo DATE, 
					id_departamento BIGINT, 
					duracao BIGINT, 
					id_pessoa BIGINT,
					finalizado BOOLEAN, 
					PRIMARY KEY(id),
					CONSTRAINT fk_departamento
				    	FOREIGN KEY(id_departamento) 
					  		REFERENCES Departamento(id)
					  			ON DELETE SET NULL
					,
					CONSTRAINT fk_pessoa
				    	FOREIGN KEY(id_pessoa) 
					  		REFERENCES Pessoa(id)
					  			ON DELETE SET NULL
					);