/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-10-05 18:51:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for scheduler
-- ----------------------------
DROP TABLE IF EXISTS `scheduler`;
CREATE TABLE `scheduler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `group` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cron` varchar(255) NOT NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `params` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0:失效 1：有效',
  `runStatus` int(11) NOT NULL DEFAULT '0' COMMENT '0:停止 1：运行 ',
  `beanName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scheduler
-- ----------------------------
INSERT INTO `scheduler` VALUES ('5', '测试调度器', null, '测试调度器', '0 0 * * * ?', '2016-10-02 16:47:15', 'test', '1', '0', 'simpleJob');
INSERT INTO `scheduler` VALUES ('6', '调度器测试2', null, '调度器测试2', '0 0 * * * ?', '2016-10-02 17:45:11', 'test2', '1', '0', 'simpleJob2');
