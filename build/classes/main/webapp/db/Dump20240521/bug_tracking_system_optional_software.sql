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
-- Table structure for table `optional_software`
--

DROP TABLE IF EXISTS `optional_software`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optional_software` (
  `id` int NOT NULL AUTO_INCREMENT,
  `software` varchar(255) DEFAULT NULL,
  `s_version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optional_software`
--

LOCK TABLES `optional_software` WRITE;
/*!40000 ALTER TABLE `optional_software` DISABLE KEYS */;
INSERT INTO `optional_software` VALUES (1,'two','4.44'),(2,'ss','33'),(3,'rr','2.2.2'),(4,'opt1','33'),(5,'opt2','5.5.5'),(6,'opt1','23'),(7,'opt2','2.2.2'),(8,'three','3'),(9,'php','23'),(10,'dd','7'),(11,'dd','22'),(12,'rr','33'),(13,'opt1','2'),(14,'opt2','22'),(15,'php','4.4'),(16,'cccc','2.2.2'),(17,'g','3444'),(18,'dd','2.2.28'),(19,'sad','3'),(20,'jjj','22'),(21,'cccc','7'),(22,'php','22'),(23,'rr','5.5.5'),(24,'ssssssss','1'),(25,'cccc2s','3'),(26,'oneb','23.7'),(27,'ccccb','7.77'),(28,'dddd','4.4'),(29,'fff','3.3.3.3'),(30,'php','3'),(31,'44','5.5.5'),(32,'opt23','4.44'),(33,'option1','23'),(34,'option2','33'),(35,'option3','555'),(36,'php','6'),(37,'subscriptionn','3');
/*!40000 ALTER TABLE `optional_software` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-22  0:04:31
