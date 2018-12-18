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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `stuid` varchar(20) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `middle` int(11) DEFAULT NULL,
  `final` int(11) DEFAULT NULL,
  `assignment` int(11) DEFAULT NULL,
  `atd` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `grade` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('stu-01','김철수',55,35,85,77,60,'D'),('stu-02','김미영',90,50,70,60,61.5,'D'),('stu-03','송현석',80,80,80,80,80,'C0'),('stu-04','권희철',12,34,78,56,48.3,'D'),('stu-05','김형준',44,33,99,22,47.85,'F'),('stu-06','양희은',80,45,80,80,66,'C0'),('stu-07','신동수',60,70,90,50,69,'C0'),('stu-08','김태섭',80,80,80,80,80,'C+'),('stu-09','강백호',80,80,80,80,80,'C+'),('stu-10','조로',80,80,80,80,80,'C+'),('stu-11','나미',80,80,80,80,80,'B0'),('stu-12','김태희',80,80,80,80,80,'B0'),('stu-13','송혜교',80,80,80,80,80,'B0'),('stu-14','강동원',80,80,80,80,80,'B0'),('stu-15','원빈',80,80,80,80,80,'B0'),('stu-16','나루토',80,80,80,80,80,'B+'),('stu-17','사스케',80,80,80,80,80,'B+'),('stu-18','사쿠라',90,85,83,97,88,'A+'),('stu-19','네지',80,80,80,80,80,'B+'),('stu-20','김지영',80,80,80,80,80,'B+'),('stu-21','이영수',80,80,80,80,80,'B+'),('stu-22','최영수',80,80,80,80,80,'A0'),('stu-23','안철수',80,80,80,80,80,'A0'),('stu-24','김동근',80,80,80,80,80,'A0'),('stu-25','비와이',80,80,80,80,80,'A0'),('stu-26','씨잼',80,80,80,80,80,'A0'),('stu-27','이혜영',80,80,80,80,80,'A0'),('stu-28','안동휘',80,80,80,80,80,'A+'),('stu-29','김기태',80,80,80,80,80,'A+'),('stu-30','김정남',60,55,3,33,37,'F');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-15 18:00:28

select * from student;

use score1;