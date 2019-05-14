-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: projectdb
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) DEFAULT NULL,
  `picture` varchar(300) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (3,'bag',NULL,'clothing'),(4,'watch',NULL,'clothing'),(5,'iphone',NULL,'electronics'),(6,'sunglasses',NULL,'clothing'),(7,'Samsung',NULL,'electronics'),(8,'huawei',NULL,'electronics');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `request_id` int(11) DEFAULT NULL,
  `item_id` varchar(50) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `rep_user` varchar(50) DEFAULT NULL,
  `req_user` varchar(50) DEFAULT NULL,
  `stream_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,NULL,NULL,'ali','rashid','ashn228h'),(2,NULL,NULL,'ali','rashid10','ashn228h'),(7,'7','cars2','rashid10','ali','vm4y89'),(19,NULL,'Samsung','ali','','OJZ00y5gYRpwR02aK1YV602q855op73yKUY'),(19,NULL,'Samsung','ali','rashidd','xROqt5UKK01ZO7601bpuNkSvbUsF1t9SE02'),(21,NULL,'iphone','ali','rashidd','RpAEZCbBBxBws01yDrjpZQZu7eYNHKaco'),(23,NULL,'bag','ali','rashidd','oaKf4CwJapFHYi01bwZ4yuFNwsA7dY9Ml'),(25,NULL,'sunglasses','rashidd','ali','1WmFbWDqjvRchs8011QHf4h02PKZhOZphK'),(25,NULL,'sunglasses','rashidd','ali','801hmvfmkvr2GOqBXkPz8RBeCY02fJQUeA'),(23,NULL,'bag','ali','rashidd','017LovqYG4Bbsp2TC81o5f95p01UEfZhZC'),(5,NULL,'cars2','rashidd','rashid10','RDJl00EZGvkRjiZ00I3uvXIqwjGsxI12Rr'),(7,NULL,'watch','ali','ali','UvMJw6hjm7e2n2eelVSbCfhdfXeTNgKd'),(7,NULL,'watch','ali','ali','e00EThDGHmBDGdSEkm2no01r1OBxT6Bms2'),(8,NULL,'bike','ali','ali','tWFEhnJbknnEyqof6zX700OHsxJnZJlJN'),(8,NULL,'bike','ali','ali','Oh9ojXoHWJwo01GIPVajs3u3TC01ah119w'),(24,NULL,'bag','00971529809876','rashidd','eIwHlZ5FN9myuJQsgZ3J01OsRoKs7wUn7'),(20,NULL,'Samsung','00971529809876','rashidd','6OafZ1wVBiCqQRMRmBYxXUq4zSBbSB3L'),(19,NULL,'Samsung','00971529809876','rashidd','Z3ATzTdb31JpWQUhoUtpg4vxgMwrVDUX'),(1,NULL,'car','00971529809876','rashid','1A00vDEo3bQmMiCXOtrM01cXMQpyNP00RWW'),(23,NULL,'bag','00971529809876','rashidd','FQo4b4RYz84hviCbwzfR1caWK00eB6vVh'),(30,NULL,'iphone','00971509885353','00971529809876','fG2GYpkRHL1knSQzl6RNcagoBkDhOj8V'),(32,NULL,'sunglasses','00971509885353','00971529809876','kmyZEtBGENImMRjMqnLscCEgkW00mwuOE'),(33,NULL,'iphone','00971509885353','00971529809876','rZBe82akoxMMLEHN59XFKdE9rFm1BaiC'),(22,NULL,'iphone','00971529809876','rashidd','01xOMaRslz00H6NGbxKZsz6QEocl4e9vy8'),(21,NULL,'iphone','00971529809876','rashidd','7P7iXlLowiDphIsO1PEofLo201nvUEWDJ'),(20,NULL,'Samsung','00971529809876','rashidd','wnxO1fodHoL4dq023d15pfnS00YXb3XVRy'),(24,NULL,'bag','00971529809876','rashidd','U8V1OK2lAW3RfnXFRAegzaF4tkScxO7Y'),(24,NULL,'bag','00971529809876','rashidd','gq64onV1wX027whF36e6tDajhEHF5n4L7'),(23,NULL,'bag','00971529809876','rashidd','i02nxjh3NkizzSwZ7bkaUsafWv02IVm200t'),(33,NULL,'iphone','00971505390644','00971529809876','WsYAhvMxMG3ztPl8ncUGskXW00StPgkLC'),(20,NULL,'Samsung','00971529809876','rashidd','9j6IopwECrIspOviP01DElBqTarM00xV58'),(20,NULL,'Samsung','00971529809876','rashidd','Frey2YLduYLfFtUAjkwJTy1Xlhw7afNA'),(21,NULL,'iphone','00971529809876','rashidd','Lez43saMw5ZlMjYpBk0202ca9H9Rbnty01J'),(22,NULL,'iphone','00971529809876','rashidd','gJoFLwQxwCjqjZf5eZXgv7hfocWKqczB'),(19,NULL,'Samsung','00971529809876','rashidd','TWVKQMvF9EztyE79ovz9wLQc7zG02xSsv'),(72,NULL,'sunglasses','00971529809876','00971505390644','rG8FI00Vn00fKT8GmEHrZaPTJ94D01TmzSu'),(73,NULL,'iPhone 9','00971529809876','00971505390644','AhVgsmJd18xZlsgi00oHfS7USVKutfHh01'),(74,NULL,'Samsung','00971529809876','00971505390644','TvEchjx7aazHCNGloxVs5soIgWXcsSyQ'),(75,NULL,'sunglasses','00971529809876','00971505390644','ZH74CWC3I24E2XpyNYc8mH6aSkwJuZgx'),(76,NULL,'watch','00971529809876','00971505390644','k1002Ctc1mj5FUFnehkNc2UoEIJcYdAlz'),(77,NULL,'sunglasses','00971529809876','00971505390644','eHw301012YzZmlwpz8UGy8N2oguK5t02H6m');
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `user` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `reply` int(11) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (19,7,'Samsung',NULL,'rashidd','electronics',1),(20,7,'Samsung',NULL,'rashidd','electronics',1),(21,5,'iphone',NULL,'rashidd','electronics',1),(22,5,'iphone',NULL,'rashidd','electronics',1),(23,3,'bag',NULL,'rashidd','clothing',1),(24,3,'bag',NULL,'rashidd','clothing',1),(29,6,'sunglasses','test description','00971529809876','clothing',0),(30,5,'iphone','test description','00971529809876','electronics',0),(32,6,'sunglasses','test description','00971529809876','clothing',0),(33,5,'iphone','test description','00971529809876','electronics',1),(34,5,'iphone','iPhone 7','00971529809876','electronics',0),(35,6,'sunglasses','a description','00971529809876','clothing',0),(53,-1,'Samsung Electronics UN32J4000C 32 Inch Model','some tv','00971505390644','mobiles',1),(54,8,'huawei','demo test','00971505390644','electronics',1),(55,7,'Samsung','demo2','00971505390644','electronics',1),(56,5,'iphone','demoo','00971529809876','electronics',0),(57,4,'watch','demoo','00971505390644','clothing',1),(58,5,'iphone','demoooo','00971505390644','electronics',1),(59,3,'bag','demoooo','00971505390644','clothing',1),(60,6,'sunglasses','deeemo','00971505390644','clothing',1),(61,4,'watch','TT','00971505390644','clothing',1),(62,8,'huawei','RR','00971505390644','electronics',1),(63,8,'huawei','','00971505390644','electronics',1),(64,5,'iphone','','00971505390644','electronics',1),(65,7,'Samsung','','00971505390644','electronics',1),(66,5,'iphone','t','00971505390644','electronics',1),(67,5,'iphone','yyy','00971505390644','electronics',1),(68,5,'iphone','ttf','00971505390644','electronics',1),(69,6,'sunglasses','gg','00971505390644','clothing',0),(70,7,'Samsung','tt','00971505390644','electronics',1),(71,8,'huawei','fine','00971505390644','electronics',1),(72,6,'sunglasses','fine','00971505390644','clothing',1),(73,1,'iPhone 9','64gb','00971505390644','electronics',1),(74,7,'Samsung','tt','00971505390644','electronics',1),(75,6,'sunglasses','tt','00971505390644','clothing',1),(76,4,'watch','test123','00971505390644','clothing',1),(77,6,'sunglasses','testing','00971505390644','clothing',1);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `name` varchar(300) DEFAULT NULL,
  `name1` varchar(50) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  `balance` int(11) DEFAULT '100',
  `verify_atempts` int(11) DEFAULT '5',
  `verify_code` int(11) DEFAULT NULL,
  `verify` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (NULL,'rashid','123123',100,5,NULL,0),(NULL,'ali','7c4a8d09ca3762af61e59520943dc26494f8941b',91,5,NULL,0),(NULL,'rashid1','sha1(123)',100,5,NULL,0),(NULL,'rashid2','sha1(123)',100,5,NULL,0),(NULL,'rashid3','sha1(123)',100,5,NULL,0),(NULL,'rashi5','sha1(1231234)',100,5,NULL,0),(NULL,'rashi6','sha1(1231234)',100,5,NULL,0),(NULL,'rashid10','b9a6e0793e9092d61e8732dc05663132961b1797',100,5,NULL,0),(NULL,'rashidd','7c4a8d09ca3762af61e59520943dc26494f8941b',110,5,NULL,0),('00971529809876',NULL,'7c4a8d09ca3762af61e59520943dc26494f8941b',103,3,7479,1),('00971509885353',NULL,'7c4a8d09ca3762af61e59520943dc26494f8941b',125,5,6058,1),('00971505390644',NULL,'601f1889667efaebb33b8c12572835da3f027f78',71,5,3171,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-14 12:24:59
