/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : learn

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 17/03/2020 16:10:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission`  (
  `id` int(11) NOT NULL,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接口路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES (1, 'video_update', '/api/video/update');
INSERT INTO `s_permission` VALUES (2, 'video_delete', '/api/video/detele');
INSERT INTO `s_permission` VALUES (3, 'video_add', '/api/video/add');
INSERT INTO `s_permission` VALUES (4, 'order_info', '/api/order;/list');
INSERT INTO `s_permission` VALUES (5, 'user_list', '/api/user/list');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES (1, 'admin', '普通管理员');
INSERT INTO `s_role` VALUES (2, 'root', '超级管理员');
INSERT INTO `s_role` VALUES (3, 'editor', '运营维护');

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission`  (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `permission` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES (1, 3, 1);
INSERT INTO `s_role_permission` VALUES (2, 3, 2);
INSERT INTO `s_role_permission` VALUES (3, 3, 3);
INSERT INTO `s_role_permission` VALUES (4, 2, 1);
INSERT INTO `s_role_permission` VALUES (5, 2, 2);
INSERT INTO `s_role_permission` VALUES (6, 2, 3);
INSERT INTO `s_role_permission` VALUES (7, 2, 4);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` int(11) NOT NULL,
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES (1, '用户1', '123456', NULL);
INSERT INTO `s_user` VALUES (2, '用户2', '123456789', NULL);
INSERT INTO `s_user` VALUES (3, 'jack', '123', NULL);

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role`  (
  `role_id` int(11) NULL DEFAULT NULL,
  `id` int(11) NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `remarks` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES (3, 1, 1, '用户1是普通审核人员');
INSERT INTO `s_user_role` VALUES (1, 2, 3, 'jack是admin');
INSERT INTO `s_user_role` VALUES (2, 3, 3, 'jack是超级管理员');
INSERT INTO `s_user_role` VALUES (3, 4, 2, 'jack是editor');
INSERT INTO `s_user_role` VALUES (1, 5, 2, '用户2是admin');

SET FOREIGN_KEY_CHECKS = 1;
