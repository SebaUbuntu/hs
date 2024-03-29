CREATE DATABASE [Prova] ON PRIMARY 
( NAME = 'Prova', FILENAME = 'E:\Source\SQL\Prova.mdf')
 LOG ON 
( NAME = 'Prova_log', FILENAME = 'E:\Source\SQL\Prova_log.ldf')
GO

USE [Prova]

CREATE TABLE [dbo].[Clienti](
	[cod] [bigint] NOT NULL,
	[nome] [varchar](50) NOT NULL,
	[piva] [char](11) NOT NULL,
	CONSTRAINT pk_clienti 
		PRIMARY KEY(cod)
)

INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(1,'aaa','11111111111')
INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(2,'bbb','22222222222')
INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(3,'ccc','33333333333')
INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(4,'ddd','44444444444')
INSERT INTO [Clienti] ([cod],[nome],[piva])VALUES(5,'eee','55555555555')

CREATE TABLE [dbo].[Fatture](
	[cod] [bigint] NOT NULL,
	[importoFattura] [money] NULL,
	CONSTRAINT fk_clienti
		FOREIGN KEY (cod)
		REFERENCES Clienti(cod)
)

INSERT INTO [fatture] ([cod],[importoFattura])VALUES(1,10.00)
INSERT INTO [fatture] ([cod],[importoFattura])VALUES(1,20.00)
INSERT INTO [fatture] ([cod],[importoFattura])VALUES(2,34.00)
INSERT INTO [fatture] ([cod],[importoFattura])VALUES(3,56.00)
INSERT INTO [fatture] ([cod],[importoFattura])VALUES(3,45.00)

CREATE VIEW [dbo].[ClientiConFatture]
AS
SELECT TOP (100) PERCENT dbo.Clienti.cod, dbo.Clienti.nome, dbo.Clienti.piva, dbo.Fatture.importoFattura
	FROM  dbo.Clienti INNER JOIN dbo.Fatture ON dbo.Clienti.cod = dbo.Fatture.cod
	ORDER BY dbo.Clienti.nome

CREATE FUNCTION fn_FatturePerCliente(@cliente VARCHAR(50))
RETURNS TABLE 
AS
RETURN
(
	SELECT dbo.Clienti.cod, dbo.Clienti.nome, dbo.Clienti.piva, dbo.Fatture.importoFattura
	FROM   dbo.Clienti INNER JOIN dbo.Fatture ON dbo.Clienti.cod = dbo.Fatture.cod
	WHERE  dbo.Clienti.nome = @cliente
)

CREATE FUNCTION [dbo].[fn_TotaleFatturePerCliente](@cliente VARCHAR(50))
RETURNS MONEY
AS
BEGIN
	DECLARE @totale MONEY
	SELECT @totale = SUM (dbo.Fatture.importoFattura)
	FROM   dbo.Clienti INNER JOIN dbo.Fatture ON dbo.Clienti.cod = dbo.Fatture.cod
	WHERE dbo.Clienti.nome = @cliente
	RETURN @totale
END

CREATE PROCEDURE [dbo].[sp_CancellaRecordTabelle]
AS
BEGIN
	DELETE FROM Fatture
	DELETE FROM Clienti
END
