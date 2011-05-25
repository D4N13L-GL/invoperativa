
create database invoperativa;
use invoperativa;

create table Fabrica(
Id INT NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(50) NOT NULL,
Localizacion VARCHAR(255),
Latitud float(10,7) NOT NULL,
Longitud float(10,7) NOT NULL,
Produccion int NOT NULL,
PRIMARY KEY(Id));

create table Usuario(
Id INT NOT NULL AUTO_INCREMENT,
UserName VARCHAR(30) NOT NULL,
Password VARCHAR(30) NOT NULL,
PRIMARY KEY(Id));

create table Punto_Entrega(
Id INT NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(50) NOT NULL,
Localizacion VARCHAR(255),
Latitud float(10,7) NOT NULL,
Longitud float(10,7) NOT NULL,
Demanda int NOT NULL,
PRIMARY KEY(Id));


