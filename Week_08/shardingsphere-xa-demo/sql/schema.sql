CREATE DATABASE IF NOT EXISTS `camp` DEFAULT CHARACTER SET utf8mb4;

USE `camp`;

DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `storage`;

CREATE TABLE `order` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `storage` (
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;