-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 11, 2009 at 07:46 AM
-- Server version: 5.1.33
-- PHP Version: 5.2.9-2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `googift`
--

-- --------------------------------------------------------

--
-- Table structure for table `acos`
--

CREATE TABLE IF NOT EXISTS `acos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `model` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `foreign_key` int(10) unsigned DEFAULT NULL,
  `alias` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `lft` int(10) DEFAULT NULL,
  `rght` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `acos`
--


-- --------------------------------------------------------

--
-- Table structure for table `advices`
--

CREATE TABLE IF NOT EXISTS `advices` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `sender` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=30 ;

--
-- Dumping data for table `advices`
--

INSERT INTO `advices` (`id`, `sender`, `content`, `date`) VALUES
(27, 'kevin', 'zheshigeceshizheshigeceshizheshigeceshizheshigeceshi\r\n', '2009-07-24 15:42:11'),
(28, '蛋蛋', '蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅。', '2009-07-24 15:55:52'),
(29, '蛋蛋', '<b>我叫蛋蛋</b>\r\n<ul>\r\n<li>蛋蛋长的很帅</li>\r\n<li>蛋蛋长的很帅</li>\r\n<li>蛋蛋长的很帅</li>\r\n<li>蛋蛋长的很帅</li>\r\n</ul>', '2009-07-24 15:56:48');

-- --------------------------------------------------------

--
-- Table structure for table `aros`
--

CREATE TABLE IF NOT EXISTS `aros` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `model` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `foreign_key` int(10) unsigned DEFAULT NULL,
  `alias` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `lft` int(10) DEFAULT NULL,
  `rght` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `aros`
--


-- --------------------------------------------------------

--
-- Table structure for table `aros_acos`
--

CREATE TABLE IF NOT EXISTS `aros_acos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `aro_id` int(10) unsigned NOT NULL,
  `aco_id` int(10) unsigned NOT NULL,
  `_create` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `_read` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `_update` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `_delete` char(2) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `aros_acos`
--


-- --------------------------------------------------------

--
-- Table structure for table `gifts`
--

CREATE TABLE IF NOT EXISTS `gifts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(8,2) DEFAULT '0.00',
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `keywords` text COLLATE utf8_unicode_ci,
  `description` text COLLATE utf8_unicode_ci,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=20 ;

--
-- Dumping data for table `gifts`
--

INSERT INTO `gifts` (`id`, `provider_id`, `name`, `price`, `image_url`, `keywords`, `description`, `created`, `modified`) VALUES
(16, NULL, '', '0.00', NULL, 'ss', NULL, '2009-08-05 09:28:50', '2009-08-05 09:28:50'),
(17, 7, 'gp2', '0.00', NULL, NULL, '<p>asdf</p>', '2009-08-07 03:51:42', '2009-08-07 03:51:42'),
(18, 7, 'gp1', NULL, NULL, NULL, '<p>asdf</p>', '2009-08-07 03:51:54', '2009-08-07 03:51:54'),
(19, 6, 'asf', '0.00', NULL, NULL, '<p>asd</p>', '2009-08-07 07:33:50', '2009-08-07 07:33:50');

-- --------------------------------------------------------

--
-- Table structure for table `gift_knowledges`
--

CREATE TABLE IF NOT EXISTS `gift_knowledges` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'other',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `gift_knowledges`
--

INSERT INTO `gift_knowledges` (`id`, `category`, `title`, `content`, `create_date`, `update_date`) VALUES
(1, 'GiftChina', '中华名族是礼仪之邦', '中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦。', '2009-07-21', '2009-07-21'),
(2, 'GiftChina', '中华名族是礼仪之邦', '中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦。', '2009-07-21', '2009-07-21');

-- --------------------------------------------------------

--
-- Table structure for table `helps`
--

CREATE TABLE IF NOT EXISTS `helps` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `helps`
--

INSERT INTO `helps` (`id`, `title`, `content`, `category`) VALUES
(5, '啊撒旦法', '<p>啊撒旦法受到</p>', '啊撒旦法'),
(3, '如何使用高级搜索', '<p>高级搜索是这样使用的！</p>', '使用帮助'),
(4, '啊撒旦法', '<p>啊撒旦法上的发生</p>', '啊撒旦法');

-- --------------------------------------------------------

--
-- Table structure for table `providers`
--

CREATE TABLE IF NOT EXISTS `providers` (
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `providers`
--

INSERT INTO `providers` (`id`, `name`, `full_name`, `introduction`, `phone`, `address`, `user_id`, `created`, `modified`) VALUES
(6, 'p1', 'pp11', '<p>asdasdfasdfasfa</p>', 'asdf', 'asdfafasfsdfa', 18, '2009-08-07 02:02:28', '2009-08-07 02:02:28'),
(7, 'p2', 'pp22', '<p>asdfasdfa</p>', 'asd', 'asdf', 18, '2009-08-07 02:02:46', '2009-08-07 02:02:46');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `gender` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `gender`, `birthday`, `email`, `register_date`) VALUES
(8, 'mucian', '4f8e38f6f7b920081179e5c19f4db617', '', '', NULL, NULL, '2009-07-31 15:37:45'),
(9, 'bb', '21ad0bd836b90d08f4cf640b4c298e7c', '', '', NULL, NULL, '2008-08-01 19:37:52'),
(18, 'enter', '4124bc0a9335c27f086f24ba207a4912', 'enterprise', '', '0000-00-00', NULL, '2009-08-03 17:20:41');
