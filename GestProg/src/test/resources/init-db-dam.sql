 -- Adminer 4.8.1 MySQL 5.5.5-10.6.4-MariaDB dump

SET NAMES utf8;
SET
time_zone = '+00:00';
SET
foreign_key_checks = 0;
SET
sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP
DATABASE IF EXISTS `gesdam`;
CREATE
DATABASE `gesdam` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE
`gesdam`;

DROP TABLE IF EXISTS `prueba`;
CREATE TABLE `prueba`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `nombre`     varchar(100) NOT NULL,
    `fecha`      datetime     NOT NULL,
    `nota` double       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


DROP TABLE IF EXISTS `evaluacion`;
CREATE TABLE `evaluacion`
(
    `id_evaluacion` int(11) NOT NULL,
    `id_alumno`    int(11) NOT NULL,
    `fecha`        date NOT NULL,
    `nombre_prueba` varchar(100) NOT NULL,
    `nota` double NOT NULL,
    KEY          `id_evaluacion` (`id_evaluacion`),
    KEY          `id_alumno` (`id_alumno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `alumno`;
CREATE TABLE `alumno`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `nombre`      varchar(30) NOT NULL,
    `apellidos`      varchar(100)   NOT NULL,
    `dni`      varchar(9) NOT NULL,
    `telefono`      varchar(10)  NOT NULL,
    `evaluacion_continua` varchar(5) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
