DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`saveArg`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`saveArg`(IN domain VARCHAR(100), IN command VARCHAR(100), IN argIndex INT, IN name VARCHAR(50), IN inOutType VARCHAR(20), IN converter VARCHAR(50), IN remark VARCHAR(300))
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	SET @timeNow = NOW();
	UPDATE tb_cos_command_arg AS args SET args.UPDATE_TIME = @timeNow, 
		args.NAME = name, args.CONVERTER = converter, args.REMARK = remark, args.IN_OUT_TYPE = inOutType
		WHERE args.DOMAIN = domain AND args.COMMAND = command AND args.ARG_INDEX = argIndex + 1;
    END$$

DELIMITER ;