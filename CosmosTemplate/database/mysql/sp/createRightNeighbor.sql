DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`createRightNeighbor`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`createRightNeighbor`(IN leftDomain VARCHAR(50), IN leftCommand VARCHAR(100), IN composite VARCHAR(100), IN domain VARCHAR(50), IN name VARCHAR(100), IN command VARCHAR(4000), IN executer VARCHAR(100), IN remark VARCHAR(300), IN commandType VARCHAR(100), IN debugLevel VARCHAR(100))
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	SET @createTime = NOW();
	SET @rgtIndex = -1;
	SELECT composite.RGT_INDEX INTO @rgtIndex FROM tb_cos_composite_command composite WHERE composite.DOMAIN = leftDomain AND composite.COMMAND = leftCommand AND composite.COMPOSITE = composite;
	IF @rgtIndex != -1 THEN
		START TRANSACTION;
		UPDATE tb_cos_composite_command composite SET composite.RGT_INDEX = composite.RGT_INDEX + 2 WHERE composite.COMPOSITE = composite AND composite.RGT_INDEX > @rgtIndex;
		UPDATE tb_cos_composite_command composite SET composite.LFT_INDEX = composite.LFT_INDEX + 2 WHERE composite.COMPOSITE = composite AND composite.LFT_INDEX > @rgtIndex;
		INSERT INTO tb_cos_command values (UUID32(), NULL, @createTime, NULL, @createTime, domain, name, command, executer, remark, commandType, debugLevel);
		INSERT INTO tb_cos_composite_command values (UUID32(), NULL, @createTime, NULL, @createTime, domain, name, composite, @rgtIndex + 1, @rgtIndex + 2);
		INSERT INTO tb_cos_composite_command values (UUID32(), NULL, @createTime, NULL, @createTime, domain, name, CONCAT(domain, '.', name), 1, 2);
		COMMIT;
	END IF;
    END$$

DELIMITER ;