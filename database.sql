# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.13)
# Database: ikpmd
# Generation Time: 2017-02-16 14:59:48 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Course
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Course`;

CREATE TABLE `Course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `ects` int(11) NOT NULL,
  `grade` varchar(10) DEFAULT NULL,
  `mandatory` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;

INSERT INTO `Course` (`id`, `name`, `ects`, `grade`, `mandatory`)
VALUES
	(1,'irdbms',3,NULL,1),
	(2,'ipsen',6,NULL,1),
	(3,'iopr3',3,NULL,1),
	(4,'iscript',3,NULL,1),
	(5,'ipsen3',6,NULL,1),
	(6,'irdm',3,NULL,1),
	(7,'iqua',3,NULL,1),
	(8,'ipsen4',6,NULL,1),
	(9,'ilg1',3,NULL,1),
	(10,'ieth',3,NULL,1),
	(11,'icommh',3,NULL,1),
	(12,'ipsen5',6,NULL,1),
	(13,'iiad',3,NULL,1),
	(14,'itrapp',3,NULL,0),
	(15,'ibroz',3,NULL,0),
	(16,'istat',3,NULL,0),
	(17,'icomas',3,NULL,0),
	(18,'idepa',3,NULL,0),
	(19,'isdeli',3,NULL,0),
	(20,'iwis',3,NULL,0),
	(21,'isdema',3,NULL,0),
	(22,'ikpmd',3,NULL,0),
	(23,'iitps',3,NULL,0),
	(24,'ibk5',3,NULL,0),
	(25,'ifp2',3,NULL,0),
	(26,'itrewa',3,NULL,0),
	(27,'ikue',3,NULL,0),
	(28,'ikema',3,NULL,0),
	(29,'idam',3,NULL,0),
	(30,'iframe',3,NULL,0),
	(31,'ilnux1',3,NULL,0);

/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
