/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : goodymdate

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2015-01-27 08:16:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(30) DEFAULT NULL,
  `loginpwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Table structure for `hym_article`
-- ----------------------------
DROP TABLE IF EXISTS `hym_article`;
CREATE TABLE `hym_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `cdate` datetime DEFAULT NULL,
  `color` int(11) DEFAULT NULL,
  `yearid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hym_article
-- ----------------------------
INSERT INTO `hym_article` VALUES ('7', '难道爱一个人有错吗', '难道爱一个人有错吗', '2015-01-26 15:13:02', '1', '1');
INSERT INTO `hym_article` VALUES ('9', '你知不知道，思念一个人的滋味', '你知不知道，思念一个人的滋味', '2015-01-26 15:47:23', '1', '2');
INSERT INTO `hym_article` VALUES ('10', '遇到一个难题', '我不知道怎么办好啊，解决不了', '2015-01-26 15:48:18', '1', '1');
INSERT INTO `hym_article` VALUES ('11', '大约在冬季', '轻轻地我将离开你，请将眼角的泪擦去', '2015-01-27 07:14:51', '0', '2');
INSERT INTO `hym_article` VALUES ('12', '说法是否对双方都', '说法是否对双方都', '2015-01-27 08:00:20', '1', '3');

-- ----------------------------
-- Table structure for `hym_year`
-- ----------------------------
DROP TABLE IF EXISTS `hym_year`;
CREATE TABLE `hym_year` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hym_year
-- ----------------------------
INSERT INTO `hym_year` VALUES ('1', '2013');
INSERT INTO `hym_year` VALUES ('2', '2014');
INSERT INTO `hym_year` VALUES ('3', '2015');
