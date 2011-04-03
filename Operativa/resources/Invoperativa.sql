drop database invoperativa;
create database invoperativa;
use invoperativa;

create table Fabrica(
Id INT NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(50) NOT NULL,
Localizacion VARCHAR(255),
Latitud float(10,7) NOT NULL,
Longitud float(10,7) NOT NULL,
Produccion int NOT NULL,
PRIMARY KEY(Id)) ENGINE=INNODB;

create table Usuario(
Id INT NOT NULL AUTO_INCREMENT,
UserName VARCHAR(30) NOT NULL,
Password VARCHAR(30) NOT NULL,
PRIMARY KEY(Id)) ENGINE=INNODB;

create table Punto_Destino(
Id INT NOT NULL AUTO_INCREMENT,
Nombre VARCHAR(50) NOT NULL,
Localizacion VARCHAR(255),
Latitud float(10,7) NOT NULL,
Longitud float(10,7) NOT NULL,
Demanda int NOT NULL,
PRIMARY KEY(Id)) ENGINE=INNODB;

create table Costo(
Id INT NOT NULL AUTO_INCREMENT,
Fabrica_Id INT NOT NULL,
Punto_Destino_Id INT NOT NULL,
Costo float(10,5) NOT NULL,
PRIMARY KEY (Id)
) ENGINE=INNODB;

alter table costo add constraint foreign key (fabrica_id) references fabrica(id);
alter table costo add constraint foreign key (Punto_Destino_id) references punto_destino(id);
