/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : db_book

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2016-01-28 16:07:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL auto_increment,
  `bookName` varchar(20) default NULL,
  `author` varchar(20) default NULL,
  `sex` varchar(10) default NULL,
  `price` float(10,2) default NULL,
  `bookTypeId` int(11) default NULL,
  `bookDesc` varchar(1000) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_ID` (`bookTypeId`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`bookTypeId`) REFERENCES `t_booktype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('2', '西游记', '吴承恩', '男', '100.00', '9', '师徒4人不辞辛苦，前往西天拜佛求经的故事。');
INSERT INTO `t_book` VALUES ('3', '红楼梦', '曹雪芹', '男', '200.00', '9', '古典名著，最有价值的艺术作品。');
INSERT INTO `t_book` VALUES ('4', 'java编程思想', '大神', '男', '200.00', '10', 'java编程思想');
INSERT INTO `t_book` VALUES ('5', '谢谢你离开我', '琼瑶', '女', '100.00', '11', '谢谢你离开我');

-- ----------------------------
-- Table structure for t_booktype
-- ----------------------------
DROP TABLE IF EXISTS `t_booktype`;
CREATE TABLE `t_booktype` (
  `id` int(11) NOT NULL auto_increment,
  `bookTypeName` varchar(20) default NULL,
  `bookTypeDesc` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_booktype
-- ----------------------------
INSERT INTO `t_booktype` VALUES ('9', '古典名著', '古典名著书籍');
INSERT INTO `t_booktype` VALUES ('10', '计算机类', '计算机编程类书籍');
INSERT INTO `t_booktype` VALUES ('11', '文学类', '文学类书籍');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `userName` varchar(20) default NULL,
  `password` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'java1234', '123456');
