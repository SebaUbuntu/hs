-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 05, 2023 alle 15:52
-- Versione del server: 10.3.16-MariaDB
-- Versione PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fantacalcio`
--
CREATE DATABASE IF NOT EXISTS `fantacalcio` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `fantacalcio`;

-- --------------------------------------------------------

--
-- Struttura della tabella `fantaleghe`
--

CREATE TABLE `fantaleghe` (
  `fantalega_id` int(11) NOT NULL,
  `nome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `admin_id` int(11) NOT NULL,
  `crediti_iniziali` int(11) NOT NULL,
  `numero_portieri` tinyint(4) NOT NULL,
  `numero_difensori` tinyint(4) NOT NULL,
  `numero_centrocampisti` tinyint(4) NOT NULL,
  `numero_attaccanti` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `fantasquadre`
--

CREATE TABLE `fantasquadre` (
  `fantasquadra_id` int(11) NOT NULL,
  `nome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fantalega_id` int(11) NOT NULL,
  `utente_id` int(11) NOT NULL,
  `crediti` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `giocatori`
--

CREATE TABLE `giocatori` (
  `giocatore_id` int(11) NOT NULL,
  `cognome_nome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `data_nascita` date NOT NULL,
  `posizione` enum('P','D','C','A') COLLATE utf8_unicode_ci NOT NULL,
  `crediti_iniziali` int(11) NOT NULL,
  `crediti_finali` int(11) NOT NULL,
  `squadra_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `giocatori_in_fantasquadre`
--

CREATE TABLE `giocatori_in_fantasquadre` (
  `fantasquadra_id` int(11) NOT NULL,
  `giocatore_id` int(11) NOT NULL,
  `crediti_spesi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `inviti`
--

CREATE TABLE `inviti` (
  `utente_id` int(11) NOT NULL,
  `fantalega_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `nazionalita_giocatori`
--

CREATE TABLE `nazionalita_giocatori` (
  `giocatore_id` int(11) NOT NULL,
  `nazione` enum('Albania','Algeria','Angola','Argentina','Armenia','Aruba','Australia','Austria','Belgio','Bosnia Erzegovina','Brasile','Bulgaria','Burkina Faso','Camerun','Canada','Cile','Cipro','Colombia','Corea Del Sud','Costa D''Avorio','Croazia','Danimarca','Egitto','Finlandia','Francia','Galles','Gambia','Georgia','Germania','Ghana','Grecia','Guadalupa','Guinea','Guinea Bissau','Guinea Equatoriale','Guyana Francese','Indonesia','Inghilterra','Irlanda','Islanda','Italia','Kenya','Kosovo','Lettonia','Lituania','Lussemburgo','Macedonia Del Nord','Mali','Marocco','Messico','Montenegro','Nigeria','Norvegia','Nuova Zelanda','Olanda','Paraguay','Polonia','Portogallo','Repubblica Ceca','Repubblica Centrafricana','Repubblica Democratica Del Congo','Romania','Russia','Scozia','Senegal','Serbia','Sierra Leone','Slovacchia','Slovenia','Spagna','Stati Uniti','Suriname','Svezia','Svizzera','Togo','Tunisia','Turchia','Ucraina','Uruguay','Uzbekistan','Venezuela','Zambia') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `squadre`
--

CREATE TABLE `squadre` (
  `squadra_id` int(11) NOT NULL,
  `nome` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `citta` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `anno_fondazione` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dump dei dati per la tabella `squadre`
--

INSERT INTO `squadre` (`squadra_id`, `nome`, `citta`, `anno_fondazione`) VALUES
(1, 'Milan', 'Milano', 1899),
(2, 'Monza', 'Monza', 1912),
(3, 'Atalanta', 'Bergamo', 1907),
(4, 'Bologna', 'Bologna', 1909),
(5, 'Cremonese', 'Cremona', 1903),
(6, 'Empoli', 'Empoli', 1920),
(7, 'Fiorentina', 'Firenze', 1926),
(8, 'Hellas Verona', 'Verona', 1903),
(9, 'Inter', 'Milano', 1908),
(10, 'Juventus', 'Torino', 1897),
(11, 'Lazio', 'Roma', 1900),
(12, 'Lecce', 'Lecce', 1908),
(13, 'Napoli', 'Napoli', 1926),
(14, 'Roma', 'Roma', 1927),
(15, 'Salernitana', 'Salerno', 1919),
(16, 'Sampdoria', 'Genova', 1946),
(17, 'Sassuolo', 'Modena', 1920),
(18, 'Spezia', 'La Spezia', 1906),
(19, 'Torino', 'Torino', 1906),
(20, 'Udinese', 'Udine', 1896);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `utente_id` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `cognome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `fantaleghe`
--
ALTER TABLE `fantaleghe`
  ADD PRIMARY KEY (`fantalega_id`),
  ADD KEY `fk_fantaleghe_admin_id` (`admin_id`);

--
-- Indici per le tabelle `fantasquadre`
--
ALTER TABLE `fantasquadre`
  ADD PRIMARY KEY (`fantasquadra_id`),
  ADD KEY `fk_fantasquadre_fantaleghe_id` (`fantalega_id`),
  ADD KEY `fk_fantasquadre_utente_id` (`utente_id`);

--
-- Indici per le tabelle `giocatori`
--
ALTER TABLE `giocatori`
  ADD PRIMARY KEY (`giocatore_id`),
  ADD KEY `fk_giocatori_squadra_id` (`squadra_id`);

--
-- Indici per le tabelle `giocatori_in_fantasquadre`
--
ALTER TABLE `giocatori_in_fantasquadre`
  ADD PRIMARY KEY (`fantasquadra_id`,`giocatore_id`),
  ADD KEY `fk_giocatori_in_fantasquadre_giocatore_id` (`giocatore_id`);

--
-- Indici per le tabelle `inviti`
--
ALTER TABLE `inviti`
  ADD PRIMARY KEY (`fantalega_id`,`utente_id`) USING BTREE,
  ADD KEY `fk_inviti_utente_id` (`utente_id`);

--
-- Indici per le tabelle `nazionalita_giocatori`
--
ALTER TABLE `nazionalita_giocatori`
  ADD PRIMARY KEY (`giocatore_id`,`nazione`);

--
-- Indici per le tabelle `squadre`
--
ALTER TABLE `squadre`
  ADD PRIMARY KEY (`squadra_id`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`utente_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `fantaleghe`
--
ALTER TABLE `fantaleghe`
  MODIFY `fantalega_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `fantasquadre`
--
ALTER TABLE `fantasquadre`
  MODIFY `fantasquadra_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `giocatori`
--
ALTER TABLE `giocatori`
  MODIFY `giocatore_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `squadre`
--
ALTER TABLE `squadre`
  MODIFY `squadra_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `utente_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `fantaleghe`
--
ALTER TABLE `fantaleghe`
  ADD CONSTRAINT `fk_fantaleghe_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `utenti` (`utente_id`);

--
-- Limiti per la tabella `fantasquadre`
--
ALTER TABLE `fantasquadre`
  ADD CONSTRAINT `fk_fantasquadre_fantaleghe_id` FOREIGN KEY (`fantalega_id`) REFERENCES `fantaleghe` (`fantalega_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_fantasquadre_utente_id` FOREIGN KEY (`utente_id`) REFERENCES `utenti` (`utente_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `giocatori`
--
ALTER TABLE `giocatori`
  ADD CONSTRAINT `fk_giocatori_squadra_id` FOREIGN KEY (`squadra_id`) REFERENCES `squadre` (`squadra_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `giocatori_in_fantasquadre`
--
ALTER TABLE `giocatori_in_fantasquadre`
  ADD CONSTRAINT `fk_giocatori_in_fantasquadre_fantasquadra_id` FOREIGN KEY (`fantasquadra_id`) REFERENCES `fantasquadre` (`fantasquadra_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_giocatori_in_fantasquadre_giocatore_id` FOREIGN KEY (`giocatore_id`) REFERENCES `giocatori` (`giocatore_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `inviti`
--
ALTER TABLE `inviti`
  ADD CONSTRAINT `fk_inviti_fantalega_id` FOREIGN KEY (`fantalega_id`) REFERENCES `fantaleghe` (`fantalega_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_inviti_utente_id` FOREIGN KEY (`utente_id`) REFERENCES `utenti` (`utente_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `nazionalita_giocatori`
--
ALTER TABLE `nazionalita_giocatori`
  ADD CONSTRAINT `fk_nazionalita_giocatori_giocatore_id` FOREIGN KEY (`giocatore_id`) REFERENCES `giocatori` (`giocatore_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
