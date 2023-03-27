-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Dic 22, 2012 alle 08:14
-- Versione del server: 5.5.25a
-- Versione PHP: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `prova`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `clienti`
--

CREATE TABLE IF NOT EXISTS `clienti` (
  `cod` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `piva` varchar(11) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `clienti`
--

INSERT INTO `clienti` (`cod`, `nome`, `piva`) VALUES
(1, 'aaa', '11111111111'),
(2, 'bbb', '22222222222'),
(3, 'ccc', '33333333333'),
(4, 'ddd', '44444444444'),
(5, 'eee', '55555555555');

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `clienticonfatture`
--
CREATE TABLE IF NOT EXISTS `clienticonfatture` (
`cod` int(11)
,`nome` varchar(50)
,`piva` varchar(11)
,`importoFattura` decimal(10,2)
);
-- --------------------------------------------------------

--
-- Struttura della tabella `fatture`
--

CREATE TABLE IF NOT EXISTS `fatture` (
  `cod` int(11) NOT NULL,
  `importoFattura` decimal(10,2) DEFAULT NULL,
  KEY `cod` (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `fatture`
--

INSERT INTO `fatture` (`cod`, `importoFattura`) VALUES
(1, 10.50),
(1, 20.35),
(2, 34.00),
(3, 56.50),
(3, 45.00),
(5, 5.00);

-- --------------------------------------------------------

--
-- Struttura per la vista `clienticonfatture`
--
DROP TABLE IF EXISTS `clienticonfatture`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `clienticonfatture` AS select `clienti`.`cod` AS `cod`,`clienti`.`nome` AS `nome`,`clienti`.`piva` AS `piva`,`fatture`.`importoFattura` AS `importoFattura` from (`clienti` join `fatture` on((`clienti`.`cod` = `fatture`.`cod`))) order by `clienti`.`nome`;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `fatture`
--
ALTER TABLE `fatture`
  ADD CONSTRAINT `fatture_ibfk_1` FOREIGN KEY (`cod`) REFERENCES `clienti` (`cod`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
