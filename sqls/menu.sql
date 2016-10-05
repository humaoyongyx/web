/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-10-05 18:51:01
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, '系统设置', 'glyphicon glyphicon-cog', null, '100');
INSERT INTO `menu` VALUES ('2', '1', '目录设置', 'glyphicon glyphicon-book', '/module/menu/', '1');
INSERT INTO `menu` VALUES ('3', '1', '用户设置', 'glyphicon glyphicon-user', '/module/user/', '2');
INSERT INTO `menu` VALUES ('4', '1', '角色设置', 'glyphicon glyphicon-th', '/module/role/', '3');
INSERT INTO `menu` VALUES ('5', '1', '资源设置', 'glyphicon glyphicon-globe', '/module/resource/', '4');
INSERT INTO `menu` VALUES ('7', null, '基本设置', 'glyphicon glyphicon-cog', '', '99');
INSERT INTO `menu` VALUES ('14', '7', '用户资料', 'glyphicon glyphicon-user', '/module/userProfile/', '1');
INSERT INTO `menu` VALUES ('15', '7', '密码设置', 'glyphicon glyphicon-credit-card', '/module/password/', '2');
INSERT INTO `menu` VALUES ('18', '1', '调度器设置', 'glyphicon glyphicon-time', '/module2/scheduler/', '5');
INSERT INTO `menu` VALUES ('19', '1', '系统配置', 'glyphicon glyphicon-cog', '/sys/sysConfig/', '6');
