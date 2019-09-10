/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 10/09/2019 14:46:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for my
-- ----------------------------
DROP TABLE IF EXISTS `my`;
CREATE TABLE `my`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序openid',
  `appid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序appid',
  `unionid` int(11) NULL DEFAULT NULL COMMENT 'unionid',
  `mpopenid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号openid',
  `mpappid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号appid',
  `created_at` int(11) NULL DEFAULT NULL COMMENT '创建时间',
  `mpopenid1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '公众号1openid',
  `mpappid1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '公众号1appid',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of my
-- ----------------------------
INSERT INTO `my` VALUES (1, '1', NULL, NULL, NULL, NULL, NULL, '', '');

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('demoApp', 'oauth2-resource', '$2a$10$qLC7HsnDLJaxGYftWYgtMucWL.Nbw/6R1T1uEJZ2oOw/B/2xjddY.', 'all', 'authorization_code,client_credentials,password,implicit,refresh_token', 'http://localhost:1080/', 'ROLE_CLIENT', 36000, 36000, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('demoApp2', 'oauth2-resource2', '$2a$10$qLC7HsnDLJaxGYftWYgtMucWL.Nbw/6R1T1uEJZ2oOw/B/2xjddY.', 'all', 'authorization_code,client_credentials,password,implicit,refresh_token', 'http://baidu.com,http://localhost:1080/', 'ROLE_CLIENT', 36000, 36000, NULL, NULL);

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `series` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `token` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('admin', 'CzXwDEJl+5kJ/c5/0Jrtmg==', 'dqFugH+8TRD9MRVm/xYU6g==', '2019-08-13 08:28:27');
INSERT INTO `persistent_logins` VALUES ('admin', 'h0PGtofcasXm3XI/hcHY/w==', 'SwliDvFqTI2romALYr/Syw==', '2019-08-12 03:54:06');
INSERT INTO `persistent_logins` VALUES ('admin', 'SJD5ZazPWj4457Z+I4z55g==', 'kbLY8sDDsD/XeR+LSVYnPg==', '2019-08-12 03:55:15');
INSERT INTO `persistent_logins` VALUES ('admin', 'TJBzxVMGdNStb1OROXNKxg==', 'xYOxokP7IiNrwqVQvJ9PtA==', '2019-08-12 03:57:00');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `username` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `gender` int(11) NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `is_del` int(1) NULL DEFAULT 0,
  `create_by` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 29 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '$2a$10$Jgtlbad2sSedlAxDzgp3TeNv/H8PJgv4ukVzPr/8ZNU7ctzAO1.Ee', 0, '112', '2019-08-23 08:54:04', 0, NULL);
INSERT INTO `sys_user` VALUES (2, 'admin2', 'admin1', '$2a$10$Jgtlbad2sSedlAxDzgp3TeNv/H8PJgv4ukVzPr/8ZNU7ctzAO1.Ee', 0, '110', '2019-08-23 07:56:46', 0, NULL);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c1` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c2` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c3` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c4` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c5` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_c1234`(`c1`, `c2`, `c3`, `c4`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, 'a1', 'a2', 'a3', 'a4', 'a5');
INSERT INTO `test` VALUES (2, 'b1', 'b2', 'b3', 'b4', 'b5');
INSERT INTO `test` VALUES (3, 'c1', 'c2', 'c3', 'c4', 'c5');
INSERT INTO `test` VALUES (4, 'd1', 'd2', 'd3', 'd4', 'd5');
INSERT INTO `test` VALUES (5, 'e1', 'e2', 'e3', 'e4', 'e5');

SET FOREIGN_KEY_CHECKS = 1;
