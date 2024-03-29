CREATE DATABASE Prova;

USE Prova;

CREATE TABLE Clienti(
	cod int(11) NOT NULL,
	nome varchar(50) NOT NULL,
	piva varchar(11) NOT NULL,
	PRIMARY KEY (cod)
);

INSERT INTO Clienti (cod,nome,piva) VALUES(1,'aaa','11111111111');
INSERT INTO Clienti (cod,nome,piva) VALUES(2,'bbb','22222222222');
INSERT INTO Clienti (cod,nome,piva) VALUES(3,'ccc','33333333333');
INSERT INTO Clienti (cod,nome,piva) VALUES(4,'ddd','44444444444');
INSERT INTO Clienti (cod,nome,piva) VALUES(5,'eee','55555555555');

CREATE TABLE Fatture (
	cod int(11) NOT NULL,
	importoFattura decimal(10,2) NULL,
	FOREIGN KEY (cod)
	REFERENCES Clienti(cod)
);

INSERT INTO Fatture (cod,importoFattura)VALUES(1,10.50);
INSERT INTO Fatture (cod,importoFattura)VALUES(1,20.35);
INSERT INTO Fatture (cod,importoFattura)VALUES(2,34.00);
INSERT INTO Fatture (cod,importoFattura)VALUES(3,56.50);
INSERT INTO Fatture (cod,importoFattura)VALUES(3,45.00);
INSERT INTO Fatture (cod,importoFattura)VALUES(5,5.00);

CREATE VIEW ClientiConFatture AS 
	SELECT Clienti.cod, Clienti.nome, Clienti.piva, Fatture.importoFattura 
	FROM Clienti INNER JOIN Fatture ON Clienti.cod = Fatture.cod 
	ORDER BY Clienti.nome;
