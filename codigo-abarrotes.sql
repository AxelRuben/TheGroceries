-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Abarrotes
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Abarrotes` ;

-- -----------------------------------------------------
-- Schema Abarrotes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Abarrotes` DEFAULT CHARACTER SET utf8 ;
USE `Abarrotes` ;

-- -----------------------------------------------------
-- Table `Abarrotes`.`Empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Empleados` (
  `idEmpleados` INT NOT NULL AUTO_INCREMENT,
  `NombreC` VARCHAR(100) NOT NULL,
  `Telefono` VARCHAR(100) NOT NULL,
  `Direccion` VARCHAR(100) NOT NULL,
  `Estudios` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idEmpleados`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abarrotes`.`Proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Proveedor` (
  `idProveedor` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProveedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abarrotes`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Producto` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Tipo` ENUM('Abarrotes', 'Enlatados', 'Lacteos', 'Botanas', 'Harinas', 'FrutasyVerduras', 'Bebidas', 'BebidasAlcoholicas', 'CarnesEmbutidos', 'Automedicacion', 'HigienePersonal', 'UsoDomestico', 'Jarceria', 'Otros') NOT NULL,
  `Cod_Bar` VARCHAR(45) NULL,
  `Stock` DOUBLE NOT NULL,
  `Proveedor_idProveedor` INT NOT NULL,
  `Costo` DOUBLE NOT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `fk_Producto_Proveedor_idx` (`Proveedor_idProveedor` ASC),
  CONSTRAINT `fk_Producto_Proveedor`
    FOREIGN KEY (`Proveedor_idProveedor`)
    REFERENCES `Abarrotes`.`Proveedor` (`idProveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abarrotes`.`Ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Ventas` (
  `idVentas` INT NOT NULL AUTO_INCREMENT,
  `Fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Total` DOUBLE NOT NULL,
  `Pago` DOUBLE NOT NULL,
  `Cambio` DOUBLE NOT NULL,
  `Empleados_idEmpleados` INT NOT NULL,
  PRIMARY KEY (`idVentas`),
  INDEX `fk_Ventas_Empleados1_idx` (`Empleados_idEmpleados` ASC),
  CONSTRAINT `fk_Ventas_Empleados1`
    FOREIGN KEY (`Empleados_idEmpleados`)
    REFERENCES `Abarrotes`.`Empleados` (`idEmpleados`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abarrotes`.`Ventas_has_Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Ventas_has_Producto` (
  `Ventas_idVentas` INT NOT NULL,
  `Producto_idProducto` INT NOT NULL,
  `CantidadDP` DOUBLE NOT NULL,
  `Subtotal` DOUBLE NOT NULL,
  PRIMARY KEY (`Ventas_idVentas`, `Producto_idProducto`),
  INDEX `fk_Ventas_has_Producto_Producto1_idx` (`Producto_idProducto` ASC),
  INDEX `fk_Ventas_has_Producto_Ventas1_idx` (`Ventas_idVentas` ASC),
  CONSTRAINT `fk_Ventas_has_Producto_Ventas1`
    FOREIGN KEY (`Ventas_idVentas`)
    REFERENCES `Abarrotes`.`Ventas` (`idVentas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ventas_has_Producto_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `Abarrotes`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abarrotes`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Contrasenia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuarios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abarrotes`.`Surtido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Surtido` (
  `idSurtido` INT NOT NULL,
  `Total` DOUBLE NOT NULL,
  `Fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idSurtido`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abarrotes`.`Producto_has_Surtido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Producto_has_Surtido` (
  `Producto_idProducto` INT NOT NULL,
  `Surtido_idSurtido` INT NOT NULL,
  `Cantidad` INT NOT NULL,
  PRIMARY KEY (`Producto_idProducto`, `Surtido_idSurtido`),
  INDEX `fk_Producto_has_Surtido_Surtido1_idx` (`Surtido_idSurtido` ASC),
  INDEX `fk_Producto_has_Surtido_Producto1_idx` (`Producto_idProducto` ASC),
  CONSTRAINT `fk_Producto_has_Surtido_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `Abarrotes`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_has_Surtido_Surtido1`
    FOREIGN KEY (`Surtido_idSurtido`)
    REFERENCES `Abarrotes`.`Surtido` (`idSurtido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Abarrotes`.`Usuarios`
-- -----------------------------------------------------
START TRANSACTION;
USE `Abarrotes`;
INSERT INTO `Abarrotes`.`Usuarios` (`idUsuarios`, `Nombre`, `Contrasenia`) VALUES (DEFAULT, 'Administrador', '123456789');

COMMIT;

/*--------------------Insert--------------------*/
use abarrotes;

DELIMITER //
create procedure insintoempl(in nom varchar(100), in tel varchar(100), in dir varchar(100), in est varchar(255))
BEGIN
insert into empleados(nombreC,telefono,direccion,estudios) values(nom,tel,dir,est);
END//
delimiter ;

DELIMITER //
create procedure insintovent(in tot double, in pag double, in cam double,  in idempl int)
BEGIN
insert into ventas(total, pago, cambio, Empleados_idEmpleado) values(tot,pag,cam,idempl);
END//
delimiter ; 

DELIMITER //
create procedure insintoventhprod(in venidven int, in proidpro int, in cantdp double, in subtot double)
BEGIN
insert into Venta_has_Producto(Ventas_idVentas,Producto_idProducto,CantidadDP,Subtotal) values(venidven,proidpro,cantdp,subtot);
END//
delimiter ;

DELIMITER //
create procedure insintoprod(in nom varchar(100), in tip varchar(100), in codb varchar(100),in sto double, in pro int,in cos double)
BEGIN
insert into Producto(Nombre,Tipo,cod_bar,stock,proveedor_idproveedor,costo) values(nom,tip,codb,sto,pro,cos);
END//
delimiter ;

DELIMITER //
create procedure insintoprodsc(in nom varchar(100), in tip varchar(100),in sto int, in pro int,in cos double)
BEGIN
insert into Producto(Nombre,Tipo,stock,proveedor_idproveedor,costo) values(nom,tip,sto,pro,cos);
END//
delimiter ; 

DELIMITER //
create procedure insintoprodhsurt(in prodidprod int,in surtidsurt int,in cant double)
BEGIN
insert into Producto_has_Surtido(producto_idproducto,surtido_idsurtido,cantidad) values(prodidprod,surtidsurt,cant);
END//
delimiter ;

DELIMITER //
create procedure insintosurt(in tot int)
BEGIN
insert into Surtido(total) values(tot);
END//
delimiter ;

DELIMITER //
create procedure insintoprov(in nom varchar(100),in tel varchar(10))
BEGIN
insert into Proveedor(nombre,telefono) values(nom,tel);
END//
delimiter ;

/*--------------------Delete--------------------*/
/*--------------------Update--------------------*/
/*--------------------SelectAll-----------------*/
/*--------------------SelectId------------------*/