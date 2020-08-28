/*
Navicat MySQL Data Transfer
Source Server         : mysql
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : practice
Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001
Date: 2020-07-14 13:50:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Sid` varchar(255) DEFAULT NULL,
  `Tid` varchar(255) DEFAULT NULL,
  `Phase` varchar(255) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instruct
-- ----------------------------
DROP TABLE IF EXISTS `instruct`;
CREATE TABLE `instruct` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` varchar(100) DEFAULT NULL,
  `TutorId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ins_message
-- ----------------------------
DROP TABLE IF EXISTS `ins_message`;
CREATE TABLE `ins_message` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TutorId` varchar(100) DEFAULT NULL,
  `Title` varchar(200) DEFAULT NULL,
  `Content` varchar(500) DEFAULT NULL,
  `ReleaseTime` varchar(20) DEFAULT NULL,
  `Phase` INT DEFAULT 0,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jwc_message
-- ----------------------------
DROP TABLE IF EXISTS `jwc_message`;
CREATE TABLE `jwc_message` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ReleaseTime` varchar(20) DEFAULT NULL,
  `Title` varchar(200) DEFAULT NULL,
  `Content` varchar(200) DEFAULT NULL,
  `Phase` INT DEFAULT 0,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for read_jwc_msg
-- ----------------------------
DROP TABLE IF EXISTS `read_jwc_msg`;
CREATE TABLE `read_jwc_msg` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` varchar(100) DEFAULT NULL,
  `TutorId` varchar(100) DEFAULT NULL,
  `ifRead` int(11) DEFAULT 0,
  `ifStudent` int(11) DEFAULT 0,
  `MsgId` int(11) DEFAULT 0,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` varchar(100) DEFAULT NULL,
  `Uid` varchar(100) DEFAULT NULL,
  `StudentName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tutor
-- ----------------------------
DROP TABLE IF EXISTS `tutor`;
CREATE TABLE `tutor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TutorId` varchar(100) DEFAULT NULL,
  `Uid` varchar(100) DEFAULT NULL,
  `TutorName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `read_ins_msg`;
CREATE TABLE `read_ins_msg` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` varchar(100) DEFAULT NULL,
  `ifRead` int(11) DEFAULT 0,
  `MsgId` int(11) DEFAULT 0,
	`Reply` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(100) DEFAULT null,
	`password` VARCHAR(100) DEFAULT null,
	`userid` VARCHAR(100) DEFAULT null,
	PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
