/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-09-22 12:15:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  `resourceId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, '系统设置', 'glyphicon glyphicon-cog', null, '100', null);
INSERT INTO `menu` VALUES ('2', '1', '目录设置', 'glyphicon glyphicon-book', '/module/menu/', '1', null);
INSERT INTO `menu` VALUES ('3', '1', '用户设置', 'glyphicon glyphicon-user', '/module/user/', '2', null);
INSERT INTO `menu` VALUES ('4', '1', '角色设置', 'glyphicon glyphicon-th', '/module/role/', '3', null);
INSERT INTO `menu` VALUES ('5', '1', '资源设置', 'glyphicon glyphicon-globe', '/module/resource/', '4', null);
