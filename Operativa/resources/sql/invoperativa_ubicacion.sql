CREATE DATABASE  IF NOT EXISTS `invoperativa` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `invoperativa`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: invoperativa
-- ------------------------------------------------------
-- Server version	5.1.50-community

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
-- Table structure for table `ubicacion`
--

DROP TABLE IF EXISTS `ubicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubicacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `TIPO` varchar(15) NOT NULL,
  `LOCALIZACION` varchar(255) DEFAULT NULL,
  `LATITUD` float(10,7) NOT NULL,
  `LONGITUD` float(10,7) NOT NULL,
  `UNIDADES` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicacion`
--

LOCK TABLES `ubicacion` WRITE;
/*!40000 ALTER TABLE `ubicacion` DISABLE KEYS */;
INSERT INTO `ubicacion` VALUES (1,'Fab1','FABRICA','Irala, Mar del Plata, General Pueyrredn, Buenos Aires, AR',-38.0048943,-57.6020927,1300),(2,'Fab2','FABRICA','302-400, Azcunaga, Mar del Plata, General Pueyrredn, Buenos Aires, AR',-38.0330429,-57.5428734,500),(3,'Dest1','DESTINO','Laprida, Mar del Plata, General Pueyrredn, Buenos Aires, AR',-38.0110703,-57.5764656,750),(4,'Dest2','DESTINO','4001-4099, Bouchard, Mar del Plata, General Pueyrredn, Buenos Aires, AR',-38.0445213,-57.5612526,750),(7,'Fab3','FABRICA','6002-6100, A. Alice, Mar del Plata, General Pueyrredn, Buenos Aires, AR',-37.9522743,-57.5575943,550),(8,'Dest3','DESTINO','101-199, Jos Ingenieros, Mar del Plata, General Pueyrredn, Buenos Aires, AR',-37.9715881,-57.5599022,550),(9,'Dest4','DESTINO','702-800, Lpez V., Mar del Plata, General Pueyrredn, Buenos Aires, AR',-38.0345230,-57.5487900,300),(11,'Dest6','DESTINO','3901-3999, Alsina, Mar del Plata, General Pueyrredn, Buenos Aires, AR',-38.0200005,-57.5499992,500);
/*!40000 ALTER TABLE `ubicacion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-05-25 18:41:58
