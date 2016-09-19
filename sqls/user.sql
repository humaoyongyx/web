/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-09-19 17:44:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `nameId` varchar(255) NOT NULL,
  `groupId` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `endTime` datetime DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'root', '1', '1', '2016-09-19 14:47:15', null, null, null, null, null);
INSERT INTO `user` VALUES ('2', 'user', 'user', '2', '1', '2016-09-19 14:48:10', null, null, null, null, null);
