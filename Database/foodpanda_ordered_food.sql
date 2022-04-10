-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: foodpanda
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `ordered_food`
--

DROP TABLE IF EXISTS `ordered_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordered_food` (
  `order_id` int NOT NULL,
  `food_id` int NOT NULL,
  KEY `FKbmd6kulwkrenlll0s6tblji6j` (`food_id`),
  KEY `FK4o5ymem1aghd4echyvrr7e2m3` (`order_id`),
  CONSTRAINT `FK4o5ymem1aghd4echyvrr7e2m3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKbmd6kulwkrenlll0s6tblji6j` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_food`
--

LOCK TABLES `ordered_food` WRITE;
/*!40000 ALTER TABLE `ordered_food` DISABLE KEYS */;
INSERT INTO `ordered_food` VALUES (1,1),(1,2),(1,4),(1,3),(1,5),(1,6),(1,7),(1,8),(2,1),(2,1),(2,4),(2,5),(2,5),(2,8),(2,8),(2,8),(3,9),(3,10),(3,11),(3,12),(4,1),(4,2),(5,8),(5,6),(5,5),(5,4),(6,9),(6,10),(6,11),(6,12),(6,12),(6,12),(7,7),(7,7),(7,7),(7,7),(7,7),(7,7),(7,7),(8,1),(8,2),(8,3),(8,4),(9,9),(9,10),(9,10),(9,12),(9,12);
/*!40000 ALTER TABLE `ordered_food` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-10 17:32:34
