DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`insertArgAfter`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`insertArgAfter`(IN domain VARCHAR(100), IN command VARCHAR(100), IN argIndex INT, IN name VARCHAR(50), IN inOutType VARCHAR(20), IN converter VARCHAR(50), IN remark VARCHAR(300))
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
	IF argIndex > @maxIndex THEN
		SET argIndex = @maxIndex;
	END IF;
	UPDATE tb_cos_command_arg AS args SET args.ARG_INDEX = args.ARG_INDEX + 1 WHERE args.DOMAIN = domain AND args.COMMAND = command AND args.ARG_INDEX > argIndex + 1;
	INSERT INTO tb_cos_command_arg VALUES (UUID32(), NULL, @timeNow, NULL, @timeNow, domain, command, argIndex + 2, name, converter, remark, inOutType);
	COMMIT;
    END$$

DELIMITER ;