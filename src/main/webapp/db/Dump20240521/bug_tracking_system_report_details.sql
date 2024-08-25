-- MySQL dump 10.13  Distrib 8.4.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bug_tracking_system
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `report_details`
--

DROP TABLE IF EXISTS `report_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `report_id` int DEFAULT NULL,
  `novalnet_id` int DEFAULT NULL,
  `shopsystem_id` int DEFAULT NULL,
  `cms_id` int DEFAULT NULL,
  `option1_id` int DEFAULT '0',
  `option2_id` int DEFAULT '0',
  `option3_id` int DEFAULT '0',
  `option4_id` int DEFAULT '0',
  `option5_id` int DEFAULT '0',
  `option6_id` int DEFAULT '0',
  `option7_id` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `shopsystem_id` (`shopsystem_id`),
  KEY `cms_id` (`cms_id`),
  KEY `report_id` (`report_id`),
  KEY `novalnet_id` (`novalnet_id`),
  KEY `option1_id` (`option1_id`),
  KEY `option2_id` (`option2_id`),
  KEY `option3_id` (`option3_id`),
  KEY `option4_id` (`option4_id`),
  KEY `option5_id` (`option5_id`),
  KEY `option6_id` (`option6_id`),
  KEY `option7_id` (`option7_id`),
  CONSTRAINT `report_details_ibfk_1` FOREIGN KEY (`shopsystem_id`) REFERENCES `shopsystem` (`shopsystem_id`),
  CONSTRAINT `report_details_ibfk_10` FOREIGN KEY (`option6_id`) REFERENCES `optional_software` (`id`),
  CONSTRAINT `report_details_ibfk_11` FOREIGN KEY (`option7_id`) REFERENCES `optional_software` (`id`),
  CONSTRAINT `report_details_ibfk_2` FOREIGN KEY (`cms_id`) REFERENCES `cms` (`cms_id`),
  CONSTRAINT `report_details_ibfk_3` FOREIGN KEY (`report_id`) REFERENCES `reports` (`report_id`),
  CONSTRAINT `report_details_ibfk_4` FOREIGN KEY (`novalnet_id`) REFERENCES `novalnet` (`id`),
  CONSTRAINT `report_details_ibfk_5` FOREIGN KEY (`option1_id`) REFERENCES `optional_software` (`id`),
  CONSTRAINT `report_details_ibfk_6` FOREIGN KEY (`option2_id`) REFERENCES `optional_software` (`id`),
  CONSTRAINT `report_details_ibfk_7` FOREIGN KEY (`option3_id`) REFERENCES `optional_software` (`id`),
  CONSTRAINT `report_details_ibfk_8` FOREIGN KEY (`option4_id`) REFERENCES `optional_software` (`id`),
  CONSTRAINT `report_details_ibfk_9` FOREIGN KEY (`option5_id`) REFERENCES `optional_software` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_details`
--

LOCK TABLES `report_details` WRITE;
/*!40000 ALTER TABLE `report_details` DISABLE KEYS */;
INSERT INTO `report_details` VALUES (7,79,24,18,NULL,28,29,NULL,NULL,NULL,NULL,NULL),(8,81,1,17,6,30,31,NULL,NULL,NULL,NULL,NULL),(9,83,23,17,6,5,32,NULL,NULL,NULL,NULL,NULL),(10,85,25,18,NULL,33,34,35,NULL,NULL,NULL,NULL),(11,87,26,17,6,36,37,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `report_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-22  0:04:29
