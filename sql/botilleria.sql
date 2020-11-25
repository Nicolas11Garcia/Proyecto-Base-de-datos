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
    activo BIT(1),

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

CREATE TABLE productos_inactivos(
    id INT AUTO_INCREMENT,
    id_antiguo_fk INT,
    nombre VARCHAR(100),
    precio INT,
    fecha_desactivacion DATETIME,

    PRIMARY KEY(id),
    FOREIGN KEY (id_antiguo_fk) REFERENCES producto(id)
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




-- 2 Desactivar PRODUCTO
DELIMITER //
CREATE PROCEDURE desactivar_producto(IN _id INT)
BEGIN
    DECLARE verificador_ INT;

    SET verificador_ = (SELECT COUNT(*) 
    FROM producto 
    WHERE id = _id);

    IF verificador_ = 1 THEN 
        UPDATE producto SET activo = 0 WHERE id = _id;
        SELECT 'Producto Desactivado' AS 'Alerta';
    ELSE
        SELECT 'Producto No encontrado' AS "Alerta";
    END IF;
END //
DELIMITER ;




-- 3 Activar PRODUCTO
DELIMITER //
CREATE PROCEDURE activar_producto(IN _id INT)
BEGIN
    DECLARE verificador_ INT;

    SET verificador_ = (SELECT COUNT(*) 
    FROM producto 
    WHERE id = _id);

    IF verificador_ = 1 THEN 
        UPDATE producto SET activo = 1 WHERE id = _id;
        SELECT 'Producto Activado' AS 'Alerta';
    ELSE
        SELECT 'Producto No encontrado' AS "Alerta";
    END IF;
END //
DELIMITER ;


-- 4 Cambiar contraseña
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



-- 5 Cambiar nombre de usuario
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

-- Funcion
DELIMITER //
CREATE FUNCTION ver_producto(_id INT) RETURNS VARCHAR(100)
BEGIN
    RETURN (SELECT nombre FROM producto WHERE id = _id);
END //
DELIMITER ;
SELECT ver_producto(1); 

-- Triggers
-- 1) Cuando se desactive un producto que se guarde en la tabla
DELIMITER //
CREATE TRIGGER desactivar_producto BEFORE UPDATE ON producto
    FOR EACH ROW
BEGIN
    IF NEW.activo = 0 THEN
	INSERT INTO productos_inactivos VALUES(NULL,OLD.id,OLD.nombre,OLD.precio,NOW());
    END IF;
END //
DELIMITER ;

-- 2) Cuando se active un producto que se guarde en la tabla
DELIMITER //
CREATE TRIGGER activos_productos BEFORE UPDATE ON producto
    FOR EACH ROW
BEGIN
    IF NEW.activo = 1 THEN
    DELETE from productos_inactivos WHERE id_antiguo_fk = OLD.id;
    END IF;
END //
DELIMITER ;



-- Para llamar a los procedimientos
CALL ingresar_producto("Sandias",500);
CALL desactivar_producto(3);
CALL activar_producto(3);
CALL cambiar_pass('nico','hola','holamundo');