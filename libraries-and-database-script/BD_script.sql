DROP Database IF EXISTS `proyecto2` ;
CREATE Database if NOT EXISTS `proyecto2`;
USE `proyecto2`;
-- -----------------------------------------------------
-- Table `proyecto2`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`persona`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`persona` (
  `pk_cedula` int NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellidos` VARCHAR(25) NOT NULL,
  `direccion` varchar(35) not null, 
  `telefono` int not null,
  PRIMARY KEY (`pk_cedula`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`usuario`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`usuario` (
  `pk_usuario` varchar(15) NOT NULL,
  `contrasena` varchar(15) NOT NULL,
  `tipo` boolean NOT NULL,
  `persona_fk` int not null,
  PRIMARY KEY (`pk_usuario`),
  CONSTRAINT `fk_persona_pk_cedula`
    FOREIGN KEY (`persona_fk`)
    REFERENCES `proyecto2`.`persona` (`pk_cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`comentario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`comentario`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`comentario` (
  `pk_usuario_fk` varchar(15) NOT NULL,
  `comentario` varchar(100) NOT NULL,
  CONSTRAINT `fk_usuario_pk_usuario`
    FOREIGN KEY (`pk_usuario_fk`)
    REFERENCES `proyecto2`.`usuario` (`pk_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`factura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`factura`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`factura` (
  `id` int NOT NULL auto_increment,
  `precioTotal` double NOT NULL,
  `metodoPago` boolean not null,
  `estado` varchar(20) not null,
  `tiempo` date not null,
  `pk_usuario_fk` varchar(15),
  PRIMARY KEY (`id`),
  CONSTRAINT `pk_usuario_fk_usuario`
    FOREIGN KEY (`pk_usuario_fk`)
    REFERENCES `proyecto2`.`usuario` (`pk_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`complemento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`complemento`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`complemento` (
  `id` int NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `precio` double not null,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`detalleComplemento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`detalleComplemento`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`detalleComplemento` (
  `id` int NOT NULL auto_increment,
  `id_factura_fk` int NOT NULL,
  `id_complemento_fk` int not null,
  `cantidad` int not null,
  PRIMARY KEY (`id`),
  CONSTRAINT `id_factura_fk_factura`
    FOREIGN KEY (`id_factura_fk`)
    REFERENCES `proyecto2`.`factura` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `id_complemento_fk_complemento`
    FOREIGN KEY (`id_complemento_fk`)
    REFERENCES `proyecto2`.`complemento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`ingrediente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`ingrediente`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`ingrediente` (
  `id` int NOT NULL,
  `nombre` varchar(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`pizza`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`pizza`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`pizza` (
  `id` int NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `precio` double not null,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`detalleComplemento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`detalleIngrediente`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`detalleIngrediente` (
  `pk_id_pizza_fk` int NOT NULL,
  `pk_id_ingrediente_fk` int not null,
  PRIMARY KEY (`pk_id_pizza_fk`,`pk_id_ingrediente_fk`),
  CONSTRAINT `pk_id_pizza_fk_pizza`
    FOREIGN KEY (`pk_id_pizza_fk`)
    REFERENCES `proyecto2`.`pizza` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `pk_id_ingrediente_fk_ingrediente`
    FOREIGN KEY (`pk_id_ingrediente_fk`)
    REFERENCES `proyecto2`.`ingrediente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `proyecto2`.`detallePizza`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proyecto2`.`detallePizza`;
CREATE TABLE IF NOT EXISTS `proyecto2`.`detallePizza` (
  `id` int NOT NULL auto_increment,
  `id_factura_fk` int NOT NULL,
  `id_pizza_fk` int not null,
  `cantidad` int not null,
  `tamano` varchar(15) not null,
  PRIMARY KEY (`id`),
  CONSTRAINT `id_factura_fk_factura2`
    FOREIGN KEY (`id_factura_fk`)
    REFERENCES `proyecto2`.`factura` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `id_pizza_fk_pizza`
    FOREIGN KEY (`id_pizza_fk`)
    REFERENCES `proyecto2`.`pizza` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Inserts
-- -----------------------------------------------------
INSERT INTO `proyecto2`.`persona`
	(`pk_cedula`, `nombre`, `apellidos`, `direccion`, `telefono`)
	VALUES
	(12345678, 'Ariel', 'Chaves Herrera', 'Alajuela', 83340009),
	(87654321, 'Luis', 'Venegas Ulloa', 'Heredia', 83412288);
INSERT INTO `proyecto2`.`usuario`
	(`pk_usuario`, `contrasena`, `tipo`, `persona_fk`)
	VALUES
	('arielch', 'hola1', true, 12345678),
	('luisvu', 'hola2', false, 87654321);
INSERT INTO `proyecto2`.`ingrediente`
	(`id`, `nombre`)
	VALUES
	(1001, 'Salsa'),
	(1002, 'Jamon'),
  (1003, 'Queso');
insert into ingrediente(id,nombre)values(1004,'carne molida');
insert into ingrediente(id,nombre)values(1005,'hongos');
insert into ingrediente(id,nombre)values(1006,'aguacate');
insert into ingrediente(id,nombre)values(1007,'frijoles molido');
insert into ingrediente(id,nombre)values(1008,'sal y aceite');
insert into ingrediente(id,nombre)values(1009,'albahaca');
insert into ingrediente(id,nombre)values(1010,'chile jalapeño');
insert into ingrediente(id,nombre)values(1011,'tocino');
insert into ingrediente(id,nombre)values(1012,'salami');
insert into ingrediente(id,nombre)values(1013,'peperoni');
insert into ingrediente(id,nombre)values(1014,'piña');
INSERT INTO `proyecto2`.`complemento`
	(`id`, `nombre`, `precio`)
	VALUES
	(2001, 'Coca Cola', 1100),
	(2002, 'Pepsi', 1000),
  (2003, 'Natural', 950),
	(2004, 'Queso Extra', 600),
	(2005, 'Salsa Extra', 750),
        (2006, 'Hongos', 800),
        (2007, 'Pan', 950),
        (2008, 'Chile', 1250),
        (2009, 'Cafe', 1000);
INSERT INTO `proyecto2`.`pizza`
	(`id`, `nombre`,`precio`)
	VALUES
	(3001, 'Margarita', 1500),
    (3002, 'Suprema', 2150),
    (3003, 'jamon y queso', 1650),
    (3004, 'peperoni', 1500),
    (3005, 'hawaiana', 1750),
    (3006, 'mexicana', 2000);
    
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3001,1001);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3001,1008);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3001,1003);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3001,1009);

insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3002,1001);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3002,1002);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3002,1003);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3002,1004);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3002,1005);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3002,1011);

insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3003,1001);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3003,1002);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3003,1003);

insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3004,1001);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3004,1002);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3004,1013);


insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3005,1001);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3005,1002);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3005,1003);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3005,1014);

insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3006,1001);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3006,1004);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3006,1003);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3006,1005);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3006,1006);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3006,1007);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3006,1010);
insert into detalleIngrediente(pk_id_pizza_fk,pk_id_ingrediente_fk)values(3006,1011);

    
      SET GLOBAL time_zone = '-6:00';