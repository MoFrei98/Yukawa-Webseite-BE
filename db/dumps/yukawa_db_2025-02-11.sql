-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: yukawa_db
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `uuid` varchar(255) NOT NULL,
  `order_uuid` varchar(255) DEFAULT NULL,
  `product_uuid` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `order_uuid_idx` (`order_uuid`),
  KEY `product_uuid_idx` (`product_uuid`),
  CONSTRAINT `order_uuid` FOREIGN KEY (`order_uuid`) REFERENCES `orders` (`uuid`),
  CONSTRAINT `product_uuid` FOREIGN KEY (`product_uuid`) REFERENCES `products` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `uuid` varchar(255) NOT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  `nr` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `user_uuid_idx` (`user_uuid`),
  CONSTRAINT `user_uuid` FOREIGN KEY (`user_uuid`) REFERENCES `users` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pinboard`
--

DROP TABLE IF EXISTS `pinboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pinboard` (
  `uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pinboard`
--

LOCK TABLES `pinboard` WRITE;
/*!40000 ALTER TABLE `pinboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `pinboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pinboard_item`
--

DROP TABLE IF EXISTS `pinboard_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pinboard_item` (
  `uuid` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `edited_at` datetime DEFAULT NULL,
  `edited_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pinboard_item`
--

LOCK TABLES `pinboard_item` WRITE;
/*!40000 ALTER TABLE `pinboard_item` DISABLE KEYS */;
INSERT INTO `pinboard_item` VALUES ('51003639-e706-11ef-acce-2cf05d052b10','test','blub blub','2025-02-09 17:52:58','morris',NULL,NULL);
/*!40000 ALTER TABLE `pinboard_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pinboard_item_attatchments`
--

DROP TABLE IF EXISTS `pinboard_item_attatchments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pinboard_item_attatchments` (
  `uuid` varchar(255) NOT NULL,
  `pinboard_item_uuid` varchar(255) DEFAULT NULL,
  `path_type` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `pinboard_item_uuid` (`pinboard_item_uuid`),
  CONSTRAINT `pinboard_item_uuid` FOREIGN KEY (`pinboard_item_uuid`) REFERENCES `pinboard_item` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pinboard_item_attatchments`
--

LOCK TABLES `pinboard_item_attatchments` WRITE;
/*!40000 ALTER TABLE `pinboard_item_attatchments` DISABLE KEYS */;
INSERT INTO `pinboard_item_attatchments` VALUES ('e3a22307-e716-11ef-acce-2cf05d052b10','51003639-e706-11ef-acce-2cf05d052b10','URL','IMAGE','https://testimage.png');
/*!40000 ALTER TABLE `pinboard_item_attatchments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `uuid` varchar(255) NOT NULL,
  `category` tinyint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_1` varbinary(255) DEFAULT NULL,
  `image_2` varbinary(255) DEFAULT NULL,
  `image_3` varbinary(255) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `price` double NOT NULL,
  `discount` double DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `can_post` bit(1) DEFAULT b'0',
  `can_edit` bit(1) DEFAULT b'0',
  `can_delete` bit(1) DEFAULT b'0',
  `can_view` bit(1) DEFAULT b'0',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES ('8eb00976-e878-11ef-9be3-2cf05d052b10','BLACKLIST',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),('d2fcb2c5-e877-11ef-9be3-2cf05d052b10','ADMIN',_binary '',_binary '',_binary '',_binary ''),('d2fcc4fe-e877-11ef-9be3-2cf05d052b10','GUEST',_binary '\0',_binary '\0',_binary '\0',_binary '');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `uuid` varchar(255) NOT NULL,
  `user_role_uuid` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `role_uuid_idx` (`user_role_uuid`),
  CONSTRAINT `user_role_uuid` FOREIGN KEY (`user_role_uuid`) REFERENCES `user_roles` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('05cf3b86-b2b0-4405-80d9-70e59114a3f5','d2fcb2c5-e877-11ef-9be3-2cf05d052b10','Morris','Freihoff','mfreihoff','morris.freihoff@web.de','password123',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-11 14:39:45
