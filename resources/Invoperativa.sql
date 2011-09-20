drop database invoperativa;
create database invoperativa;
use invoperativa;

create table Usuario(
Id INT NOT NULL AUTO_INCREMENT,
UserName VARCHAR(30) NOT NULL,
Password VARCHAR(30) NOT NULL,
PRIMARY KEY(Id)) ENGINE=INNODB;

insert into usuario (id, username, password) values (1, 'admin', 'admin');

CREATE TABLE UBICACION(
ID INT NOT NULL AUTO_INCREMENT,
NOMBRE VARCHAR(50) NOT NULL,
TIPO VARCHAR(15) NOT NULL,
LOCALIZACION VARCHAR(255),
LATITUD float(10,7) NOT NULL,
LONGITUD float(10,7) NOT NULL,
UNIDADES int NOT NULL,
PRIMARY KEY(Id)) ENGINE=INNODB;

create table Costo(
Id INT NOT NULL AUTO_INCREMENT,
Desde_Id INT NOT NULL,
Hasta_Id INT NOT NULL,
Costo float(10,5) NOT NULL,
PRIMARY KEY (Id)
) ENGINE=INNODB;

alter table costo add constraint foreign key (desde_id) references ubicacion(id);
alter table costo add constraint foreign key (hasta_id) references ubicacion(id);
