CREATE DATABASE botilleria;

USE botilleria;

-- TABLAS

CREATE TABLE trabajador (
    id INT AUTO_INCREMENT,
    username VARCHAR(50),
    contraseña VARCHAR(200),

    PRIMARY KEY (id)
);

CREATE TABLE cliente (
    id INT AUTO_INCREMENT,
    rut VARCHAR(13),
    nombre VARCHAR(200),

    PRIMARY KEY (id),
    UNIQUE (rut)
);


CREATE TABLE producto(
    id INT AUTO_INCREMENT,
    nombre VARCHAR(100),
    precio INT,

    PRIMARY KEY(id)
); 

CREATE TABLE factura (
    id INT AUTO_INCREMENT,
    cliente_id_fk INT,
    fecha DATETIME,
    trabajador_id_fk INT,

    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id_fk) REFERENCES cliente(id),
    FOREIGN KEY (trabajador_id_fk) REFERENCES trabajador(id)
);
  

CREATE TABLE detalle(
    id INT AUTO_INCREMENT,
    factura_id_fk INT,
    producto_id_fk INT,
    cantidad INT,
    precio INT,

    PRIMARY KEY (id),
    FOREIGN KEY (factura_id_fk) REFERENCES factura(id),
    FOREIGN KEY (producto_id_fk) REFERENCES producto(id)
);

-- Procedimientos Almacenados

-- 1 Agregar producto
DELIMITER //
CREATE PROCEDURE ingresar_producto(IN _nombre VARCHAR(100), _precio INT)
BEGIN

    DECLARE verificador INT ;

    SET verificador = (SELECT COUNT(*) 
    FROM producto 
    WHERE nombre = _nombre);

    IF verificador = 0 THEN 
        INSERT INTO producto VALUES (NULL,_nombre,_precio);
        SELECT 'Producto Agregado con exito' AS 'Alerta';
    ELSE
        SELECT 'No puede agregar productos repetidos' AS "Alerta";
    END IF;
END //
DELIMITER ; 

CALL ingresar_producto("Sandias",500);


-- 2 BORRAR PRODUCTO
DELIMITER //
CREATE PROCEDURE borrar_producto(IN _id INT)
BEGIN
    DECLARE verificador_ INT;

    SET verificador_ = (SELECT COUNT(*) 
    FROM producto 
    WHERE id = _id);

    IF verificador_ = 1 THEN 
        DELETE FROM producto WHERE id = _id;
        SELECT 'Producto Eliminado Con Exito' AS 'Alerta';
    ELSE
        SELECT 'Producto No encontrado' AS "Alerta";
    END IF;
END //
DELIMITER ;

CALL borrar_producto(3);



