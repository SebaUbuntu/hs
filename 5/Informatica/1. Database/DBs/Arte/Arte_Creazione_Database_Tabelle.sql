CREATE DATABASE  Arte ;

USE Arte;

CREATE TABLE Musei  (
	MM_CodiceMuseo char (3) PRIMARY KEY NOT NULL,
	MM_Nome varchar (100),
	MM_Citta varchar (100)
);

CREATE TABLE Artisti  (
	AR_CodiceArtista char (4) primary key not NULL,
	AR_Nome varchar (100),
	AR_Alias varchar (100),
	AR_DataNascita integer,
	AR_DataMorte integer
);

CREATE TABLE Quadri  (
	QQ_TitoloQuadro varchar (100) primary key not NULL,
	QQ_CodiceArtista char (4), 
	QQ_AnnoEsecuzione integer, 
	QQ_Tecnica varchar (100),
	QQ_Altezza integer,
	QQ_Larghezza integer,
	QQ_CodiceMuseo char (3),
	QQ_Note varchar (100),
    FOREIGN KEY (QQ_CodiceArtista) REFERENCES Artisti(AR_CodiceArtista),
    FOREIGN KEY (QQ_CodiceMuseo) REFERENCES Musei(MM_CodiceMuseo)
);