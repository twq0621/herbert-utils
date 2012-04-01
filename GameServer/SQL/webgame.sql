/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.0.51a-community-nt : Database - webgame
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`webgame` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `webgame`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) default NULL,
  `AGE` tinyint(4) default NULL,
  `EMAIL` varchar(100) default NULL,
  `SOURCE` varchar(100) default NULL,
  `CREATE_DATE` date default NULL,
  `LAST_LOGIN_DATE` date default NULL,
  `FLAG` tinyint(4) default '0',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;



CREATE DATABASE /*!32312 IF NOT EXISTS*/`webgame` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `webgame2`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) default NULL,
  `AGE` tinyint(4) default NULL,
  `EMAIL` varchar(100) default NULL,
  `SOURCE` varchar(100) default NULL,
  `CREATE_DATE` date default NULL,
  `LAST_LOGIN_DATE` date default NULL,
  `FLAG` tinyint(4) default '0',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
