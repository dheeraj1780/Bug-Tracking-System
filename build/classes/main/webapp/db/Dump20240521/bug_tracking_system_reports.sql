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
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `report_name` varchar(100) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `report_status` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (71,'ss_3-N_4-jjj_22-cccc_7',1),(72,'ss_1-w3_4-N_3-php_22-rr_5.5.5',1),(73,'ss_1-w3_4-N_3-php_22-rr_5.5.5',1),(74,'w3_7.7.7-N_611-ssssssss_1-cccc2s_3',1),(75,'w3_7.7.7-N_611-ssssssss_1-cccc2s_3',1),(76,'ss_9.7-N_37.7-oneb_23.7-ccccb_7.77',1),(77,'ss_9.7-N_37.7-oneb_23.7-ccccb_7.77',1),(78,'ss_9-N_5-dddd_4.4-fff_3.3.3.3',1),(79,'ss_9-N_5-dddd_4.4-fff_3.3.3.3',1),(80,'w3_7.7.7-N_4-php_3-44_5.5.5',1),(81,'w3_7.7.7-N_4-php_3-44_5.5.5',1),(82,'w3_4-N_5-opt2_5.5.5-opt23_4.44',1),(83,'w3_4-N_5-opt2_5.5.5-opt23_4.44',1),(84,'ss_9-N_9-option1_23-option2_33-option3_555',1),(85,'ss_9-N_9-option1_23-option2_33-option3_555',1),(86,'w3_1.1-N_8-php_6-subscriptionn_3',1),(87,'w3_1.1-N_8-php_6-subscriptionn_3',1);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
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
