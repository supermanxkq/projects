/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2015-11-19 20:58:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for stuinfo_
-- ----------------------------
DROP TABLE IF EXISTS `stuinfo_`;
CREATE TABLE `stuinfo_` (
  `stuId` int(11) NOT NULL auto_increment,
  `stuName` varchar(255) character set utf8 default NULL,
  `age` int(11) default NULL,
  `sex` varchar(255) character set utf8 default NULL,
  `email` varchar(255) character set utf8 default NULL,
  `mobile` varchar(255) character set utf8 default NULL,
  `address` varchar(255) character set utf8 default NULL,
  `studentDetail` int(11) default NULL,
  PRIMARY KEY  (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuinfo
-- ----------------------------
INSERT INTO `stuinfo_` VALUES ('1', '薛凯琪', '25', '男', '994028591@qq.com', '1231312312', '北京市朝阳区', '1');
INSERT INTO `stuinfo_` VALUES ('2', '薛凯琪2', '25', '男', '994028591@qq.com', '1231312312', '北京市朝阳区', '1');
INSERT INTO `stuinfo_` VALUES ('3', '薛凯琪', '25', '男', '994028591@qq.com', '1231312312', '北京市朝阳区', '1');
INSERT INTO `stuinfo_` VALUES ('4', '徐凯强', '25', '男', '994028591@qq.com', '1231312312', '北京市朝阳区', '1');
INSERT INTO `stuinfo_` VALUES ('5', '张三', '25', '男', '994028591@qq.com', '1231312312', '北京市朝阳区', '2');
INSERT INTO `stuinfo_` VALUES ('6', '薛凯琪', '25', '男', '994028591@qq.com', '1231312312', '北京市朝阳区', '1');
INSERT INTO `stuinfo_` VALUES ('7', '薛凯琪2', '25', '男', '994028591@qq.com', '1231312312', '北京市朝阳区', '1');
INSERT INTO `stuinfo_` VALUES ('8', '徐半仙儿', '22', '男', '994028591@qq.com', '13355612212', '北京', '1');
INSERT INTO `stuinfo_` VALUES ('9', '徐半仙儿2', '23', '女', '123132132@163.com', '13355612212', '上海', '2');
INSERT INTO `stuinfo_` VALUES ('10', '徐半仙儿', '22', '男', '994028591@qq.com', '13355612212', '北京', '1');
INSERT INTO `stuinfo_` VALUES ('11', '徐半仙儿2', '23', '女', '123132132@163.com', '13355612212', '上海', '2');
INSERT INTO `stuinfo_` VALUES ('12', '徐半仙儿', '22', '男', '994028591@qq.com', '13355612212', '北京', '1');
INSERT INTO `stuinfo_` VALUES ('13', '徐半仙儿2', '23', '女', '123132132@163.com', '13355612212', '上海', '2');
