CREATE DATABASE IF NOT EXISTS `camp` DEFAULT CHARACTER SET utf8mb4;

USE `camp`;

DROP TABLE IF EXISTS `commodity`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `id` bigint NOT NULL,
  `name` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `gender` smallint DEFAULT NULL,
  `phone_no` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `postal_code` varchar(45) DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `commodity` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `commodity_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `created_ts` timestamp NULL DEFAULT NULL,
  `updated_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


##############################################
USE camp;
DROP PROCEDURE IF EXISTS create_records;
DELIMITER $$

CREATE procedure create_records(
    total_count INT
)
BEGIN
    DECLARE counter INT DEFAULT 1;

    WHILE counter < total_count DO
        INSERT INTO `commodity`
            values(counter, 1, 1, 1, CURRENT_TIMESTAMP( ), CURRENT_TIMESTAMP( ));
        SET counter = counter + 1;
    END WHILE;

END$$
DELIMITER ;