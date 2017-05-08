/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-10-05 18:51:16
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `resource_uk_menuId_action` (`menuId`,`action`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '查看', '2', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-09-18 21:55:36');
INSERT INTO `resource` VALUES ('2', '增加', '2', 'add', 'add.*|insert.*|save.*', '2016-09-18 21:56:04');
INSERT INTO `resource` VALUES ('3', '修改', '2', 'modify', 'modfiy.*|update.*|edit.*', '2016-09-18 21:56:17');
INSERT INTO `resource` VALUES ('4', '删除', '2', 'delete', 'delete.*|remove.*', '2016-09-18 21:56:35');
INSERT INTO `resource` VALUES ('5', '查看', '3', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-09-20 18:20:01');
INSERT INTO `resource` VALUES ('6', '增加', '3', 'add', 'add.*|insert.*|save.*', '2016-09-20 18:20:21');
INSERT INTO `resource` VALUES ('7', '修改', '3', 'modify', 'modfiy.*|update.*|edit.*', '2016-09-20 18:20:52');
INSERT INTO `resource` VALUES ('8', '删除', '3', 'delete', 'delete.*|remove.*', '2016-09-20 18:21:58');
INSERT INTO `resource` VALUES ('9', '查看', '4', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-09-24 12:32:24');
INSERT INTO `resource` VALUES ('10', '增加', '4', 'add', 'add.*|insert.*|save.*', '2016-09-24 12:32:24');
INSERT INTO `resource` VALUES ('11', '修改', '4', 'modify', 'modfiy.*|update.*|edit.*', '2016-09-24 12:32:24');
INSERT INTO `resource` VALUES ('12', '删除', '4', 'delete', 'delete.*|remove.*', '2016-09-24 12:32:24');
INSERT INTO `resource` VALUES ('13', '查看', '5', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('14', '增加', '5', 'add', 'add.*|insert.*|save.*', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('15', '修改', '5', 'modify', 'modfiy.*|update.*|edit.*', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('16', '删除', '5', 'delete', 'delete.*|remove.*', '2016-09-20 18:32:32');
INSERT INTO `resource` VALUES ('49', '查看', '14', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-09-27 20:53:40');
INSERT INTO `resource` VALUES ('50', '增加', '14', 'add', 'add.*|insert.*|save.*', '2016-09-27 20:53:40');
INSERT INTO `resource` VALUES ('51', '删除', '14', 'delete', 'delete.*|remove.*', '2016-09-27 20:53:40');
INSERT INTO `resource` VALUES ('52', '修改', '14', 'modfiy', 'modfiy.*|update.*|edit.*', '2016-09-27 20:53:40');
INSERT INTO `resource` VALUES ('53', '查看', '15', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-09-27 20:54:01');
INSERT INTO `resource` VALUES ('54', '增加', '15', 'add', 'add.*|insert.*|save.*', '2016-09-27 20:54:01');
INSERT INTO `resource` VALUES ('55', '删除', '15', 'delete', 'delete.*|remove.*', '2016-09-27 20:54:01');
INSERT INTO `resource` VALUES ('56', '修改', '15', 'modfiy', 'modfiy.*|update.*|edit.*', '2016-09-27 20:54:01');
INSERT INTO `resource` VALUES ('77', '查看', '18', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-10-02 13:01:17');
INSERT INTO `resource` VALUES ('78', '增加', '18', 'add', 'add.*|insert.*|save.*', '2016-10-02 13:01:17');
INSERT INTO `resource` VALUES ('79', '删除', '18', 'delete', 'delete.*|remove.*', '2016-10-02 13:01:17');
INSERT INTO `resource` VALUES ('80', '修改', '18', 'modfiy', 'modfiy.*|update.*|edit.*', '2016-10-02 13:01:17');
INSERT INTO `resource` VALUES ('81', '查看', '19', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-10-02 14:27:21');
INSERT INTO `resource` VALUES ('82', '增加', '19', 'add', 'add.*|insert.*|save.*', '2016-10-02 14:27:21');
INSERT INTO `resource` VALUES ('83', '删除', '19', 'delete', 'delete.*|remove.*', '2016-10-02 14:27:21');
INSERT INTO `resource` VALUES ('84', '修改', '19', 'modfiy', 'modfiy.*|update.*|edit.*', '2016-10-02 14:27:21');
INSERT INTO `resource` VALUES ('93', '查看', '20', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-11-23 10:45:21');
INSERT INTO `resource` VALUES ('94', '增加', '20', 'add', 'add.*|insert.*|save.*', '2016-11-23 10:45:21');
INSERT INTO `resource` VALUES ('95', '删除', '20', 'delete', 'delete.*|remove.*', '2016-11-23 10:45:21');
INSERT INTO `resource` VALUES ('96', '修改', '20', 'modfiy', 'modfiy.*|update.*|edit.*', '2016-11-23 10:45:21');
INSERT INTO `resource` VALUES ('105', '查看', '21', 'show', 'show.*|get.*|find.*|load.*|search.*', '2016-11-23 12:42:40');
INSERT INTO `resource` VALUES ('106', '增加', '21', 'add', 'add.*|insert.*|save.*', '2016-11-23 12:42:40');
INSERT INTO `resource` VALUES ('107', '删除', '21', 'delete', 'delete.*|remove.*', '2016-11-23 12:42:40');
INSERT INTO `resource` VALUES ('108', '修改', '21', 'modfiy', 'modfiy.*|update.*|edit.*', '2016-11-23 12:42:40');
