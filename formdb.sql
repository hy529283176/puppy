/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - formdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`formdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `formdb`;

/*Table structure for table `auth_role` */

DROP TABLE IF EXISTS `auth_role`;

CREATE TABLE `auth_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `visible` tinyint(4) NOT NULL DEFAULT '1',
  `backupA` varchar(50) DEFAULT NULL,
  `backupB` varchar(50) DEFAULT NULL,
  `backupC` varchar(50) DEFAULT NULL,
  `backupD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `auth_role` */

insert  into `auth_role`(`role_id`,`role_name`,`visible`,`backupA`,`backupB`,`backupC`,`backupD`) values (1,'管理员',1,NULL,NULL,NULL,NULL),(2,'普通成员',1,NULL,NULL,NULL,NULL);

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `uName` varchar(50) NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  `enStatus` tinyint(4) NOT NULL DEFAULT '0',
  `visible` tinyint(4) NOT NULL DEFAULT '1',
  `backupA` varchar(50) DEFAULT NULL,
  `backupB` varchar(50) DEFAULT NULL,
  `backupC` varchar(50) DEFAULT NULL,
  `backupD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `FK_auth_user` (`role_id`),
  CONSTRAINT `FK_auth_user` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `auth_user` */

insert  into `auth_user`(`id`,`username`,`pwd`,`uName`,`role_id`,`enStatus`,`visible`,`backupA`,`backupB`,`backupC`,`backupD`) values (1,'admin','admin','管理员大大',1,1,1,NULL,NULL,NULL,NULL),(2,'usera','usera','李狗剩',2,1,1,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
