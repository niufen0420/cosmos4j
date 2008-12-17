DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`save`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`save`(IN domain VARCHAR(50), IN name VARCHAR(100), IN command VARCHAR(4000), IN executer VARCHAR(100), IN remark VARCHAR(300), IN commandType VARCHAR(100), IN debugLevel VARCHAR(100))
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	SET @updateTime = NOW();
	UPDATE tb_cos_command command SET command.UPDATE_TIME = @updateTime, command.COMMAND = command, command.EXECUTER = executer, command.REMARK = remark, command.TYPE = commandType, command.DEBUG_LEVEL = debugLevel WHERE command.DOMAIN = domain AND command.NAME = name;
    END$$

DELIMITER ;