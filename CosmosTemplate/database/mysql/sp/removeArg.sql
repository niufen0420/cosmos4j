DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`removeArg`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`removeArg`(IN domain VARCHAR(100), IN command VARCHAR(100), IN argIndex INT)
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	START TRANSACTION;
	DELETE FROM tb_cos_command_arg WHERE DOMAIN = domain AND COMMAND = command AND ARG_INDEX = argIndex + 1;
	UPDATE tb_cos_command_arg AS args SET args.ARG_INDEX = args.ARG_INDEX - 1 WHERE args.DOMAIN = domain AND args.COMMAND = command AND args.ARG_INDEX > argIndex;
	COMMIT;
    END$$

DELIMITER ;