/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-10-05 18:51:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL,
  `category` varchar(255) NOT NULL,
  `key` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `value3` varchar(255) DEFAULT NULL,
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_setting_uk_category_key` (`category`,`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'email', 'protocol', 'smtp', null, null, '2016-10-05 00:29:55');
INSERT INTO `sys_config` VALUES ('2', 'email', 'host', 'localhost', null, null, '2016-10-05 00:29:55');
INSERT INTO `sys_config` VALUES ('3', 'email', 'port', '25', null, null, '2016-10-05 00:29:55');
INSERT INTO `sys_config` VALUES ('4', 'email', 'username', 'help@yx.com', null, null, '2016-10-05 00:29:55');
INSERT INTO `sys_config` VALUES ('5', 'email', 'password', '1', null, null, '2016-10-05 00:29:55');
INSERT INTO `sys_config` VALUES ('6', 'email', 'auth', 'true', null, null, '2016-10-05 00:29:55');
