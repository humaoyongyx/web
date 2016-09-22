/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-09-22 21:01:54
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
  `url` varchar(255) DEFAULT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '查看', '2', 'show', '/,/show', '2016-09-18 21:55:36');
INSERT INTO `resource` VALUES ('2', '增加', '2', 'add', '/add', '2016-09-18 21:56:04');
INSERT INTO `resource` VALUES ('3', '修改', '2', 'modify', '/modify', '2016-09-18 21:56:17');
INSERT INTO `resource` VALUES ('4', '删除', '2', 'delete', '/delete', '2016-09-18 21:56:35');
INSERT INTO `resource` VALUES ('5', '查看', '3', 'show', '/,/show', '2016-09-20 18:20:01');
INSERT INTO `resource` VALUES ('6', '增加', '3', 'add', '/add', '2016-09-20 18:20:21');
INSERT INTO `resource` VALUES ('7', '修改', '3', 'modify', '/modify', '2016-09-20 18:20:52');
INSERT INTO `resource` VALUES ('8', '删除', '3', 'delete', '/delete', '2016-09-20 18:21:58');
INSERT INTO `resource` VALUES ('9', '查看', '4', 'show', '/,/show', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('10', '增加', '4', 'add', '/add', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('11', '修改', '4', 'modify', '/modify', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('12', '删除', '4', 'delete', '/delete', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('13', '查看', '5', 'show', '/,/show', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('14', '增加', '5', 'add', '/add', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('15', '修改', '5', 'modify', '/modify', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('16', '删除', '5', 'delete', '/delete', '2016-09-20 18:32:32');
