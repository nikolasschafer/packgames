-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 
-- Versão do Servidor: 5.5.24-log
-- Versão do PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `ex_aluno`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

CREATE TABLE IF NOT EXISTS `aluno` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identificador de aluno',
  `nome` varchar(100) COLLATE utf8_bin NOT NULL COMMENT 'nome do aluno',
  `idade` int(11) NOT NULL COMMENT 'idade do aluno',
  `curso_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `curso_id` (`curso_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tabela de alunos' AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`id`, `nome`, `idade`, `curso_id`) VALUES
(1, 'wesley', 12, 0),
(2, 'naty', 6, 0),
(3, 'romualdo', 23, 2),
(4, 'DeocrÃ©cio', 19, 1),
(5, 'Zoroastro', 12, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `curso`
--

CREATE TABLE IF NOT EXISTS `curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sigla` varchar(10) COLLATE utf8_bin NOT NULL,
  `nome` varchar(100) COLLATE utf8_bin NOT NULL,
  `descricao` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `curso`
--

INSERT INTO `curso` (`id`, `sigla`, `nome`, `descricao`) VALUES
(1, 'COMP', 'Computação', ''),
(2, 'MAT', 'Matemática', ''),
(3, 'FIS', 'Física', ''),
(4, 'CCO', 'Computacao', 'computacao descricao                    \r\n                ');

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina`
--

CREATE TABLE IF NOT EXISTS `disciplina` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sigla` varchar(10) COLLATE utf8_bin NOT NULL,
  `nome` varchar(100) COLLATE utf8_bin NOT NULL,
  `dia_semana` varchar(10) COLLATE utf8_bin NOT NULL,
  `hora_inicio` varchar(10) COLLATE utf8_bin NOT NULL,
  `hora_fim` varchar(10) COLLATE utf8_bin NOT NULL,
  `curso_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `disciplina`
--

INSERT INTO `disciplina` (`id`, `sigla`, `nome`, `dia_semana`, `hora_inicio`, `hora_fim`, `curso_id`) VALUES
(1, 'null', 'LP2', 'TER', '07:45', '11:45', 1),
(2, 'null', 'BD2', 'quarta', '10:00', '12:00', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `matricula`
--

CREATE TABLE IF NOT EXISTS `matricula` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aluno_id` int(11) NOT NULL,
  `disciplina_id` int(11) NOT NULL,
  `semestre` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `matricula`
--

INSERT INTO `matricula` (`id`, `aluno_id`, `disciplina_id`, `semestre`, `ano`) VALUES
(1, 5, 2, 2, 2017),
(2, 5, 1, 2, 2017),
(3, 5, 2, 1, 2016);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
