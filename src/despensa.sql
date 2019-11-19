-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 19, 2019 at 10:57 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `despensa`
--

-- --------------------------------------------------------

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
CREATE TABLE IF NOT EXISTS `evento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `evento`
--

INSERT INTO `evento` (`id`, `nome`, `data`) VALUES
(1, 'Festa', '2019-11-19'),
(2, 'Festa', '2019-11-19'),
(3, 'Festa', '2019-11-19'),
(4, 'Festa', '2019-11-19'),
(5, 'Festa', '2019-11-19');

-- --------------------------------------------------------

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
CREATE TABLE IF NOT EXISTS `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `validade` date NOT NULL,
  `previsao_falta` date NOT NULL,
  `preco` double NOT NULL,
  `gasto_diario` double NOT NULL,
  `quantidade` double NOT NULL,
  `numero_consumidores` int(11) NOT NULL,
  `id_usuario` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_dono` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produto`
--

INSERT INTO `produto` (`id`, `nome`, `validade`, `previsao_falta`, `preco`, `gasto_diario`, `quantidade`, `numero_consumidores`, `id_usuario`) VALUES
(1, 'Maça', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(2, 'Maça', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(3, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(4, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(5, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(6, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(7, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(8, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(9, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(10, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(11, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1),
(12, 'Banana', '2019-11-20', '2019-11-24', 25, 1, 5, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `produto_evento`
--

DROP TABLE IF EXISTS `produto_evento`;
CREATE TABLE IF NOT EXISTS `produto_evento` (
  `id_produto` int(11) NOT NULL,
  `id_evento` int(11) NOT NULL,
  KEY `id_produto` (`id_produto`,`id_evento`),
  KEY `id_evento` (`id_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produto_evento`
--

INSERT INTO `produto_evento` (`id_produto`, `id_evento`) VALUES
(2, 1),
(2, 1),
(3, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `idade` int(11) NOT NULL,
  `email` varchar(250) NOT NULL,
  `telefone` varchar(250) NOT NULL,
  `afiliacao` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `idade`, `email`, `telefone`, `afiliacao`) VALUES
(1, 'Pedro', 12, 'teste@teste.com', '(31) 9 9795-8935', 'NULL');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Constraints for table `produto_evento`
--
ALTER TABLE `produto_evento`
  ADD CONSTRAINT `produto_evento_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id`),
  ADD CONSTRAINT `produto_evento_ibfk_2` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
