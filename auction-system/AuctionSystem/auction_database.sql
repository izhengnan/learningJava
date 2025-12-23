-- 创建数据库
CREATE DATABASE IF NOT EXISTS auction DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE auction;

-- 删除已存在的表（如果需要重新创建）
DROP TABLE IF EXISTS auction_order;
DROP TABLE IF EXISTS auction_record;
DROP TABLE IF EXISTS auction_item;
DROP TABLE IF EXISTS user;

-- 1. 用户表（user）
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT COMMENT '用户ID（自增）',
    username VARCHAR(50) NOT NULL COMMENT '用户名（唯一，登录/标识用）',
    password VARCHAR(128) NOT NULL COMMENT '密码（加密存储）',
    role TINYINT NOT NULL DEFAULT 1 COMMENT '角色：0-管理员，1-普通用户（默认1）',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 2. 拍品表（auction_item）
CREATE TABLE auction_item (
    id BIGINT AUTO_INCREMENT COMMENT '拍品ID（自增）',
    title VARCHAR(100) NOT NULL COMMENT '拍品名称',
    image VARCHAR(255) COMMENT '拍品图片URL（单张）',
    initial_price DECIMAL(10,2) NOT NULL COMMENT '起拍价（单位：元）',
    description VARCHAR(500) COMMENT '拍品描述',
    start_time DATETIME NOT NULL COMMENT '起拍时间',
    end_time DATETIME NOT NULL COMMENT '结束时间',
    current_max_price DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '当前最高价（默认=起拍价）',
    current_max_user_id BIGINT COMMENT '当前最高出价用户ID（默认null）',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-未开始，1-竞拍中，2-已结束',
    listing_status TINYINT NOT NULL DEFAULT 1 COMMENT '上架状态：0-下架，1-上架',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_status (status),
    KEY idx_listing_status (listing_status),
    KEY idx_start_time (start_time),
    KEY idx_end_time (end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='拍品表';

-- 3. 竞拍记录表（auction_record）
CREATE TABLE auction_record (
    id BIGINT AUTO_INCREMENT COMMENT '记录ID（自增）',
    item_id BIGINT NOT NULL COMMENT '关联拍品ID',
    user_id BIGINT NOT NULL COMMENT '出价用户ID',
    bid_price DECIMAL(10,2) NOT NULL COMMENT '出价金额（元）',
    bid_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出价时间',
    PRIMARY KEY (id),
    KEY idx_item_id (item_id),
    KEY idx_user_id (user_id),
    KEY idx_bid_time (bid_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='竞拍记录表';

-- 4. 订单表（auction_order）
CREATE TABLE auction_order (
    id BIGINT AUTO_INCREMENT COMMENT '订单ID',
    item_id BIGINT NOT NULL COMMENT '关联拍品ID',
    user_id BIGINT NOT NULL COMMENT '买家ID',
    deal_price DECIMAL(10,2) NOT NULL COMMENT '成交价格（=拍品最终最高价）',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-待付款，1-已完成',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_item_id (item_id),
    KEY idx_user_id (user_id),
    KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- 插入初始管理员用户（用户名：admin，密码：admin123，实际应用中应加密）
INSERT INTO user (username, password, role) VALUES ('admin', '$2a$10$abcdefghijklmnopqrstuvABCDEFGHIJKLMNOPQRSTUVWX', 0);

-- 插入初始普通用户（用户名：user，密码：user123，实际应用中应加密）
INSERT INTO user (username, password, role) VALUES ('user', '$2a$10$abcdefghijklmnopqrstuvABCDEFGHIJKLMNOPQRSTUVWX', 1);