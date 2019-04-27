/*
Navicat MySQL Data Transfer

Source Server         : localhost mysql
Source Server Version : 100137
Source Host           : localhost:3306
Source Database       : dbdev

Target Server Type    : MYSQL
Target Server Version : 100137
File Encoding         : 65001

Date: 2019-04-27 21:47:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_mapping
-- ----------------------------
DROP TABLE IF EXISTS `m_mapping`;
CREATE TABLE `m_mapping` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `m_mapping_ibfk_1` FOREIGN KEY (`status`) REFERENCES `sys_status` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for m_role
-- ----------------------------
DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `m_role_ibfk_1` FOREIGN KEY (`status`) REFERENCES `sys_status` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for sid_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `sid_role_mapping`;
CREATE TABLE `sid_role_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_role` bigint(255) DEFAULT NULL,
  `id_mapping` bigint(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `id_m_role` (`id_role`) USING BTREE,
  KEY `id_m_mapping` (`id_mapping`) USING BTREE,
  KEY `status` (`status`),
  CONSTRAINT `sid_role_mapping_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `m_role` (`id`),
  CONSTRAINT `sid_role_mapping_ibfk_2` FOREIGN KEY (`id_mapping`) REFERENCES `m_mapping` (`id`),
  CONSTRAINT `sid_role_mapping_ibfk_3` FOREIGN KEY (`status`) REFERENCES `sys_status` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for sid_user
-- ----------------------------
DROP TABLE IF EXISTS `sid_user`;
CREATE TABLE `sid_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `passowrd_hash` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `sid_user_ibfk_1` FOREIGN KEY (`status`) REFERENCES `sys_status` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for sid_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sid_user_role`;
CREATE TABLE `sid_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_user` bigint(255) DEFAULT NULL,
  `id_role` bigint(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `id_user` (`id_user`) USING BTREE,
  KEY `id_role` (`id_role`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `sid_user_role_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `sid_user` (`id`),
  CONSTRAINT `sid_user_role_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `m_role` (`id`),
  CONSTRAINT `sid_user_role_ibfk_3` FOREIGN KEY (`status`) REFERENCES `sys_status` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(5000) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for sys_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_status`;
CREATE TABLE `sys_status` (
  `id` bigint(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- View structure for vw_user_mapping
-- ----------------------------
DROP VIEW IF EXISTS `vw_user_mapping`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `vw_user_mapping` AS SELECT
	sid_user.id,
	sid_user.username,
	sid_user.`password`,
	sid_user.passowrd_hash,
	sid_user.`status`,
	sid_user_role.id_role,
	sid_user_role.`status` AS status_user_role,
	m_role.`name` AS name_role,
	m_role.`status` AS status_role,
	sid_role_mapping.id_mapping,
	sid_role_mapping.`status` AS status_role_mapping,
	m_mapping.path,
	m_mapping.`status` AS status_mapping
FROM
	sid_user
LEFT JOIN sid_user_role ON sid_user_role.id_user = sid_user.id
LEFT JOIN m_role ON sid_user_role.id_role = m_role.id
LEFT JOIN sid_role_mapping ON sid_role_mapping.id_role = m_role.id
LEFT JOIN m_mapping ON sid_role_mapping.id_mapping = m_mapping.id ;
