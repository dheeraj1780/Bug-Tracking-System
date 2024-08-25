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
-- Table structure for table `bug_details`
--

DROP TABLE IF EXISTS `bug_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bug_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bug_id` int DEFAULT NULL,
  `bug_name` varchar(100) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `feedback` varchar(250) DEFAULT NULL,
  `severity_id` int DEFAULT NULL,
  `developer_id` int DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `f_url` varchar(100) DEFAULT NULL,
  `b_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bug_id` (`bug_id`),
  KEY `severity_id` (`severity_id`),
  KEY `status_id` (`status_id`),
  KEY `developer_id` (`developer_id`),
  CONSTRAINT `bug_details_ibfk_1` FOREIGN KEY (`bug_id`) REFERENCES `bug` (`id`),
  CONSTRAINT `bug_details_ibfk_2` FOREIGN KEY (`severity_id`) REFERENCES `severity` (`id`),
  CONSTRAINT `bug_details_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `bug_status` (`id`),
  CONSTRAINT `bug_details_ibfk_4` FOREIGN KEY (`developer_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bug_details`
--

LOCK TABLES `bug_details` WRITE;
/*!40000 ALTER TABLE `bug_details` DISABLE KEYS */;
INSERT INTO `bug_details` VALUES (5,1,'bug no 108','this is bug 108','108 alert',2,4,1,'frontfront/url','back/url'),(6,2,'bug three','three bug bug','three one two four five six seven eight nine ten',3,3,1,'front url///22222','/backurlwwwwwwww'),(7,3,'bugavalanche','this is the most dangerou bug','ayo',1,3,1,'this/front/url','this/url/back'),(8,4,'bugavalanche2','ddddd','ttttttttttttt',1,3,1,'this/front/url','this/url/back'),(9,5,'bug  10909','fffffff','ssssssss',1,3,1,'front url///22222','/backurlwwwwwwww'),(10,6,'bug  10909','fffffff','ssssssss',1,3,1,'front url///22222','/backurlwwwwwwww'),(11,7,'bug  10909','bugbugbug','gubgubgubgub',1,4,1,'front url///','/backurlwwwwwwww'),(12,8,'bug 8','dsfsdf','213',2,3,1,'front url///22222','/backurlwwwwwwww'),(13,9,'bug  109092','ddddddddddddddd','dddddddddddddddddddddddddd',2,3,1,'f4ont','/backurlwwwwwwww'),(14,10,'bug 202','fff','232323',2,4,1,'front url///','/backurlwwwwwwww'),(15,11,'bug  10909','ffffffff','ccccccccc',1,3,1,'frontfront/url','this/url/back'),(16,12,'bug  10909d','ddddddd','wwwww',2,3,1,'front url///','this/url/back'),(17,13,'bug  109093','ssss','dd',1,4,1,'frontfront/url','/backurlwwwwwwww'),(18,14,'bug 101','onehundredone','hunderdone',2,4,1,'front url///22222','this/url/back'),(19,15,'bug  708','ssssss','cccccccc',1,3,1,'d','d'),(20,16,'bug 908','s','s',2,3,1,'f4ont','back/url'),(21,17,'bug 808','dfa','vcx',3,4,1,'frontfront/url','/backurl'),(22,18,'bug 5656','this is bug 5656','bug 5656',2,4,1,'front url///22222','/backurlwwwwwwww');
/*!40000 ALTER TABLE `bug_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-22  0:04:30
