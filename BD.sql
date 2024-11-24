CREATE DATABASE if NOT EXISTS nominas;
USE nominas;

CREATE TABLE `empleados` (
    `dni` VARCHAR(10) NOT NULL COLLATE 'latin1_swedish_ci',
    `nombre` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `sexo` CHAR(1) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
    `anyos` INT(11) NULL DEFAULT '0',
    `categoria` INT(11) NULL DEFAULT '1',
    PRIMARY KEY (`dni`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE `nominas` (
	`empleado_dni` VARCHAR(10) NOT NULL COLLATE 'latin1_swedish_ci',
	`sueldo` DECIMAL(10,2) NULL DEFAULT NULL,
	PRIMARY KEY (`empleado_dni`) USING BTREE,
	CONSTRAINT `nominas_ibfk_1` FOREIGN KEY (`empleado_dni`) REFERENCES `empleados` (`dni`) 
        ON UPDATE RESTRICT ON DELETE CASCADE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

DELIMITER $$

CREATE TRIGGER `after_empleado_insert`
AFTER INSERT ON `empleados`
FOR EACH ROW
BEGIN
    DECLARE sueldo_base DECIMAL(10, 2);

    SET sueldo_base = CASE
        WHEN NEW.categoria = 1 THEN 50000
        WHEN NEW.categoria = 2 THEN 70000
        WHEN NEW.categoria = 3 THEN 90000
        WHEN NEW.categoria = 4 THEN 110000
        WHEN NEW.categoria = 5 THEN 130000
        WHEN NEW.categoria = 6 THEN 150000
        WHEN NEW.categoria = 7 THEN 170000
        WHEN NEW.categoria = 8 THEN 190000
        WHEN NEW.categoria = 9 THEN 210000
        WHEN NEW.categoria = 10 THEN 230000
        ELSE 50000 
    END;

    INSERT INTO nominas (empleado_dni, sueldo)
    VALUES (NEW.dni, sueldo_base + (5000 * NEW.anyos));
END$$

DELIMITER ;


DELIMITER $$

CREATE TRIGGER `after_empleado_update`
AFTER UPDATE ON `empleados`
FOR EACH ROW
BEGIN
    DECLARE sueldo_base DECIMAL(10, 2);

    SET sueldo_base = CASE
        WHEN NEW.categoria = 1 THEN 50000
        WHEN NEW.categoria = 2 THEN 70000
        WHEN NEW.categoria = 3 THEN 90000
        WHEN NEW.categoria = 4 THEN 110000
        WHEN NEW.categoria = 5 THEN 130000
        WHEN NEW.categoria = 6 THEN 150000
        WHEN NEW.categoria = 7 THEN 170000
        WHEN NEW.categoria = 8 THEN 190000
        WHEN NEW.categoria = 9 THEN 210000
        WHEN NEW.categoria = 10 THEN 230000
        ELSE 50000 
    END;

    UPDATE nominas
    SET sueldo = sueldo_base + (5000 * NEW.anyos)
    WHERE empleado_dni = NEW.dni;
END$$

DELIMITER ;


INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES
('12345678A', 'Juan Pérez', 'M', 3, 5),
('87654321B', 'María Gómez', 'F', 2, 3),
('11223344C', 'Carlos Rodríguez', 'M', 5, 8),
('22334455D', 'Ana Sánchez', 'F', 4, 2),
('33445566E', 'Luis Fernández', 'M', 1, 0),
('44556677F', 'Laura Martínez', 'F', 6, 7);
