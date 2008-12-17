DELIMITER $$

DROP TRIGGER IF EXISTS `cosmos`.`removeSingleCommand`$$

CREATE
    /*[DEFINER = { user | CURRENT_USER }]*/
    TRIGGER `cosmos`.`removeSingleCommand` BEFORE DELETE
    ON `cosmos`.`tb_cos_composite_command`
    FOR EACH ROW BEGIN
	SET @compositeCount = 0;
	SELECT COUNT(composite.ID) INTO @compositeCount FROM tb_cos_composite_command composite WHERE composite.DOMAIN = OLD.DOMAIN AND composite.COMMAND = OLD.COMMAND;
	IF @compositeCount < 2 THEN
		DELETE FROM tb_cos_command WHERE tb_cos_command.DOMAIN = OLD.DOMAIN AND tb_cos_command.NAME = OLD.COMMAND;
	END IF;
    END$$

DELIMITER ;