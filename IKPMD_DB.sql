# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.13)
# Database: ikpmd
# Generation Time: 2017-02-28 13:39:35 +0000
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
  `term` int(11) NOT NULL,
  `mandatory` tinyint(1) DEFAULT NULL,
  `grade` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;

INSERT INTO `Course` (`id`, `name`, `ects`, `term`, `mandatory`, `grade`)
VALUES
	(1,'irdbms',3,1,1,NULL),
	(2,'ipsen',6,1,1,NULL),
	(3,'iopr3',3,1,1,NULL),
	(4,'iscript',3,2,1,NULL),
	(5,'ipsen3',6,2,1,NULL),
	(6,'irdm',3,2,1,NULL),
	(7,'iqua',3,3,1,NULL),
	(8,'ipsen4',6,3,1,NULL),
	(9,'ilg1',3,3,1,NULL),
	(10,'ieth',3,4,1,NULL),
	(11,'icommh',3,4,1,NULL),
	(12,'ipsen5',6,4,1,NULL),
	(13,'iiad',3,4,1,NULL),
	(14,'itrapp',3,2,0,NULL),
	(15,'ibroz',3,1,0,NULL),
	(16,'istat',3,1,0,NULL),
	(17,'icomas',3,2,0,NULL),
	(18,'idepa',3,1,0,NULL),
	(19,'isdeli',3,1,0,NULL),
	(20,'iwis',3,2,0,NULL),
	(21,'isdema',3,2,0,NULL),
	(22,'ikpmd',3,2,0,NULL),
	(23,'iitps',3,3,0,NULL),
	(24,'ibk5',3,3,0,NULL),
	(25,'ifp2',3,3,0,NULL),
	(26,'itrewa',3,3,0,NULL),
	(27,'ikue',3,4,0,NULL),
	(28,'ikema',3,4,0,NULL),
	(29,'idam',3,4,0,NULL),
	(30,'iframe',3,4,0,NULL),
	(31,'ilnux1',3,4,0,NULL);

/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
