CREATE DATABASE IF NOT EXISTS `camp_1` DEFAULT CHARACTER SET utf8mb4;

USE `camp_1`;

DROP TABLE IF EXISTS `order_1`;
DROP TABLE IF EXISTS `order_2`;
DROP TABLE IF EXISTS `order_3`;
DROP TABLE IF EXISTS `order_4`;
DROP TABLE IF EXISTS `order_5`;
DROP TABLE IF EXISTS `order_6`;
DROP TABLE IF EXISTS `order_7`;
DROP TABLE IF EXISTS `order_8`;
DROP TABLE IF EXISTS `order_9`;
DROP TABLE IF EXISTS `order_10`;
DROP TABLE IF EXISTS `order_11`;
DROP TABLE IF EXISTS `order_12`;
DROP TABLE IF EXISTS `order_13`;
DROP TABLE IF EXISTS `order_14`;
DROP TABLE IF EXISTS `order_15`;
DROP TABLE IF EXISTS `order_16`;

CREATE TABLE `order_1` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_2` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_3` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_4` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_5` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_6` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `order_7` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_8` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `order_9` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_10` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_11` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_12` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE DATABASE IF NOT EXISTS `camp_0` DEFAULT CHARACTER SET utf8mb4;

USE `camp_0`;

DROP TABLE IF EXISTS `order_1`;
DROP TABLE IF EXISTS `order_2`;
DROP TABLE IF EXISTS `order_3`;
DROP TABLE IF EXISTS `order_4`;
DROP TABLE IF EXISTS `order_5`;
DROP TABLE IF EXISTS `order_6`;
DROP TABLE IF EXISTS `order_7`;
DROP TABLE IF EXISTS `order_8`;
DROP TABLE IF EXISTS `order_9`;
DROP TABLE IF EXISTS `order_10`;
DROP TABLE IF EXISTS `order_11`;
DROP TABLE IF EXISTS `order_12`;
DROP TABLE IF EXISTS `order_13`;
DROP TABLE IF EXISTS `order_14`;
DROP TABLE IF EXISTS `order_15`;
DROP TABLE IF EXISTS `order_16`;

CREATE TABLE `order_1` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_2` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_3` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_4` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_5` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_6` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `order_7` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_8` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `order_9` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_10` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_11` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `order_12` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;