--使用shipment数据库
use shipment;
-- 用户表建表语句
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` VARCHAR(255) NOT NULL COMMENT '用户名',
  `passwd` VARCHAR(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 管理员表建表语句
CREATE TABLE `admin` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `account` VARCHAR(255) NOT NULL COMMENT '管理员账号',
  `passwd` VARCHAR(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 承运商表建表语句
CREATE TABLE `carrier` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '承运商ID',
  `name` VARCHAR(255) NOT NULL COMMENT '承运商名称',
  `info` VARCHAR(255) NOT NULL COMMENT '承运商信息',
  `passwd` VARCHAR(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 运输单表建表语句
CREATE TABLE `shipment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '运输单ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID（逻辑外键）',
  `carrier` BIGINT NOT NULL COMMENT '承运商ID（逻辑外键）',
  `from` VARCHAR(255) NOT NULL COMMENT '出发地',
  `to` VARCHAR(255) NOT NULL COMMENT '目的地',
  `status` VARCHAR(50) NOT NULL COMMENT '运输状态',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_user_id` (`user_id`),
  INDEX `idx_carrier` (`carrier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;