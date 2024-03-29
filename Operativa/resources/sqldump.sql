Warning: The option '--all' is deprecated and will be removed in a future release. Please use --create-options instead.
-- MySQL dump 10.13  Distrib 5.1.50, for Win64 (unknown)
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
-- Table structure for table `costo`
--

DROP TABLE IF EXISTS `costo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `costo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Desde_Id` int(11) NOT NULL,
  `Hasta_Id` int(11) NOT NULL,
  `Costo` float(10,5) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Desde_Id` (`Desde_Id`),
  KEY `Hasta_Id` (`Hasta_Id`),
  CONSTRAINT `costo_ibfk_1` FOREIGN KEY (`Desde_Id`) REFERENCES `ubicacion` (`ID`),
  CONSTRAINT `costo_ibfk_2` FOREIGN KEY (`Hasta_Id`) REFERENCES `ubicacion` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costo`
--

LOCK TABLES `costo` WRITE;
/*!40000 ALTER TABLE `costo` DISABLE KEYS */;
INSERT INTO `costo` (`Id`, `Desde_Id`, `Hasta_Id`, `Costo`) VALUES (45,15,11,173.96600),(46,11,15,173.96600),(47,15,13,127.60800),(48,13,15,127.60800),(49,15,14,621.39398),(50,14,15,621.39398),(51,16,11,226.32800),(52,11,16,226.32800),(53,16,13,258.21899),(54,13,16,258.21899),(55,16,14,567.98798),(56,14,16,567.98798),(57,17,11,182.98700),(58,11,17,182.98700),(59,17,13,142.11000),(60,13,17,142.11000),(61,17,14,590.54797),(62,14,17,590.54797),(63,18,11,329.25400),(64,11,18,329.25400),(65,18,13,466.68500),(66,13,18,466.68500),(67,18,14,299.56500),(68,14,18,299.56500),(69,18,15,368.39801),(70,15,18,368.39801),(71,18,16,313.37500),(72,16,18,313.37500),(73,19,11,379.04999),(74,11,19,379.04999),(75,19,13,537.13702),(76,13,19,537.13702),(77,19,14,132.84100),(78,14,19,132.84100),(79,19,15,533.47400),(80,15,19,533.47400),(81,20,11,123.18500),(82,11,20,123.18500),(83,20,13,118.00500),(84,13,20,118.00500),(85,20,14,602.09198),(86,14,20,602.09198),(87,20,15,85.49200),(88,15,20,85.49200),(89,20,16,188.44299),(90,16,20,188.44299),(91,20,17,256.81000),(92,17,20,256.81000),(93,20,18,349.20901),(94,18,20,349.20901);
/*!40000 ALTER TABLE `costo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabrica`
--

DROP TABLE IF EXISTS `fabrica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabrica` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Localizacion` varchar(255) DEFAULT NULL,
  `Latitud` float(10,7) NOT NULL,
  `Longitud` float(10,7) NOT NULL,
  `Produccion` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabrica`
--

LOCK TABLES `fabrica` WRITE;
/*!40000 ALTER TABLE `fabrica` DISABLE KEYS */;
/*!40000 ALTER TABLE `fabrica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `punto_entrega`
--

DROP TABLE IF EXISTS `punto_entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `punto_entrega` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Localizacion` varchar(255) DEFAULT NULL,
  `Latitud` float(10,7) NOT NULL,
  `Longitud` float(10,7) NOT NULL,
  `Demanda` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punto_entrega`
--

LOCK TABLES `punto_entrega` WRITE;
/*!40000 ALTER TABLE `punto_entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `punto_entrega` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicacion`
--

LOCK TABLES `ubicacion` WRITE;
/*!40000 ALTER TABLE `ubicacion` DISABLE KEYS */;
INSERT INTO `ubicacion` (`ID`, `NOMBRE`, `TIPO`, `LOCALIZACION`, `LATITUD`, `LONGITUD`, `UNIDADES`) VALUES (11,'Fabrica Tandil','FABRICA','1400-1499, Av Col┬Øn, Tandil, Tandil, Buenos Aires, AR',-37.3100014,-59.1300011,13000),(13,'Fabrica Necochea','FABRICA','1702-1800, Av 75, Necochea, Necochea, Buenos Aires, AR',-38.5600014,-58.7299995,9000),(14,'Fabrica Pergamino','FABRICA','Ruta Provincial 178, Pergamino, Pergamino, Buenos Aires, AR',-33.8899994,-60.5600014,8000),(15,'Destino Mar del Plata','DESTINO','Hudson, Mar del Plata, General Pueyrred┬Øn, Buenos Aires, AR',-38.0000000,-57.5699997,6000),(16,'Destino Pinamar','DESTINO','Av del Libertador, Pinamar, Pinamar, Buenos Aires, AR',-37.1100006,-56.8600006,5000),(17,'Destino Tres Arroyos','DESTINO','201-299, Av Moreno, Tres Arroyos, Tres Arroyos, Buenos Aires, AR',-38.3699989,-60.2700005,3000),(18,'Destino La Plata','DESTINO','1002-1050, Calle 12, La Plata, La Plata, Buenos Aires, AR',-34.9199982,-57.9500008,9000),(19,'Destino Chacabuco','DESTINO','251-299, Av Solis, Chacabuco, Chacabuco, Buenos Aires, AR',-34.6399994,-60.4599991,5000),(20,'Destino Balcarce','DESTINO','Ruta Provincial 55, Balcarce, Buenos Aires, AR',-37.8199997,-58.2299995,2000);
/*!40000 ALTER TABLE `ubicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`Id`, `UserName`, `Password`) VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-07-27 22:39:45
