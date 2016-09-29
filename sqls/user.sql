/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-09-30 00:57:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `nameId` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `endTime` datetime DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_uk_nameId` (`nameId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'root', '2376f53af275455bd0d9f04130a15817', '1', '2016-09-19 14:47:15', null, '11111', '1', '/pics/1475142342452.jpg', '上海市市辖区静安区', 'root@root.com');
INSERT INTO `user` VALUES ('2', '管理员', 'user', '0c4032ad7440e5e431a43cee25e0ed83', '1', '2016-09-19 14:48:10', null, '111', '', '/pics/1475149680220.jpg', '江苏省南京市秦淮区', '');
INSERT INTO `user` VALUES ('3', '测试', 'test', '109831dc00cc744f84457c0af6064691', '1', '2016-09-26 09:29:08', null, null, null, '', null, null);
INSERT INTO `user` VALUES ('6', 'guest', 'guest', '641c79418d6e9d4ab0d6deb58452be71', '1', '2016-09-29 14:28:03', null, '', '', '/pics/1475142590307.jpg', '', '');
