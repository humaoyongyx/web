/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-10-05 18:51:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'root', '2016-09-21 19:45:00');
INSERT INTO `role` VALUES ('2', 'user', '2016-09-24 16:03:48');
INSERT INTO `role` VALUES ('3', '测试组', '2016-09-27 14:45:28');
INSERT INTO `role` VALUES ('4', '管理员', '2016-09-28 12:14:35');
INSERT INTO `role` VALUES ('5', '来宾访问组', '2016-09-29 14:16:50');
INSERT INTO `role` VALUES ('6', '基本角色', '2016-10-01 23:07:30');
