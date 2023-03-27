-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generato il: Gen 11, 2013 alle 18:30
-- Versione del server: 5.5.27
-- Versione PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cinema`
--
CREATE DATABASE cinema;

USE cinema;

-- --------------------------------------------------------

--
-- Struttura della tabella `attore`
--

CREATE TABLE IF NOT EXISTS `attore` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=279332 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `dirige`
--

CREATE TABLE IF NOT EXISTS `dirige` (
  `film` int(11) DEFAULT '0',
  `regista` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `film`
--

CREATE TABLE IF NOT EXISTS `film` (
  `titolo` varchar(100) DEFAULT NULL,
  `titoloOriginale` varchar(100) DEFAULT NULL,
  `durata` int(11) DEFAULT '0',
  `anno` varchar(4) DEFAULT NULL,
  `stato` varchar(50) DEFAULT NULL,
  `pellicola` varchar(3) DEFAULT NULL,
  `nota` varchar(255) DEFAULT NULL,
  `soggetto` varchar(255) DEFAULT NULL,
  `sceneggiatura` varchar(255) DEFAULT NULL,
  `montaggio` varchar(255) DEFAULT NULL,
  `fotografia` varchar(255) DEFAULT NULL,
  `musica` varchar(255) DEFAULT NULL,
  `scenografia` varchar(255) DEFAULT NULL,
  `produzione` varchar(255) DEFAULT NULL,
  `distribuzione` varchar(255) DEFAULT NULL,
  `trama` longtext,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `titoloMaiuscolo` varchar(80) DEFAULT NULL,
  `genere` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `genere` (`genere`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=59853 ;

--
-- RELATIONS FOR TABLE `film`:
--   `genere`
--       `genere` -> `ID`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `filmpremio`
--

CREATE TABLE IF NOT EXISTS `filmpremio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `titoloDelFilm` varchar(255) DEFAULT NULL,
  `titoloOriginaleDelFilm` varchar(255) DEFAULT NULL,
  `premio` int(11) DEFAULT '0',
  `film` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21146 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `genere`
--

CREATE TABLE IF NOT EXISTS `genere` (
  `nome` varchar(50) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=97 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `manifestazione`
--

CREATE TABLE IF NOT EXISTS `manifestazione` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nomeSintetico` varchar(20) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `premio`
--

CREATE TABLE IF NOT EXISTS `premio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `anno` int(11) DEFAULT '0',
  `premiato` int(11) DEFAULT '0',
  `concorso` int(11) DEFAULT '0',
  `dato` varchar(255) DEFAULT NULL,
  `completo` varchar(255) DEFAULT NULL,
  `tipologia` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20884 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `recita`
--

CREATE TABLE IF NOT EXISTS `recita` (
  `film` int(11) DEFAULT '0',
  `attore` int(11) DEFAULT '0',
  KEY `film` (`film`),
  KEY `attore` (`attore`),
  KEY `film_2` (`film`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `regista`
--

CREATE TABLE IF NOT EXISTS `regista` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `cognomeNome` varchar(50) DEFAULT NULL,
  `cognome` varchar(50) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21224 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `tipologia`
--

CREATE TABLE IF NOT EXISTS `tipologia` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) DEFAULT NULL,
  `tipo` int(11) DEFAULT '0',
  `manifestazione` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=654 ;

--
-- Definizione chiavi esterne
--

ALTER TABLE film 
  ADD FOREIGN KEY (genere) REFERENCES genere (ID);
  
ALTER TABLE recita 
  ADD FOREIGN KEY (film) REFERENCES film (ID),
  ADD FOREIGN KEY (attore) REFERENCES attore (ID);
  
ALTER TABLE dirige 
  ADD FOREIGN KEY (film) REFERENCES film (ID),
  ADD FOREIGN KEY (regista) REFERENCES regista (ID);
  
ALTER TABLE filmpremio 
  ADD FOREIGN KEY (film) REFERENCES film (ID),
  ADD FOREIGN KEY (premio) REFERENCES premio (ID);
  
ALTER TABLE premio 
  ADD FOREIGN KEY (tipologia) REFERENCES tipologia (ID);
  
ALTER TABLE tipologia
  ADD FOREIGN KEY (manifestazione) REFERENCES manifestazione (ID);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
