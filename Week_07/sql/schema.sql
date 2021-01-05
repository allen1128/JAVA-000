####COMMIT FOR EACH INSERT################

USE camp;
DROP PROCEDURE IF EXISTS create_records;

DELIMITER $$

CREATE procedure create_records(
    total_count INT,
    start_index INT
)

BEGIN
    DECLARE counter INT DEFAULT 1;
    WHILE counter <= total_count DO
        INSERT INTO `commodity`
            values(start_index + counter, 1, 1, 1, CURRENT_TIMESTAMP( ), CURRENT_TIMESTAMP( ));
        SET counter = counter + 1;
    END WHILE;
END$$
DELIMITER ;

####COMMIT AFTER BATCH INSERT################

USE camp;
DROP PROCEDURE IF EXISTS create_records;

DELIMITER $$

CREATE procedure create_records(
    total_count INT,
    start_index INT
)

BEGIN
    DECLARE counter INT DEFAULT 1;
	START TRANSACTION;
    WHILE counter <= total_count DO

        INSERT INTO `commodity`
            values(start_index + counter, 1, 1, 1, CURRENT_TIMESTAMP( ), CURRENT_TIMESTAMP( ));
        SET counter = counter + 1;

    END WHILE;
	COMMIT;
END$$
DELIMITER ;

CALL create_records(1000000, 0);