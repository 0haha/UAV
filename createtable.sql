-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: UAV
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `Operator`
--

DROP TABLE IF EXISTS `Operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `birthday` char(10) NOT NULL,
  `worktime` int(11) NOT NULL,
  `phone` char(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `ran` double(10,4) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Operator`
--

LOCK TABLES `Operator` WRITE;
/*!40000 ALTER TABLE `Operator` DISABLE KEYS */;
INSERT INTO `Operator` VALUES (1,1,'王杨林','1991-01-11',2,'18828234988','广东省-汕头市-潮南区-仙城镇-长春村',500000.0000,0),(2,1,'王佳','1995-11-13',1,'18828234998','广东省-佛山市-三水区-乐平镇-新旗村',900000.0000,0),(3,1,'林辉','1975-05-13',1,'18928234998','广东省-广州市-番禺区-小谷围街道-贝岗村',800000.0000,0),(4,0,'洪秀琳','1985-05-21',5,'18928904998','广东省-广州市-天河区-石牌街道-华师大社区',800000.0000,0),(5,1,'周波','1985-02-21',5,'18928904998','广东省-广州市-天河区-石牌街道-暨大社区',200000.0000,0),(9,0,'kk','1987-02-28',10,'18822234908','guangdongjieyang',4.8000,1);
/*!40000 ALTER TABLE `Operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operator_order`
--

DROP TABLE IF EXISTS `operator_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operator_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `order_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `operator_id` (`operator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator_order`
--

LOCK TABLES `operator_order` WRITE;
/*!40000 ALTER TABLE `operator_order` DISABLE KEYS */;
INSERT INTO `operator_order` VALUES (1,1,1,0),(2,2,1,0);
/*!40000 ALTER TABLE `operator_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_usr`
--

DROP TABLE IF EXISTS `order_usr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_usr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `usrid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orderid` (`orderid`),
  CONSTRAINT `order_usr_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_usr`
--

LOCK TABLES `order_usr` WRITE;
/*!40000 ALTER TABLE `order_usr` DISABLE KEYS */;
INSERT INTO `order_usr` VALUES (1,1,1);
/*!40000 ALTER TABLE `order_usr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `crops` varchar(20) DEFAULT NULL,
  `area` int(11) DEFAULT NULL,
  `price` double(5,3) DEFAULT NULL,
  `worktime` char(10) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'王小明','18826077397','广东省-汕头市-潮南区-仙城镇-长春村','小麦',500,3.300,'2017-12-31','2017-12-31','注重质量！',NULL),(2,'方式玉','14846577397','广东省-深圳市-龙岗区-坪山街道办事处-田心社区','玉米',500,5.300,'2017-03-21','2017-03-20','注意植保质量',0),(3,'李红','18826047397','广东省-广州市-天河区-石牌街道-新一街社区','玉米',150,3.300,'2017-08-21','2017-08-21','注重质量！',0),(4,'马同文','18722077397','广东省-揭阳市-揭西县-棉湖镇-后埔村','蔬菜',570,4.300,'2017-12-31','2017-12-31','注意质量！',NULL),(5,'章杰','18826377397','广东省-揭阳市-普宁市-赤岗镇-埔下村','花生',1500,10.300,'2017-12-31','1917-12-31','速率要快',0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quotation`
--

DROP TABLE IF EXISTS `quotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quotation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operatorid` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `price` double(5,3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1` (`operatorid`),
  KEY `FK2` (`orderId`),
  CONSTRAINT `FK1` FOREIGN KEY (`operatorid`) REFERENCES `Operator` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK2` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quotation`
--

LOCK TABLES `quotation` WRITE;
/*!40000 ALTER TABLE `quotation` DISABLE KEYS */;
INSERT INTO `quotation` VALUES (5,1,1,4.300),(6,2,1,3.300);
/*!40000 ALTER TABLE `quotation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-09  0:08:42
