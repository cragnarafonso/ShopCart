-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`stm_product` FEITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_product` (
  `product_id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_ds` VARCHAR(250) NOT NULL,
  `product_st` CHAR(3) NOT NULL,
  `product_jd` JSON NULL,
  `product_image_cd` VARCHAR(250) NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_product_code` FEITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_product_code` (
  `product_id` BIGINT NOT NULL,
  `product_code_cd` VARCHAR(250) NOT NULL,
  `product_code_tp` CHAR(3) NOT NULL,
  `product_code_jd` JSON NULL,
  PRIMARY KEY (`product_id`, `product_code_cd`, `product_code_tp`),
  CONSTRAINT `fk_stm_product_code_stm_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`stm_product` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site` FEITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site` (
  `site_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Site serial identifier',
  `site_ds` VARCHAR(250) NOT NULL COMMENT 'Site designation',
  `site_tp` CHAR(3) NOT NULL COMMENT 'Type of site (see z_enumerated)',
  `site_st` CHAR(3) NOT NULL COMMENT 'Status of the site (see z_enumerated)',
  `site_jd` JSON NULL COMMENT 'Json with site extra information',
  PRIMARY KEY (`site_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site_product`  FEITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_product` (
  `site_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  PRIMARY KEY (`site_id`, `product_id`),
  INDEX `fk_product` (`product_id` ASC),
  CONSTRAINT `fk_stm_site_product_stm_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `mydb`.`stm_site` (`site_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_stm_site_product_stm_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`stm_product` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site_product_code` FEITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_product_code` (
  `site_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  `product_code_cd` VARCHAR(250) NOT NULL,
  `product_code_tp` CHAR(3) NOT NULL,
  `product_code_jd` JSON NULL,
  PRIMARY KEY (`site_id`, `product_id`, `product_code_cd`, `product_code_tp`),
  CONSTRAINT `fk_stm_site_product_code_stm_site_product1`
    FOREIGN KEY (`site_id` , `product_id`)
    REFERENCES `mydb`.`stm_site_product` (`site_id` , `product_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site_product_price` FEITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_product_price` (
  `site_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  `currency_cd` CHAR(3) NOT NULL,
  `start_dt` DATETIME NOT NULL,
  `price_vl` DECIMAL(19,2) NOT NULL,
  `price_st` CHAR(3) NOT NULL,
  PRIMARY KEY (`site_id`, `product_id`, `currency_cd`, `start_dt`),
  CONSTRAINT `fk_stm_site_product_price_stm_site_product1`
    FOREIGN KEY (`site_id` , `product_id`)
    REFERENCES `mydb`.`stm_site_product` (`site_id` , `product_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site_client` FEITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_client` (
  `client_id` BIGINT NOT NULL AUTO_INCREMENT,
  `site_id` BIGINT NOT NULL,
  `client_ds` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`client_id`, `site_id`),
  CONSTRAINT `fk_stm_site_client_stm_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `mydb`.`stm_site` (`site_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site_session` FEITO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_session` (
  `site_id` BIGINT NOT NULL,
  `session_tk` VARCHAR(250) NOT NULL,
  `client_id` BIGINT NOT NULL,
  `created_dt` DATETIME NOT NULL,
  `updated_dt` DATETIME NOT NULL,
  PRIMARY KEY (`site_id`, `session_tk`),
  INDEX `fk_site_client` (`client_id` ASC, `site_id` ASC),
  CONSTRAINT `fk_stm_site_session_stm_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `mydb`.`stm_site` (`site_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_stm_site_session_stm_site_client1`
    FOREIGN KEY (`client_id` , `site_id`)
    REFERENCES `mydb`.`stm_site_client` (`client_id` , `site_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site_cart` NO WEBSITE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_cart` (
  `site_id` BIGINT NOT NULL,
  `cart_tk` VARCHAR(250) NOT NULL,
  `session_tk` VARCHAR(250) NULL,
  `client_id` BIGINT NULL,
  PRIMARY KEY (`site_id`, `cart_tk`),
  UNIQUE INDEX `fk_site_session` (`session_tk` ASC, `site_id` ASC),
  UNIQUE INDEX `fk_site_client` (`client_id` ASC, `site_id` ASC),
  CONSTRAINT `fk_stm_site_cart_stm_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `mydb`.`stm_site` (`site_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stm_site_cart_stm_site_session1`
    FOREIGN KEY (`site_id`, `session_tk`)
    REFERENCES `mydb`.`stm_site_session` (`site_id`, `session_tk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stm_site_cart_stm_site_client1`
    FOREIGN KEY (`client_id` , `site_id`)
    REFERENCES `mydb`.`stm_site_client` (`client_id` , `site_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site_cart_product` NO WEBSITE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_cart_product` (
  `site_id` BIGINT NOT NULL,
  `cart_tk` VARCHAR(250) NOT NULL,
  `product_id` BIGINT NOT NULL,
  `order_qt` INT NOT NULL,
  `units_tp` CHAR(3) NOT NULL,
  PRIMARY KEY (`site_id`, `cart_tk`, `product_id`),
  INDEX `fk_site_product` (`product_id` ASC, `site_id` ASC),
  CONSTRAINT `fk_stm_site_cart_product_stm_site_cart1`
    FOREIGN KEY (`site_id` , `cart_tk`)
    REFERENCES `mydb`.`stm_site_cart` (`site_id` , `cart_tk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stm_site_cart_product_stm_site_product1`
    FOREIGN KEY (`product_id` , `site_id`)
    REFERENCES `mydb`.`stm_site_product` (`product_id` , `site_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stm_site_client_order` NO WEBSITE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_client_order` (
  `order_id` BIGINT NOT NULL AUTO_INCREMENT,
  `site_id` BIGINT NOT NULL,
  `client_id` BIGINT NOT NULL,
  `order_jd` JSON NOT NULL,
  PRIMARY KEY (`order_id`, `site_id`, `client_id`),
  CONSTRAINT `fk_stm_site_client_order_stm_site_client1`
    FOREIGN KEY (`site_id` , `client_id`)
    REFERENCES `mydb`.`stm_site_client` (`site_id` , `client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;

-- -----------------------------------------------------
-- Placeholder table for view `mydb`.`stm_site_product_code_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`stm_site_product_code_view` (`NULL` INT, `product_id` INT, `product_code_cd` INT, `product_code_tp` INT, `product_code_jd` INT);

-- -----------------------------------------------------
-- View `mydb`.`stm_site_product_code_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`stm_site_product_code_view`;
USE `mydb`;
CREATE  OR REPLACE VIEW `stm_site_product_code_view` AS
SELECT NULL, product_id, product_code_cd, product_code_tp, product_code_jd
FROM stm_product_code
UNION
SELECT site_id, product_id, product_code_cd, product_code_tp, product_code_jd
FROM stm_site_product_code;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
