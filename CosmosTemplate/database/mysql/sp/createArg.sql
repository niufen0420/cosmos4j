DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`createArg`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`createArg`(IN domain VARCHAR(100), IN command VARCHAR(100), IN name VARCHAR(50), IN inOutType VARCHAR(20), IN converter VARCHAR(50), IN remark VARCHAR(300))
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	SET @timeNow = NOW();
	START TRANSACTION;
	SELECT MAX(args.ARG_INDEX) INTO @maxIndex FROM tb_cos_command_arg AS args WHERE args.DOMAIN = domain AND args.COMMAND = command;
	IF @maxIndex IS NULL THEN
		SET @maxIndex = 0;
	END IF;
	INSERT INTO tb_cos_command_arg VALUES (UUID32(), NULL, @timeNow, NULL, @timeNow, domain, command, @maxIndex + 1, name, converter, remark, inOutType);
	COMMIT;
    END$$

DELIMITER ;