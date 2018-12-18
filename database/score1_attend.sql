-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: score1
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attend`
--

DROP TABLE IF EXISTS `attend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attend` (
  `stuID` varchar(20) NOT NULL,
  `1week` varchar(10) DEFAULT NULL,
  `2week` varchar(10) DEFAULT NULL,
  `3week` varchar(10) DEFAULT NULL,
  `4week` varchar(10) DEFAULT NULL,
  `5week` varchar(10) DEFAULT NULL,
  `6week` varchar(10) DEFAULT NULL,
  `7week` varchar(10) DEFAULT NULL,
  `8week` varchar(10) DEFAULT NULL,
  `9week` varchar(10) DEFAULT NULL,
  `10week` varchar(10) DEFAULT NULL,
  `11week` varchar(10) DEFAULT NULL,
  `12week` varchar(10) DEFAULT NULL,
  `13week` varchar(10) DEFAULT NULL,
  `14week` varchar(10) DEFAULT NULL,
  `15week` varchar(10) DEFAULT NULL,
  `16week` varchar(10) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`stuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attend`
--

LOCK TABLES `attend` WRITE;
/*!40000 ALTER TABLE `attend` DISABLE KEYS */;
INSERT INTO `attend` VALUES ('stu-01','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','김철수'),('stu-02','출석','지각','출석','출석','출석','출석','결석','출석','출석','지각','지각','출석','출석','출석','출석','출석','김미영'),('stu-03','결석','출석','출석','출석','출석','출석','결석','결석','출석','출석','출석','출석','결석','출석','출석','출석','송현석'),('stu-04','출석','출석','출석','출석','지각','출석','출석','출석','출석','출석','출석','출석','결석','출석','출석','출석','권희철'),('stu-05','지각','출석','출석','출석','출석','출석','결석','지각','출석','출석','출석','출석','출석','출석','출석','출석','김형준'),('stu-06','출석','출석','출석','출석','출석','출석','출석','결석','출석','출석','출석','출석','출석','출석','출석','출석','양희은'),('stu-07','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','신동수'),('stu-08','출석','출석','출석','출석','출석','출석','출석','지각','출석','출석','출석','출석','출석','출석','출석','출석','김태섭'),('stu-09','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','강백호'),('stu-10','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','조로'),('stu-11','출석','출석','출석','출석','출석','출석','출석','출석','결석','출석','출석','출석','출석','출석','출석','출석','나미'),('stu-12','출석','출석','출석','출석','출석','지각','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','김태희'),('stu-13','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','송혜교'),('stu-14','결석','출석','출석','결석','출석','출석','출석','출석','출석','출석','출석','지각','출석','지각','출석','출석','강동원'),('stu-15','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','원빈'),('stu-16','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','나루토'),('stu-17','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','사스케'),('stu-18','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','사쿠라'),('stu-19','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','네지'),('stu-20','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','김지영'),('stu-21','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','이영수'),('stu-22','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','최영수'),('stu-23','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','안철수'),('stu-24','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','김동근'),('stu-25','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','비와이'),('stu-26','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','씨잼'),('stu-27','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','이혜영'),('stu-28','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','안동휘'),('stu-29','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','김기태'),('stu-30','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','출석','김정남');
/*!40000 ALTER TABLE `attend` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-15 18:00:26

use score1;

select * from attend;

delete from attend where stuid = 'stu-31';
