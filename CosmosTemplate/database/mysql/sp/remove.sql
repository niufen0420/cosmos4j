DELIMITER $$

DROP PROCEDURE IF EXISTS `cosmos`.`remove`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    PROCEDURE `cosmos`.`remove`(IN domain VARCHAR(50), IN name VARCHAR(100), IN composite VARCHAR(100), IN lftIndex INT(10))
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN
	SET @lftIndex = lftIndex, @rgtIndex = 0;
	SELECT composite.LFT_INDEX, composite.RGT_INDEX INTO @lftIndex, @rgtIndex FROM tb_cos_composite_command composite WHERE composite.COMPOSITE = composite AND composite.DOMAIN = domain AND composite.COMMAND = name AND composite.LFT_INDEX = lftIndex;
	START TRANSACTION;
	DELETE FROM tb_cos_composite_command WHERE tb_cos_composite_command.COMPOSITE = composite AND tb_cos_composite_command.LFT_INDEX >= @lftIndex AND tb_cos_composite_command.LFT_INDEX < @rgtIndex;
	UPDATE tb_cos_composite_command composite SET composite.RGT_INDEX = composite.RGT_INDEX - @rgtIndex + @lftIndex - 1 WHERE composite.COMPOSITE = composite AND composite.RGT_INDEX > @rgtIndex;
	UPDATE tb_cos_composite_command composite SET composite.LFT_INDEX = composite.LFT_INDEX - @rgtIndex + @lftIndex - 1 WHERE composite.COMPOSITE = composite AND composite.LFT_INDEX > @rgtIndex;
	COMMIT;
    END$$

DELIMITER ;