# Programma

## Sistemi informativi e sistemi informatici

- Dati e informazione
- Sistemi informativi e sistemi informatici
- Basi di dati e sistemi di gestione delle basi di dati (DBMS)
- Architettura logica di un sistema di gestione delle basi di dati

## Le basi di dsti relazionali

- Diagrammi Entità/Relazioni
	- Entità
	- Associazioni
	- Tipi di associazioni
	- Attributi
	- Chiavi primarie e chiavi esterne
- Il modello dei dati relazionale
	- Concetti fondamentali del modello relazionale, algebra relazionale
	- La derivazione delle relazioni dal modello E/R
	- Le operazioni relazionali: Select, Project, Join
- Progettazione e normalizzazione di una base di dati relazionale
	- Normalizzazione
		- Dipendenze funzionali
		- 1FN, 2FN e 3FN
	- Integrità referenziale
- Esempi di progettazione di basi di dati relazionali
- Linguaggi per operare su basi di dati relazionali
- Transazioni
	- Proprietà ACID

## Il linguaggio SQL

- Caratteristiche generali del linguaggio SQL
- Identificatori e tipi di dati
- I comandi DDL di SQL: CREATE, ALTER e DROP
- I comandi DML di SQL: INSERT, DELETE, UPDATE
- Il comando SELECT e le operazioni relazionali in SQL
- Le funzioni di aggregazione
- Ordinamenti e raggruppamenti
	- Condizioni sui raggruppamenti
- Condizioni di ricerca (Between, In, Like, Is Null)
- Query nidificate
- Le viste logiche
- I trigger

## Sicurezza e concorrenza nelle basi di dati

- Aspetti generali
- Problemi di concorrenza
- Consistenza
- Accesso concorrente
- Transazioni
- Transazioni e accesso concorrente
- Utilizzo delle transazioni
- Sicurezza delle basi di dati
- Controllo dell’accesso ai dati
- Protezione da accessi non autorizzati

## Database nel web

- Introduzione
- L’interfaccia utente
- La validazione dei dati
- Programmazione web lato client
- L’elaborazione dei dati
- L’output dell’interrogazione
- Programmazione lato server

## Database no-SQL

- Introduzione ai DB No-SQL
	- Vantaggi e svantaggi dei DB No-SQL rispetto ai relazionali
	- JSON e BSON
	- Schemeless e schema dinamico
	- Dal relazionale a documenti e collections

## Attività in laboratorio

### Introduzione al linguaggio C# e al Framework .NET

- Presentazione del Framework.NET. 
- L’ambiente di sviluppo Visual Studio
- Caratteristiche generali del linguaggio C#, differenze con Java
- Programmazione in console con costrutti principali, array (anche 2 dimensioni), 
gestione file
- Programmazione con Windows Form e Controlli principali (ComboBox, Liste e 
Griglie)
- Tecniche per popolare controlli (tipo DataGrid) con oggetto DataTable

### Introduzione alla gestione di database relazionali in linguaggio SQL

- Caratteristiche generali del linguaggio
- Query SQL in ambiente DBMS (MySql)
- Query SQL in linguaggio ospite (C#, Python)
- Comandi DDL in SQL: creazione e modifica di tabelle e relazioni
- I comandi DML in SQL: interrogazione con SELECT e clausole where, group, 
count, order by, in, sum, ecc.
- Introduzione e cenni al motore database MySql e interfaccia di gestione delle 
istanze dei Database (PhpMyAdmin e SqlWorkBench, Creazione, 
Cancellazione, Collegamento di database)

### Gestione di basi di dati relazionali con MySql

- Creazione e apertura di un database
- Definizione e gestione di tabelle
- Inserimento e modifica dei dati
- Definizione delle associazioni fra tabelle
- Query guidate e Query SQL anche interattive, creazione diagrammi
- Cenni a viste, stored procedure e trigger
- Introduzione e cenni alla gestione della sicurezza del DBMS (CREATE USER, 
GRANT) e prove su MySQL 
- Introduzione alla gestione e controllo all’accesso concorrente ai database con 
transazioni (transaction, commit, autocommit, rollback)
- Classe MySqlConnection e tipologie di stringhe di connessione per 
collegamenti a Istanze MySql locali e remote
- Tecniche di gestione disconnessa delle tabelle database e classi principali 
come MySqlCommand, MySqlDataAdapter, DataTable, DataBinding, 
Parameters

### Database nel web

- Il concetto di web server
- Installazione di Apache, MySql con pacchetto XAMPP e avvio dei servizi
- Linguaggi di programmazione server side per il web (PHP)
- Array normali, globali e associativi
- Creazione di funzioni
- Ricezione parametri in modalità get, post con array globali $_GET, $_POST
- Gestione SESSION
- Gestione file con php
- Interazione fra PHP e DataBase MySql con classe MySqli (comandi query, 
fetch_assoc)
- Gestione sql injection (prepare(), bind_param(), execute())
- Tecnologia Ajax in javascript e jquery
