CREATE DATABASE IF NOT EXISTS `camp` DEFAULT CHARACTER SET utf8mb4;

USE `camp`;

CREATE TABLE `account_even` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `rmb_wallet` float DEFAULT NULL,
  `frozen_rmb_wallet` float DEFAULT NULL,
  `usd_wallet` float DEFAULT NULL,
  `frozen_usd_wallet` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `account_odd` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `rmb_wallet` float DEFAULT NULL,
  `frozen_rmb_wallet` float DEFAULT NULL,
  `usd_wallet` float DEFAULT NULL,
  `frozen_usd_wallet` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO `account_even`
(`id`,
`name`,
`rmb_wallet`,
`frozen_rmb_wallet`,
`usd_wallet`,
`frozen_usd_wallet`)
VALUES
(2, 'sean', 0, 0, 10, 0);

INSERT INTO `account_odd`
(`id`,
`name`,
`rmb_wallet`,
`frozen_rmb_wallet`,
`usd_wallet`,
`frozen_usd_wallet`)
VALUES
(1, 'tim', 700, 0, 0, 0);