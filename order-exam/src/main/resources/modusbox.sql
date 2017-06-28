/*
 Navicat Premium Data Transfer

 Source Server         : locahost
 Source Server Type    : MySQL
 Source Server Version : 50505
 Source Host           : 127.0.0.1
 Source Database       : modusbox

 Target Server Type    : MySQL
 Target Server Version : 50505
 File Encoding         : utf-8

 Date: 06/28/2017 18:56:47 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `items`
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `cost` double NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_product_entity_id` (`name`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `items`
-- ----------------------------
BEGIN;
INSERT INTO `items` VALUES ('1', '2017-06-28 18:07:53', '0', '2017-06-28 18:07:53', '100', 'sneakers');
COMMIT;

-- ----------------------------
--  Table structure for `order_item`
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `cost` double NOT NULL,
  `count` int(11) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_order_item_id` (`order_id`,`item_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `order_item_entity_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `order_item`
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES ('2', '2017-06-28 18:50:28', '0', '2017-06-28 18:50:28', '100', '20', '1', '2');
COMMIT;

-- ----------------------------
--  Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `customer_name` varchar(100) NOT NULL,
  `placement_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `orders`
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES ('2', '2017-06-28 18:50:28', '2', '2017-06-28 18:50:29', 'Alex', '2017-04-12 08:00:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
