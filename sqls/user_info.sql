/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-10-05 18:52:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `salary` double(10,2) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `descn` varchar(100) DEFAULT NULL,
  `photo` varchar(40) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('38', 'Fiona White', '1234.00', '男', 'Edinburgh', null, null);
INSERT INTO `user_info` VALUES ('39', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/login_logo.png', null);
INSERT INTO `user_info` VALUES ('40', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/login_logo.png', null);
INSERT INTO `user_info` VALUES ('41', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('42', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('43', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('44', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('45', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('46', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('47', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('48', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('49', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('50', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('51', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('52', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('53', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('54', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('55', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('56', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('57', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('58', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('59', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('60', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('61', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('62', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('63', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('64', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('65', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('66', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('67', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('68', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('69', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('70', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('71', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('72', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('73', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('74', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('75', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('76', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('77', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('78', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('79', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('80', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('81', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('82', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('83', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('84', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('85', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('86', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', '2016-09-21 21:13:30');
INSERT INTO `user_info` VALUES ('87', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('88', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('89', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('90', 'Fiona White', '1234.00', '男', 'Edinburgh', '/pics/bd_logo.png', null);
INSERT INTO `user_info` VALUES ('92', 'issac hu', '1234.00', '男', 'Edinburgh', '/pics/google.jpg', '2016-09-12 21:13:26');
INSERT INTO `user_info` VALUES ('93', 'issac hu', '1234.00', '男', 'Edinburgh', '/pics/google.jpg', null);
INSERT INTO `user_info` VALUES ('94', 'issac hu', '1234.00', '男', 'Edinburgh', '/pics/google.png', null);
INSERT INTO `user_info` VALUES ('95', 'issac hu', '1234.00', '男', 'Edinburgh', '/pics/google.png', null);
INSERT INTO `user_info` VALUES ('96', 'issac hu', '1234.00', '男', 'Edinburgh', '/pics/google.png', '2016-09-12 21:13:23');
INSERT INTO `user_info` VALUES ('97', 'xx', '12.00', 'd', 'sdsf', '/pics/bd_logo.png', '2016-09-13 23:11:17');
INSERT INTO `user_info` VALUES ('98', 'Fiona White  Fiona White Fiona White Fiona White Fiona White', '1221.00', '女', 'Edinburgh 	Edinburgh 	Edinburgh	Edinburgh	Edinburgh	Edinburgh	Edinburgh', '/pics/google.jpg', '2016-09-15 13:53:15');
INSERT INTO `user_info` VALUES ('99', 'test', '12.00', '女', 'Edinburgh	Edinburgh	Edinburgh Edinburgh', '/pics/login_logo.png', '2016-09-15 13:56:33');
INSERT INTO `user_info` VALUES ('100', '姓名', '121.00', '男', '', '/pics/default_avatar_male.jpg', '0000-00-00 00:00:00');
