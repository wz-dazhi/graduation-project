/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.7.28
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : bicycle

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 22/06/2022 16:42:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_bicycle
-- ----------------------------
DROP TABLE IF EXISTS `t_bicycle`;
CREATE TABLE `t_bicycle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` bigint(20) NOT NULL COMMENT '单车分类',
  `name` varchar(20) NOT NULL COMMENT '单车名称',
  `img` longtext COMMENT '单车图片',
  `in_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `state` smallint(1) NOT NULL DEFAULT '1' COMMENT '状态: 1-未借,2-已借,3-需维修,4-报废',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='单车分类表';

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '品牌名称',
  `remain` int(11) NOT NULL DEFAULT '0' COMMENT '剩余数量',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单车采购单价',
  `real_rent` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '租金,单位:元/小时',
  `cash` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '押金',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_name` (`name`) USING HASH COMMENT '名称唯一'
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='单车分类表';

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operator_id` bigint(20) NOT NULL COMMENT '操作人ID',
  `msg` varchar(255) NOT NULL COMMENT '操作信息',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sid` bigint(20) NOT NULL COMMENT '学生ID',
  `bid` bigint(20) NOT NULL COMMENT '单车ID',
  `borrow_time` datetime DEFAULT NULL COMMENT '借车时间',
  `return_time` datetime DEFAULT NULL COMMENT '还车时间',
  `real_rent` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '租金',
  `cash` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '押金',
  `state` smallint(1) NOT NULL DEFAULT '1' COMMENT '状态: 1-未归还, 2-归还, 3-归还(需维修), 4-归还(报废)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(50) NOT NULL COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sex` smallint(1) DEFAULT NULL COMMENT '性别: 1-女, 2-男',
  `id_card` varchar(20) NOT NULL COMMENT '身份证',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) NOT NULL COMMENT '电话',
  `avatar` longtext COMMENT '头像',
  `faculty` varchar(20) DEFAULT NULL COMMENT '院系',
  `major` varchar(20) DEFAULT NULL COMMENT '专业',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `phone` varchar(11) NOT NULL COMMENT '电话',
  `avatar` text COMMENT '头像',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_username` (`username`) USING HASH COMMENT '用户名唯一'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
