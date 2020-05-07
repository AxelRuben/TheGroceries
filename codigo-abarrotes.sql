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
  `CostoAlCl` DOUBLE NOT NULL,
  `CostoAlDu` DOUBLE NOT NULL,
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
  `idvhp` INT NOT NULL AUTO_INCREMENT,
  `Ventas_idVentas` INT NOT NULL,
  `Producto_idProducto` INT NOT NULL,
  `CantidadDP` DOUBLE NOT NULL,
  `Subtotal` DOUBLE NOT NULL,
  INDEX `fk_Ventas_has_Producto_Producto1_idx` (`Producto_idProducto` ASC),
  INDEX `fk_Ventas_has_Producto_Ventas1_idx` (`Ventas_idVentas` ASC),
  PRIMARY KEY (`idvhp`),
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
  `idSurtido` INT NOT NULL AUTO_INCREMENT,
  `Fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Total` DOUBLE NOT NULL,
  `Pago` DOUBLE NOT NULL,
  `Cambio` DOUBLE NOT NULL,
  PRIMARY KEY (`idSurtido`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Abarrotes`.`Producto_has_Surtido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Abarrotes`.`Producto_has_Surtido` (
  `idphs` INT NOT NULL AUTO_INCREMENT,
  `Cantidad` INT NOT NULL,
  `Subtotal` DOUBLE NOT NULL,
  `Surtido_idSurtido` INT NOT NULL,
  `Producto_idProducto` INT NOT NULL,
  PRIMARY KEY (`idphs`),
  INDEX `fk_Producto_has_Surtido_Surtido1_idx` (`Surtido_idSurtido` ASC),
  INDEX `fk_Producto_has_Surtido_Producto1_idx` (`Producto_idProducto` ASC),
  CONSTRAINT `fk_Producto_has_Surtido_Surtido1`
    FOREIGN KEY (`Surtido_idSurtido`)
    REFERENCES `Abarrotes`.`Surtido` (`idSurtido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_has_Surtido_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `Abarrotes`.`Producto` (`idProducto`)
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
create procedure insintoventhprod(in venidven int, in proidpro int, in cantdp double, in subtot double)
BEGIN
insert into Ventas_has_Producto(Ventas_idVentas,Producto_idProducto,CantidadDP,Subtotal) values(venidven,proidpro,cantdp,subtot);
END//
delimiter ;

DELIMITER //
create procedure insintoprod(in nom varchar(100), in tip varchar(100), in codb varchar(100),in sto double, in pro int,in cosac double,in cosad double)
BEGIN
insert into Producto(Nombre,Tipo,cod_bar,stock,proveedor_idproveedor,costoalcl,costoaldu) values(nom,tip,codb,sto,pro,cosac,cosad);
END//
delimiter ;

DELIMITER //
create procedure insintoprodsc(in nom varchar(100), in tip varchar(100),in sto int, in pro int,in cosac double,in cosad double)
BEGIN
insert into Producto(Nombre,Tipo,stock,proveedor_idproveedor,costoalcl,costoaldu) values(nom,tip,sto,pro,cosac,cosad);
END//
delimiter ; 

DELIMITER //
create procedure insintoprodhsurt(in prodidprod int,in surtidsurt int,in cant double,in sub double)
BEGIN
insert into Producto_has_Surtido(producto_idproducto,surtido_idsurtido,cantidad,subtotal) values(prodidprod,surtidsurt,cant,sub);
END//
delimiter ;

DELIMITER //
create procedure insintoprov(in nom varchar(100),in tel varchar(10))
BEGIN
insert into Proveedor(nombre,telefono) values(nom,tel);
END//
delimiter ;
/*
SELECT * FROM surtido;
SELECT * FROM producto_has_surtido;
SELECT * FROM producto;
DESCRIBE surtido;
select distinct p.nombre, p.costoalcl, phs.cantidad, phs.subtotal from surtido s,producto_has_surtido phs, producto p where phs.producto_idproducto=p.idproducto and phs.surtido_idsurtido=4;

select s.idsurtido, s.fecha,pr.nombre from surtido s, producto_has_surtido phs, producto p, proveedor pr 
where phs.surtido_idSurtido=s.idsurtido 
and p.idproducto=phs.producto_idproducto 
and pr.idProveedor=p.proveedor_idProveedor;

select p.nombre, p.costoalcl, phs.cantidad, phs.subtotal from surtido s,producto_has_surtido phs, producto p where phs.producto_idproducto=p.idproducto and phs.surtido_idsurtido=1;

select * from producto_has_surtido;
select * from ventas_has_producto;
select * from surtido;
select distinct s.idsurtido,s.fecha,pr.nombre from surtido s, producto_has_surtido phs, producto p, proveedor pr where pr.idProveedor=p.proveedor_idProveedor and p.idproducto=phs.producto_idproducto and phs.surtido_idSurtido=s.idsurtido;

select * from ventas_has_producto;
select * from surtido;
select * from Producto_has_Surtido;
select * from ventas_has_producto;
select * from Producto_has_Surtido;
select * from surtido;


call insintosurt(12.2,12.2,12.2);
select * from surtido;
delete from surtido where idSurtido=0;
insert into Surtido(total,pago,cambio) values(12.2,12.2,12.2);

select * from producto where proveedor_idproveedor=1;
select v.idVentas,v.fecha,e.nombreC from ventas v, empleados e where v.empleados_idEmpleados=e.idEmpleados;
select p.nombre,p.costo,vhp.cantidadDP,vhp.subtotal from ventas_has_producto vhp,producto p where vhp.producto_idproducto=p.idProducto and vhp.ventas_idventas=3;
select * from ventas_has_producto;*/
/*--------------------Delete--------------------*/
/*--------------------Update--------------------*/
/*--------------------SelectAll-----------------*/
/*--------------------SelectId------------------*/