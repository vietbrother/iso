-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: iso_local
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `c_document`
--

DROP TABLE IF EXISTS `c_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_code` varchar(45) NOT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `document_type` varchar(45) DEFAULT NULL,
  `security_level` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `part_storage_time` int(10) DEFAULT NULL,
  `department_storage_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_document`
--

LOCK TABLES `c_document` WRITE;
/*!40000 ALTER TABLE `c_document` DISABLE KEYS */;
INSERT INTO `c_document` VALUES (3,'test','123test','cứng','trung bình','test','1',32,12),(4,'test234','test234','test234','test234',NULL,'test234',33,22);
/*!40000 ALTER TABLE `c_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_file`
--

DROP TABLE IF EXISTS `c_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_code` varchar(45) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(45) DEFAULT NULL,
  `security_level` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `part_storage_time` int(10) DEFAULT NULL,
  `department_storage_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_file`
--

LOCK TABLES `c_file` WRITE;
/*!40000 ALTER TABLE `c_file` DISABLE KEYS */;
INSERT INTO `c_file` VALUES (2,'sử dụng','test123','test321','mềm',NULL,'test321',12,11);
/*!40000 ALTER TABLE `c_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_security_level`
--

DROP TABLE IF EXISTS `c_security_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_security_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `security_level_name` varchar(255) DEFAULT NULL,
  `security_level_color` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_security_level`
--

LOCK TABLES `c_security_level` WRITE;
/*!40000 ALTER TABLE `c_security_level` DISABLE KEYS */;
INSERT INTO `c_security_level` VALUES (1,'test','#0000','test324',0,'test',NULL,'test',NULL);
/*!40000 ALTER TABLE `c_security_level` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-12 13:47:09
