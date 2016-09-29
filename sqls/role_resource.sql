/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-09-30 00:57:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `resourceId` int(11) NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_uk_roleId_resourceId` (`roleId`,`resourceId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1', '1', '2016-09-21 19:45:26');
INSERT INTO `role_resource` VALUES ('99', '2', '1', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('100', '2', '2', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('101', '2', '3', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('102', '2', '4', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('103', '2', '5', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('104', '2', '6', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('105', '2', '7', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('106', '2', '8', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('107', '2', '9', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('108', '2', '10', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('109', '2', '11', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('110', '2', '12', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('111', '2', '13', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('112', '2', '14', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('113', '2', '15', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('114', '2', '16', '2016-09-26 09:29:24');
INSERT INTO `role_resource` VALUES ('133', '3', '49', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('134', '3', '50', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('135', '3', '51', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('136', '3', '52', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('137', '3', '53', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('138', '3', '54', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('139', '3', '55', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('140', '3', '56', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('141', '3', '1', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('142', '3', '5', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('143', '3', '9', '2016-09-28 11:28:45');
INSERT INTO `role_resource` VALUES ('144', '4', '49', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('145', '4', '50', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('146', '4', '51', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('147', '4', '52', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('148', '4', '53', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('149', '4', '54', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('150', '4', '55', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('151', '4', '56', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('152', '4', '1', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('153', '4', '2', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('154', '4', '3', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('155', '4', '4', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('156', '4', '5', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('157', '4', '6', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('158', '4', '7', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('159', '4', '8', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('160', '4', '9', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('161', '4', '10', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('162', '4', '11', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('163', '4', '12', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('164', '4', '13', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('165', '4', '14', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('166', '4', '15', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('167', '4', '16', '2016-09-28 12:14:35');
INSERT INTO `role_resource` VALUES ('168', '5', '49', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('169', '5', '50', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('170', '5', '51', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('171', '5', '52', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('172', '5', '53', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('173', '5', '54', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('174', '5', '55', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('175', '5', '56', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('176', '5', '1', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('177', '5', '5', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('178', '5', '9', '2016-09-29 14:16:50');
INSERT INTO `role_resource` VALUES ('179', '5', '13', '2016-09-29 14:16:50');
