-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.4.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for book_store
CREATE DATABASE IF NOT EXISTS `book_store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `book_store`;

-- Dumping structure for table book_store.author
CREATE TABLE IF NOT EXISTS `author` (
  `author_id` char(50) NOT NULL,
  `full_name` varchar(256) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `bio` text,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table book_store.book
CREATE TABLE IF NOT EXISTS `book` (
  `book_id` char(50) NOT NULL,
  `book_title` varchar(512) DEFAULT NULL,
  `author_id` char(50) DEFAULT NULL,
  `publication_year` int DEFAULT NULL,
  `import_price` double DEFAULT NULL,
  `list_price` double DEFAULT NULL,
  `selling_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `category_id` char(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`book_id`),
  KEY `FK_book_category` (`category_id`),
  KEY `FK_book_author` (`author_id`),
  CONSTRAINT `FK_book_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `FK_book_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table book_store.category
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table book_store.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` char(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(512) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `billing_address` varchar(512) DEFAULT NULL,
  `shipping_address` varchar(512) DEFAULT NULL,
  `invoice_address` varchar(512) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `phone_number` varchar(12) DEFAULT NULL,
  `email` varchar(512) DEFAULT NULL,
  `sub_to_newsletter` tinyint DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table book_store.order
CREATE TABLE IF NOT EXISTS `order` (
  `order_id` char(50) NOT NULL,
  `customer_id` char(50) NOT NULL,
  `billing_address` varchar(512) NOT NULL,
  `shipping_address` varchar(512) NOT NULL,
  `payment_method` varchar(512) NOT NULL,
  `order_status` varchar(256) NOT NULL,
  `paid_amount` double NOT NULL,
  `remaining_amount` double NOT NULL,
  `order_date` date NOT NULL,
  `delivery_date` date NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_order_customer` (`customer_id`),
  CONSTRAINT `FK_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table book_store.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `order_detail_id` char(50) NOT NULL,
  `order_id` char(50) NOT NULL,
  `book_id` char(50) NOT NULL,
  `quantity` int NOT NULL,
  `list_price` double NOT NULL DEFAULT (0),
  `discount` double NOT NULL DEFAULT (0),
  `selling_price` double NOT NULL DEFAULT (0),
  `vat` double NOT NULL DEFAULT (0),
  PRIMARY KEY (`order_detail_id`),
  KEY `FK_order_detail_order` (`order_id`),
  KEY `FK_order_detail_book` (`book_id`),
  CONSTRAINT `FK_order_detail_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `FK_order_detail_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
