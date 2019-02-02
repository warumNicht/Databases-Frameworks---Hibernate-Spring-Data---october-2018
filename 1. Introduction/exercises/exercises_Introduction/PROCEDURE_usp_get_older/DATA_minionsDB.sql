
USE MinionsDB;

DROP PROCEDURE IF EXISTS usp_get_older;
DELIMITER $$
CREATE PROCEDURE usp_get_older(minion_id INT(11))
BEGIN

    UPDATE minions AS m
    SET m.age=m.age+1
    WHERE m.id=minion_id;

END $$


