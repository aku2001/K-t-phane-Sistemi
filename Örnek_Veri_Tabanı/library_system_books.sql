-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: library_system
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `idbooks` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `pubDate` varchar(45) NOT NULL,
  `pages` int NOT NULL,
  `isBooked` varchar(45) NOT NULL,
  `isReserved` varchar(45) NOT NULL,
  `reservedDate` varchar(45) DEFAULT NULL,
  `bookedDate` varchar(45) DEFAULT NULL,
  `barcodeNumber` int NOT NULL,
  `health` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idbooks`),
  UNIQUE KEY `idbooks_UNIQUE` (`idbooks`),
  UNIQUE KEY `barcodeNumber_UNIQUE` (`barcodeNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (11,'harry potter','jk','2010',500,'F','F','F','F',1,'85'),(13,'lord','ak','2012',300,'F','F',NULL,NULL,2,'100'),(14,'karayip','mer','2013',305,'F','F','F',NULL,3,'100'),(17,'kitap1','qty','2013',893,'F','F',NULL,NULL,4,'100'),(18,'kitap2','qtyf','2013',700,'F','F',NULL,NULL,5,'100'),(19,'kitap5','thr','2013',345,'F','F',NULL,NULL,6,'100'),(20,'kitap19','mahmut','2015',600,'F','F',NULL,NULL,7,'100'),(21,'kitap10','qty','2015',85,'F','F','F',NULL,8,'95'),(22,'kitap4','mahmut','2059',95,'F','F',NULL,NULL,9,'80'),(23,'kitap89','qty','2045',145,'F','F',NULL,NULL,10,'95');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-23 17:35:54
