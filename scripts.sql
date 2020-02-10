CREATE DATABASE 'BOOK_STORE';
USE BOOK_STORE;

CREATE TABLE `books` (
  `id` BIGINT(15) NOT NULL AUTO_INCREMENT,
  `isbn` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `copies` INT(10) DEFAULT NULL,
  `author_id` BIGINT(15) NOT NULL,
  `created_on` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updated_on` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  `version` INT(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_BOOKS` (`isbn`),
  FOREIGN KEY `FK_AUTHOR` (`author_id`) REFERENCES `authors` (`author_id`)
) ENGINE=INNODB;

CREATE TABLE `authors` (
  `author_id` BIGINT(15) AUTO_INCREMENT,
  `author_name` VARCHAR(255) NOT NULL,
  `created_on` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updated_on` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`author_id`),
  UNIQUE KEY `UK_AUTHORS` (`author_name`)
);

SELECT * FROM AUTHORS;

SELECT * FROM BOOKS;
COMMIT;

DROP TABLE AUTHORS;
DROP TABLE BOOKS;
TRUNCATE TABLE AUTHORS;