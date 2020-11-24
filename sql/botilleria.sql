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

    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id_fk) REFERENCES cliente(id)
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

-- 1 Ingresar producto
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


-- 2 Eliminar PRODUCTO
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


-- 3 Cambiar contraseña
DELIMITER //
CREATE PROCEDURE cambiar_pass(IN _user VARCHAR(50),_pass VARCHAR(200),passnew VARCHAR(200))
BEGIN
    DECLARE verificador_ INT;
    DECLARE verificador2_ INT;

    SET verificador_ = (SELECT COUNT(*) 
    FROM trabajador
    WHERE username = _user);

    SET verificador2_ = (SELECT COUNT(*) 
    FROM trabajador
    WHERE contraseña = SHA2(_pass,0));

    IF verificador_ = 1 AND verificador2_ = 1 THEN 
        UPDATE trabajador SET contraseña = SHA2(passnew,0) WHERE username = _user;
        SELECT 'Su contraseña a sido cambiada' AS 'Alerta';
    ELSE
        SELECT 'El usuario o la contraseña son incorrectos' AS 'Alerta';
    END IF;
END //
DELIMITER ;

CALL cambiar_pass('nico','hola','holamundo');


-- 4 Cambiar nombre de usuario
DELIMITER //
CREATE PROCEDURE cambiar_user(IN _user VARCHAR(50),_pass VARCHAR(200),usernew VARCHAR(200))
BEGIN
    DECLARE verificador_ INT;
    DECLARE verificador2_ INT;

    SET verificador_ = (SELECT COUNT(*) 
    FROM trabajador
    WHERE username = _user);

    SET verificador2_ = (SELECT COUNT(*) 
    FROM trabajador
    WHERE contraseña = SHA2(_pass,0));

    IF verificador_ = 1 AND verificador2_ = 1 THEN 
        UPDATE trabajador SET username = usernew WHERE username = _user;
        SELECT 'Su usuario a sido cambiado' AS 'Alerta';
    ELSE
        SELECT 'El usuario o la contraseña son incorrectos' AS 'Alerta';
    END IF;
END //
DELIMITER ;

<<<<<<< HEAD
=======
call cambiar_user('carlos','perrito','carlitos perrito');

>>>>>>> aad2b7341e56ff6d6d2f0ce6bb40cd65992b826b

