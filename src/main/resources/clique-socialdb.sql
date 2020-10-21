-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema clique-social
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clique-social
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clique-social` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `clique-social` ;

-- -----------------------------------------------------
-- Table `clique-social`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clique-social`.`profile` (
  `id` INT(11) NOT NULL,
  `date_of_birth` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `join_date` DATETIME NOT NULL,
  `no_of_disapproved_posts` VARCHAR(45) NOT NULL,
  `profile_photo` VARCHAR(45) NOT NULL,
  `address_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clique-social`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clique-social`.`user` (
  `id` INT(11) NOT NULL,
  `enabled` TINYINT(4) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `profile_id` INT(11) NOT NULL,
  `profile_id1` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `profile_id1`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `fk_user_profile_idx` (`profile_id1` ASC) VISIBLE,
  CONSTRAINT `fk_user_profile`
    FOREIGN KEY (`profile_id1`)
    REFERENCES `clique-social`.`profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clique-social`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clique-social`.`address` (
  `id` INT(11) NOT NULL,
  `city` VARCHAR(25) NOT NULL,
  `state` VARCHAR(25) NOT NULL,
  `street` VARCHAR(90) NOT NULL,
  `house_number` VARCHAR(10) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `user_profile_id1` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `user_profile_id1`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_address_user1_idx` (`user_id` ASC, `user_profile_id1` ASC) VISIBLE,
  CONSTRAINT `fk_address_user1`
    FOREIGN KEY (`user_id` , `user_profile_id1`)
    REFERENCES `clique-social`.`user` (`id` , `profile_id1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clique-social`.`advertisement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clique-social`.`advertisement` (
  `id` INT(11) NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `enabled` TINYINT(4) NOT NULL,
  `expiry_date` DATETIME NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  `advertisement_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clique-social`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clique-social`.`comments` (
  `id` INT(11) NOT NULL,
  `usernamee_of_commentor` VARCHAR(20) NOT NULL,
  `comment_body` VARCHAR(100) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `user_profile_id1` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `user_profile_id1`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_comments_user1_idx` (`user_id` ASC, `user_profile_id1` ASC) VISIBLE,
  CONSTRAINT `fk_comments_user1`
    FOREIGN KEY (`user_id` , `user_profile_id1`)
    REFERENCES `clique-social`.`user` (`id` , `profile_id1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clique-social`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clique-social`.`post` (
  `id` INT(11) NOT NULL,
  `usernamee_of_poster` VARCHAR(20) NOT NULL,
  `comment_body` VARCHAR(100) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `user_profile_id1` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `user_profile_id1`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_post_user1_idx` (`user_id` ASC, `user_profile_id1` ASC) VISIBLE,
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id` , `user_profile_id1`)
    REFERENCES `clique-social`.`user` (`id` , `profile_id1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
