DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`createCommand`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`createCommand`(IN domain VARCHAR(50), IN name VARCHAR(100), IN command VARCHAR(4000), IN executer VARCHAR(100), IN remark VARCHAR(300), IN commandType VARCHAR(100), IN debugLevel VARCHAR(100))
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	SET @commandId = UUID32();
	SET @compositeId = UUID32();
	SET @createTime = NOW();
	START TRANSACTION;
	INSERT INTO tb_cos_command values (@commandId, NULL, @createTime, NULL, @createTime, domain, name, command, executer, remark, commandType, debugLevel);
	INSERT INTO tb_cos_composite_command values (@compositeId, NULL, @createTime, NULL, @createTime, domain, name, CONCAT(domain, '.', name), 1, 2);
	COMMIT;
    END$$

DELIMITER ;