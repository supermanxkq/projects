/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2015-11-19 20:58:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for studentdetail
-- ----------------------------
DROP TABLE IF EXISTS `studentdetail`;
CREATE TABLE `studentdetail` (
  `studentDetailId` int(11) NOT NULL auto_increment,
  `hobby` varchar(255) character set utf8 default NULL,
  PRIMARY KEY  (`studentDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of studentdetail
-- ----------------------------
INSERT INTO `studentdetail` VALUES ('1', '体育');
INSERT INTO `studentdetail` VALUES ('2', '音乐');
