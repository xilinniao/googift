-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2008 年 08 月 02 日 15:07
-- 服务器版本: 5.1.33
-- PHP 版本: 5.2.9-2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- 数据库: `googift`
--

-- --------------------------------------------------------

--
-- 表的结构 `acos`
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
-- 导出表中的数据 `acos`
--


-- --------------------------------------------------------

--
-- 表的结构 `advices`
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
-- 导出表中的数据 `advices`
--

INSERT INTO `advices` (`id`, `sender`, `content`, `date`) VALUES
(27, 'kevin', 'zheshigeceshizheshigeceshizheshigeceshizheshigeceshi\r\n', '2009-07-24 15:42:11'),
(28, '蛋蛋', '蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅蛋蛋长的很帅。', '2009-07-24 15:55:52'),
(29, '蛋蛋', '<b>我叫蛋蛋</b>\r\n<ul>\r\n<li>蛋蛋长的很帅</li>\r\n<li>蛋蛋长的很帅</li>\r\n<li>蛋蛋长的很帅</li>\r\n<li>蛋蛋长的很帅</li>\r\n</ul>', '2009-07-24 15:56:48');

-- --------------------------------------------------------

--
-- 表的结构 `aros`
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
-- 导出表中的数据 `aros`
--


-- --------------------------------------------------------

--
-- 表的结构 `aros_acos`
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
-- 导出表中的数据 `aros_acos`
--


-- --------------------------------------------------------

--
-- 表的结构 `gifts`
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- 导出表中的数据 `gifts`
--

INSERT INTO `gifts` (`id`, `provider_id`, `name`, `price`, `image_url`, `keywords`, `description`, `created`, `modified`) VALUES
(4, 1, 'a second', '3.00', NULL, 'day{Birthday/Christmas},age{<40}', '中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民中国人民', NULL, NULL),
(3, 1, 'æ–°ç¤¼å“', '1.20', '200906250929237625.jpg', 'day{SpringFestival},age{>35}', NULL, '2009-06-25 09:07:43', '2009-06-25 09:07:43'),
(6, 1, 'dandan', '11.00', '200906250929237625.jpg', NULL, NULL, '2009-07-24 07:57:43', '2009-07-24 07:57:43');

-- --------------------------------------------------------

--
-- 表的结构 `gift_knowledges`
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
-- 导出表中的数据 `gift_knowledges`
--

INSERT INTO `gift_knowledges` (`id`, `category`, `title`, `content`, `create_date`, `update_date`) VALUES
(1, 'GiftChina', '中华名族是礼仪之邦', '中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦。', '2009-07-21', '2009-07-21'),
(2, 'GiftChina', '中华名族是礼仪之邦', '中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦，中华名族是礼仪之邦。', '2009-07-21', '2009-07-21');

-- --------------------------------------------------------

--
-- 表的结构 `helps`
--

CREATE TABLE IF NOT EXISTS `helps` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- 导出表中的数据 `helps`
--

INSERT INTO `helps` (`id`, `title`, `content`, `category`) VALUES
(5, '啊撒旦法', '<p>啊撒旦法受到</p>', '啊撒旦法'),
(3, '如何使用高级搜索', '<p>高级搜索是这样使用的！</p>', '使用帮助'),
(4, '啊撒旦法', '<p>啊撒旦法上的发生</p>', '啊撒旦法');

-- --------------------------------------------------------

--
-- 表的结构 `providers`
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- 导出表中的数据 `providers`
--

INSERT INTO `providers` (`id`, `name`, `full_name`, `introduction`, `phone`, `address`, `user_id`, `created`, `modified`) VALUES
(1, '米奇', '西安米旗食品有限公司', '<p><span class="Apple-style-span" style="word-spacing: 0px; font: 16px simsun; text-transform: none; color: rgb(0,0,0); text-indent: 0px; white-space: normal; letter-spacing: normal; border-collapse: separate; orphans: 2; widows: 2; webkit-border-horizontal-spacing: 0px; webkit-border-vertical-spacing: 0px; webkit-text-decorations-in-effect: none; webkit-text-size-adjust: auto; webkit-text-stroke-width: 0px">\r\n<table cellspacing="0" cellpadding="0" width="100%" border="0">\r\n    <tbody>\r\n        <tr>\r\n        </tr>\r\n    </tbody>\r\n</table>\r\n<table height="224" cellspacing="0" cellpadding="0" width="100%" border="0">\r\n    <tbody>\r\n        <tr>\r\n            <td valign="top" width="27%" rowspan="3"><img height="210" alt="2" width="180" src="http://localhost/image/i1_25.gif" /></td>\r\n            <td valign="middle" width="73%" height="70"><span class="STYLE14" style="font-size: 12px; word-spacing: normal; color: rgb(102,102,102); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px; text-align: justify">　　<span class="Apple-converted-space">&nbsp;</span><span class="STYLE17" style="font-size: 12px; word-spacing: normal; color: rgb(138,71,53); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px">米旗1994年创建。是一家以生产月饼、蛋糕、面包、中西式糕点、卤制品为主的全国连锁食品企业。从创立至今，米旗始终坚持以产品品质为前提的经营理念，不断致力于产品创新和对市场的准确把握，持续保持行业先锋地位。</span></span></td>\r\n        </tr>\r\n        <tr>\r\n            <td valign="middle" height="10">\r\n            <table height="1" cellspacing="0" cellpadding="0" width="100%" border="0">\r\n                <tbody>\r\n                    <tr>\r\n                        <td bgcolor="#c9beb4" height="1">&nbsp;</td>\r\n                    </tr>\r\n                </tbody>\r\n            </table>\r\n            </td>\r\n        </tr>\r\n        <tr>\r\n            <td valign="middle" height="90"><span class="STYLE15" style="font-size: 12px; word-spacing: normal; color: rgb(102,102,102); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px; text-align: justify">　　<span class="STYLE18" style="font-size: 12px; word-spacing: normal; color: rgb(105,62,33); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px">1996年，米旗开始区域市场和行业扩张，陆续在西安、哈尔滨、长春、大庆、北京、天津、沈阳、成都、杭州成立10家分公司和一家专业食品研究所，产品形成蛋糕、月饼、面包、中点、西点、营养快餐、制馅、冰淇淋、速冻食品、卤制品等十大品类体系，经营涉足焙烤、快餐、食品原料加工和冷饮五个行业，拥有员工近三千人，在中国北方市场设立400余家专卖店，产品辐射北部、东部、西部数十个大中城市，成长为全国知名品牌。</span></span></td>\r\n        </tr>\r\n    </tbody>\r\n</table>\r\n<table cellspacing="0" cellpadding="0" width="100%" border="0">\r\n    <tbody>\r\n        <tr>\r\n            <td valign="top" align="left">\r\n            <table height="1" cellspacing="0" cellpadding="0" width="100%" border="0">\r\n                <tbody>\r\n                    <tr>\r\n                        <td bgcolor="#c9beb4" height="1">&nbsp;</td>\r\n                    </tr>\r\n                </tbody>\r\n            </table>\r\n            <br />\r\n            <table cellspacing="0" cellpadding="0" width="100%" border="0">\r\n                <tbody>\r\n                    <tr>\r\n                        <td width="50"><img height="38" alt="2" src="http://localhost/image/3_03.gif" width="85" /></td>\r\n                        <td align="center" width="100">&nbsp;</td>\r\n                        <td valign="bottom" width="548">&nbsp;</td>\r\n                    </tr>\r\n                </tbody>\r\n            </table>\r\n            <p class="STYLE13" style="font-size: 12px; word-spacing: normal; color: rgb(105,62,33); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px; text-align: justify">　　年轻的米旗，一直保持稳健的发展步伐。米旗西安、哈尔滨两大现代工业园于2002、2004年相继建立，采用恒温、恒湿、防尘的现代化厂房，引进先进生产设备，包装区域更率先使用十万级净化系统。园区生产条件已达到国际标准，并通过ISO9001质量管理体系、HACCP食品安全管理体系认证。米旗严格按照体系要求，从上游供应商到生产、储运至销售服务进行细致、周密品质管理，确保产品安全、安心、优质。</p>\r\n            <p class="STYLE16" style="font-size: 12px; word-spacing: normal; color: rgb(102,102,102); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px; text-align: justify">　　<span class="STYLE19" style="font-size: 12px; word-spacing: normal; color: rgb(105,62,33); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px">作为行业旗帜，米旗连续5年蝉联中国焙烤行业最高赛事蛋糕、月饼、面包团体和个人全部金奖，米旗月饼连续5年荣获中国&ldquo;国饼十佳&rdquo;称号。目前已有20余名技术人员先后获得&ldquo;全国技术能手&rdquo;称号。2006年，米旗人荣获了国家劳动部授予的&ldquo;全国五一劳动奖章&rdquo;，这是目前焙烤行业唯一通过技术比赛获得的殊荣。2006年9月，米旗月饼又获得&ldquo;中国名牌&rdquo;。</span></p>\r\n            </td>\r\n        </tr>\r\n    </tbody>\r\n</table>\r\n</span></p>', '86521888', '陕西省西安市新城区万寿北路', 0, '2009-07-30 09:02:38', '2009-07-30 09:02:38'),
(2, '米奇', '西安米旗食品有限公司', '<p><span class="Apple-style-span" style="word-spacing: 0px; font: 16px simsun; text-transform: none; color: rgb(0,0,0); text-indent: 0px; white-space: normal; letter-spacing: normal; border-collapse: separate; orphans: 2; widows: 2; webkit-border-horizontal-spacing: 0px; webkit-border-vertical-spacing: 0px; webkit-text-decorations-in-effect: none; webkit-text-size-adjust: auto; webkit-text-stroke-width: 0px">\r\n<table cellspacing="0" cellpadding="0" width="100%" border="0">\r\n    <tbody>\r\n        <tr>\r\n        </tr>\r\n    </tbody>\r\n</table>\r\n<table height="224" cellspacing="0" cellpadding="0" width="100%" border="0">\r\n    <tbody>\r\n        <tr>\r\n            <td valign="top" width="27%" rowspan="3"><img height="210" alt="2" width="180" src="http://localhost/image/i1_25.gif" /></td>\r\n            <td valign="middle" width="73%" height="70"><span class="STYLE14" style="font-size: 12px; word-spacing: normal; color: rgb(102,102,102); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px; text-align: justify">　　<span class="Apple-converted-space">&nbsp;</span><span class="STYLE17" style="font-size: 12px; word-spacing: normal; color: rgb(138,71,53); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px">米旗1994年创建。是一家以生产月饼、蛋糕、面包、中西式糕点、卤制品为主的全国连锁食品企业。从创立至今，米旗始终坚持以产品品质为前提的经营理念，不断致力于产品创新和对市场的准确把握，持续保持行业先锋地位。</span></span></td>\r\n        </tr>\r\n        <tr>\r\n            <td valign="middle" height="10">\r\n            <table height="1" cellspacing="0" cellpadding="0" width="100%" border="0">\r\n                <tbody>\r\n                    <tr>\r\n                        <td bgcolor="#c9beb4" height="1">&nbsp;</td>\r\n                    </tr>\r\n                </tbody>\r\n            </table>\r\n            </td>\r\n        </tr>\r\n        <tr>\r\n            <td valign="middle" height="90"><span class="STYLE15" style="font-size: 12px; word-spacing: normal; color: rgb(102,102,102); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px; text-align: justify">　　<span class="STYLE18" style="font-size: 12px; word-spacing: normal; color: rgb(105,62,33); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px">1996年，米旗开始区域市场和行业扩张，陆续在西安、哈尔滨、长春、大庆、北京、天津、沈阳、成都、杭州成立10家分公司和一家专业食品研究所，产品形成蛋糕、月饼、面包、中点、西点、营养快餐、制馅、冰淇淋、速冻食品、卤制品等十大品类体系，经营涉足焙烤、快餐、食品原料加工和冷饮五个行业，拥有员工近三千人，在中国北方市场设立400余家专卖店，产品辐射北部、东部、西部数十个大中城市，成长为全国知名品牌。</span></span></td>\r\n        </tr>\r\n    </tbody>\r\n</table>\r\n<table cellspacing="0" cellpadding="0" width="100%" border="0">\r\n    <tbody>\r\n        <tr>\r\n            <td valign="top" align="left">\r\n            <table height="1" cellspacing="0" cellpadding="0" width="100%" border="0">\r\n                <tbody>\r\n                    <tr>\r\n                        <td bgcolor="#c9beb4" height="1">&nbsp;</td>\r\n                    </tr>\r\n                </tbody>\r\n            </table>\r\n            <br />\r\n            <table cellspacing="0" cellpadding="0" width="100%" border="0">\r\n                <tbody>\r\n                    <tr>\r\n                        <td width="50"><img height="38" alt="2" width="85" src="http://localhost/image/3_03.gif" /></td>\r\n                        <td align="center" width="100">&nbsp;</td>\r\n                        <td valign="bottom" width="548">&nbsp;</td>\r\n                    </tr>\r\n                </tbody>\r\n            </table>\r\n            <p class="STYLE13" style="font-size: 12px; word-spacing: normal; color: rgb(105,62,33); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px; text-align: justify">　　年轻的米旗，一直保持稳健的发展步伐。米旗西安、哈尔滨两大现代工业园于2002、2004年相继建立，采用恒温、恒湿、防尘的现代化厂房，引进先进生产设备，包装区域更率先使用十万级净化系统。园区生产条件已达到国际标准，并通过ISO9001质量管理体系、HACCP食品安全管理体系认证。米旗严格按照体系要求，从上游供应商到生产、储运至销售服务进行细致、周密品质管理，确保产品安全、安心、优质。</p>\r\n            <p class="STYLE16" style="font-size: 12px; word-spacing: normal; color: rgb(102,102,102); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px; text-align: justify">　　<span class="STYLE19" style="font-size: 12px; word-spacing: normal; color: rgb(105,62,33); line-height: 18px; font-style: normal; font-family: Arial, Helvetica, sans-serif; letter-spacing: 1px">作为行业旗帜，米旗连续5年蝉联中国焙烤行业最高赛事蛋糕、月饼、面包团体和个人全部金奖，米旗月饼连续5年荣获中国&ldquo;国饼十佳&rdquo;称号。目前已有20余名技术人员先后获得&ldquo;全国技术能手&rdquo;称号。2006年，米旗人荣获了国家劳动部授予的&ldquo;全国五一劳动奖章&rdquo;，这是目前焙烤行业唯一通过技术比赛获得的殊荣。2006年9月，米旗月饼又获得&ldquo;中国名牌&rdquo;。</span></p>\r\n            </td>\r\n        </tr>\r\n    </tbody>\r\n</table>\r\n</span></p>', '86521888', '陕西省西安市新城区万寿北路', 0, '2009-07-30 09:04:28', '2009-07-30 09:04:28');

-- --------------------------------------------------------

--
-- 表的结构 `users`
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- 导出表中的数据 `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `gender`, `birthday`, `email`, `register_date`) VALUES
(8, 'mucian', '4f8e38f6f7b920081179e5c19f4db617', '', '', NULL, NULL, '2009-07-31 15:37:45'),
(9, 'bb', '21ad0bd836b90d08f4cf640b4c298e7c', '', '', NULL, NULL, '2008-08-01 19:37:52');
