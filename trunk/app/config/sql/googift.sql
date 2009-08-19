/*
SQLyog Community Edition- MySQL GUI v7.02 
MySQL - 5.1.33-community-log : Database - googift
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`googift` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `googift`;

/*Table structure for table `acos` */

DROP TABLE IF EXISTS `acos`;

CREATE TABLE `acos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `model` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `foreign_key` int(10) unsigned DEFAULT NULL,
  `alias` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `lft` int(10) DEFAULT NULL,
  `rght` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `acos` */

/*Table structure for table `advices` */

DROP TABLE IF EXISTS `advices`;

CREATE TABLE `advices` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `sender` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `advices` */

insert  into `advices`(`id`,`sender`,`content`,`date`) values (27,'kevin','zheshigeceshizheshigeceshizheshigeceshizheshigeceshi\r\n','2009-07-24 15:42:11'),(28,'è›‹è›‹','这是第一个建议','2009-07-24 15:55:52'),(29,'è›‹è›‹','这是第2个Provider','2009-07-24 15:56:48'),(30,'中国人','阿隆索的积分拉萨发','2009-08-12 11:15:30');

/*Table structure for table `aros` */

DROP TABLE IF EXISTS `aros`;

CREATE TABLE `aros` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `model` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `foreign_key` int(10) unsigned DEFAULT NULL,
  `alias` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `lft` int(10) DEFAULT NULL,
  `rght` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `aros` */

/*Table structure for table `aros_acos` */

DROP TABLE IF EXISTS `aros_acos`;

CREATE TABLE `aros_acos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `aro_id` int(10) unsigned NOT NULL,
  `aco_id` int(10) unsigned NOT NULL,
  `_create` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `_read` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `_update` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `_delete` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `aros_acos` */

/*Table structure for table `facets` */

DROP TABLE IF EXISTS `facets`;

CREATE TABLE `facets` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `is_categorical` tinyint(1) NOT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `facets` */

insert  into `facets`(`id`,`name`,`is_categorical`,`weight`) values (1,'day',1,0.5),(2,'acceptor',1,0.7),(3,'age',0,0.6),(4,'gender',1,0.9);

/*Table structure for table `gift_knowledges` */

DROP TABLE IF EXISTS `gift_knowledges`;

CREATE TABLE `gift_knowledges` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'other',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `gift_knowledges` */

insert  into `gift_knowledges`(`id`,`category`,`title`,`content`,`create_date`,`update_date`) values (1,'GiftChina','中华名族是礼仪之邦','中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦。','2009-07-21','2009-07-21'),(2,'GiftChina','中华名族是礼仪之邦','中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦。','2009-07-21','2009-07-21');

/*Table structure for table `gifts` */

DROP TABLE IF EXISTS `gifts`;

CREATE TABLE `gifts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(8,2) DEFAULT '0.00',
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `keywords` text COLLATE utf8_unicode_ci,
  `vector` text COLLATE utf8_unicode_ci,
  `description` text COLLATE utf8_unicode_ci,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `gifts` */

insert  into `gifts`(`id`,`provider_id`,`name`,`price`,`image_url`,`keywords`,`vector`,`description`,`created`,`modified`) values (16,NULL,'','0.00',NULL,'day{生日|情人节},gender{男};day{生日},age{40~50}','(531:0.5),(541:0.5),(631:0.9);(531:0.5),(1100:0.6),(1110:0.6),(1120:0.6),(1130:0.6)',NULL,'2009-08-05 09:28:50','2009-08-19 03:54:51');

/*Table structure for table `helps` */

DROP TABLE IF EXISTS `helps`;

CREATE TABLE `helps` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `helps` */

insert  into `helps`(`id`,`title`,`content`,`category`) values (5,'啊撒旦法','<p>啊撒旦法受到</p>','啊撒旦法'),(3,'如何使用高级搜索','<p>高级搜索是这样使用的！</p>','使用帮助'),(4,'啊撒旦法','<p>啊撒旦法上的发生</p>','啊撒旦法');

/*Table structure for table `keywords` */

DROP TABLE IF EXISTS `keywords`;

CREATE TABLE `keywords` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(20) CHARACTER SET utf8 NOT NULL,
  `is_primary` tinyint(1) DEFAULT NULL,
  `primary` mediumint(9) DEFAULT NULL,
  `facet_id` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `keywords` */

insert  into `keywords`(`id`,`content`,`is_primary`,`primary`,`facet_id`) values (1,'丈夫',1,NULL,2),(2,'老公',0,1,2),(3,'妻子',1,NULL,2),(4,'老婆',0,3,2),(5,'男朋友',1,NULL,2),(6,'男友',0,5,2),(7,'女朋友',1,NULL,2),(8,'女友',0,7,2),(9,'好朋友',1,NULL,2),(10,'好友',0,9,2),(11,'死党',0,9,2),(12,'普通朋友',1,NULL,2),(13,'朋友',0,12,2),(14,'同事',1,NULL,2),(15,'同僚',0,14,2),(16,'同学',1,NULL,2),(17,'同校',0,16,2),(18,'校友',0,16,2),(19,'领导',1,NULL,2),(20,'经理',0,19,2),(21,'主任',0,19,2),(22,'manager',0,19,2),(23,'leader',0,19,2),(24,'父亲',1,NULL,2),(25,'爸爸',0,24,2),(26,'爸',0,24,2),(27,'母亲',1,NULL,2),(28,'妈妈',0,27,2),(29,'妈',0,27,2),(30,'岳父',1,NULL,2),(31,'丈人',0,30,2),(32,'老丈人',0,30,2),(33,'外父',0,30,2),(34,'岳母',1,NULL,2),(35,'丈母娘',0,34,2),(36,'外母',0,34,2),(37,'儿子',1,NULL,2),(38,'女儿',1,NULL,2),(39,'父辈',1,NULL,2),(40,'舅舅',0,39,2),(41,'叔叔',0,39,2),(42,'伯父',0,39,2),(43,'姨父',0,39,2),(44,'姑父',0,39,2),(45,'母辈',1,NULL,2),(46,'姨姨',0,45,2),(47,'阿姨',0,45,2),(48,'婶婶',0,45,2),(49,'舅妈',0,45,2),(50,'姑姑',0,45,2),(51,'妗母',0,45,2),(52,'妗子',0,45,2),(53,'生日',1,NULL,1),(54,'情人节',1,NULL,1),(55,'七夕',1,NULL,1),(56,'中国情人节',0,55,1),(57,'圣诞',1,NULL,1),(58,'圣诞节',0,57,1),(59,'春节',1,NULL,1),(60,'过年',0,59,1),(61,'元旦',1,NULL,1),(62,'新年',0,61,1),(63,'男',1,NULL,4),(64,'男性',0,63,4),(65,'男的',0,63,4),(66,'女',1,NULL,4),(67,'女性',0,66,4),(68,'女的',0,66,4);

/*Table structure for table `providers` */

DROP TABLE IF EXISTS `providers`;

CREATE TABLE `providers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `introduction` text COLLATE utf8_unicode_ci,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` mediumint(9) NOT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `providers` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`role`,`gender`,`birthday`,`email`,`register_date`) values (21,'admin','0d9e16219860e4f647d61725edca3efa88bd68ec','admin','',NULL,'admin@googift.cn','0000-00-00 00:00:00'),(20,'mucian1','8399ef36673e7f496e267cd7a1218263757706cb','enterprise','',NULL,'hfdang@gmail.com','0000-00-00 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
