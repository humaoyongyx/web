/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-09-18 23:28:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `menuId` int(11) NOT NULL,
  `action` varchar(255) NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '查看', '2', 'show', '2016-09-18 21:55:36');
INSERT INTO `resource` VALUES ('2', '增加', '2', 'add', '2016-09-18 21:56:04');
INSERT INTO `resource` VALUES ('3', '修改', '2', 'modify', '2016-09-18 21:56:17');
INSERT INTO `resource` VALUES ('4', '删除', '2', 'delete', '2016-09-18 21:56:35');
