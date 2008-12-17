DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`selectLeftNeighbor`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`selectLeftNeighbor`(IN rightDomain VARCHAR(50), IN rightCommand VARCHAR(100), IN composite VARCHAR(100), IN domain VARCHAR(50), IN name VARCHAR(100))
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	SET @createTime = NOW();
	SET @lftIndex = -1;
	SELECT composite.LFT_INDEX INTO @lftIndex FROM tb_cos_composite_command composite WHERE composite.DOMAIN = rightDomain AND composite.COMMAND = rightCommand AND composite.COMPOSITE = composite;
	IF @lftIndex != -1 THEN
		START TRANSACTION;
		UPDATE tb_cos_composite_command composite SET composite.LFT_INDEX = composite.LFT_INDEX + 2 WHERE composite.LFT_INDEX >= @lftIndex AND composite.COMPOSITE = composite;
		UPDATE tb_cos_composite_command composite SET composite.RGT_INDEX = composite.RGT_INDEX + 2 WHERE composite.RGT_INDEX >= @lftIndex AND composite.COMPOSITE = composite;
		INSERT INTO tb_cos_composite_command values (UUID32(), NULL, @createTime, NULL, @createTime, domain, name, composite, @lftIndex, @lftIndex + 1);
		COMMIT;
	END IF;
    END$$

DELIMITER ;