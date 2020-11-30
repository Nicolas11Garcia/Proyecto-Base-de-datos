CREATE DATABASE botilleria;

USE botilleria;

-- TABLAS
-- Crear Tabla Trabajador
CREATE TABLE trabajador (
    id INT AUTO_INCREMENT,
    username VARCHAR(50),
    contraseña VARCHAR(200),

    PRIMARY KEY (id),
    UNIQUE (username)
);
-- Crear Tabla Cliente
CREATE TABLE cliente (
    id INT AUTO_INCREMENT,
    rut VARCHAR(13),
    nombre VARCHAR(200),

    PRIMARY KEY (id),
    UNIQUE (rut)
);
-- Crear Tabla Producto
CREATE TABLE producto(
    id INT AUTO_INCREMENT,
    nombre VARCHAR(100),
    precio INT,
    activo BIT,

    PRIMARY KEY(id)
);
-- Crear Tabla Factura
CREATE TABLE factura (
    id INT AUTO_INCREMENT,
    cliente_id_fk INT,
    fecha DATETIME,

    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id_fk) REFERENCES cliente(id)
);
-- Crear Tabla Detalle
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
-- Crear Tabal para los Productos Inactivos
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

-- 1) Ingresar producto
DELIMITER //
CREATE PROCEDURE ingresar_producto(IN _nombre VARCHAR(100), _precio INT)
BEGIN

    DECLARE verificar_ingreso INT;

    SET verificar_ingreso = (SELECT COUNT(*) 
    FROM producto 
    WHERE nombre = _nombre);

    IF verificar_ingreso = 0 THEN 
        INSERT INTO producto VALUES (NULL,_nombre,_precio,1);
        SELECT 'Producto Agregado con exito' AS 'Alerta';
    ELSE
        SELECT 'No puede agregar productos repetidos' AS "Alerta";
    END IF;
END //
DELIMITER ; 

-- 2) Desactivar PRODUCTO
DELIMITER //
CREATE PROCEDURE desactivar_producto(IN _id INT)
BEGIN
    DECLARE verificar_id_product INT;

    SET verificar_id_product = (SELECT COUNT(*) 
    FROM producto 
    WHERE id = _id);

    IF verificar_id_product = 1 THEN 
        UPDATE producto SET activo = 0 WHERE id = _id;
        SELECT 'Producto Desactivado' AS 'Alerta';
    ELSE
        SELECT 'Producto No encontrado' AS "Alerta";
    END IF;
END //
DELIMITER ;

-- 3) Activar PRODUCTO
DELIMITER //
CREATE PROCEDURE activar_producto(IN _id INT)
BEGIN
    DECLARE verificar_id_product INT;

    SET verificar_id_product = (SELECT COUNT(*) 
    FROM producto 
    WHERE id = _id);

    IF verificar_id_product = 1 THEN 
        UPDATE producto SET activo = 1 WHERE id = _id;
        SELECT 'Producto Activado' AS 'Alerta';
    ELSE
        SELECT 'Producto No encontrado' AS "Alerta";
    END IF;
END //
DELIMITER ;

-- 4) Cambiar contraseña
DELIMITER //
CREATE PROCEDURE cambiar_pass(IN _user VARCHAR(50),_pass VARCHAR(200),passnew VARCHAR(200))
BEGIN
    DECLARE verificar_credenciales INT;

    SET verificar_credenciales = (SELECT COUNT(*) 
    FROM trabajador 
    WHERE username = _user AND contraseña = SHA2(_pass,0));

    IF verificar_credenciales = 1 THEN 
        UPDATE trabajador SET contraseña = SHA2(passnew,0) WHERE username = _user;
        SELECT 'Su contraseña a sido cambiada' AS 'Alerta';
    ELSE
        SELECT 'El usuario o la contraseña son incorrectos' AS 'Alerta';
    END IF;
END //
DELIMITER ;


-- 5) Cambiar nombre de usuario
DELIMITER //
CREATE PROCEDURE cambiar_user(IN _user VARCHAR(50),_pass VARCHAR(200),usernew VARCHAR(200))
BEGIN
    DECLARE verificar_credenciales INT;

    SET verificar_credenciales = (SELECT COUNT(*) 
    FROM trabajador 
    WHERE username = _user AND contraseña = SHA2(_pass,0));

    IF verificar_credenciales = 1 THEN 
        UPDATE trabajador SET username = usernew WHERE username = _user;
        SELECT 'Su usuario a sido cambiado' AS 'Alerta';
    ELSE
        SELECT 'El usuario o la contraseña son incorrectos' AS 'Alerta';
    END IF;
END //
DELIMITER ;

-- 6) Ver total con el nombre de los productos y la fecha en la que se compraron, segun fecha ingresada 
DELIMITER //
CREATE PROCEDURE ver_pro_fecha_total(IN _desde DATETIME,_hasta DATETIME)
BEGIN
    DECLARE verificador_existe_fecha_desde INT;
    DECLARE verificador_existe_fecha_hasta INT;

    SET verificador_existe_fecha_desde = (SELECT COUNT(*)
    FROM detalle
    INNER JOIN factura ON factura.id = detalle.factura_id_fk
    WHERE factura.fecha >= _desde);

    SET verificador_existe_fecha_hasta = (SELECT COUNT(*)
    FROM detalle
    INNER JOIN factura ON factura.id = detalle.factura_id_fk
    WHERE factura.fecha <= _hasta);

    IF verificador_existe_fecha_desde >= 1 and verificador_existe_fecha_hasta >= 1 THEN 
        SELECT producto.nombre,factura.fecha, detalle.precio
        FROM detalle
        INNER JOIN factura on detalle.factura_id_fk = factura.id
        INNER JOIN producto on producto.id = detalle.producto_id_fk
        WHERE factura.fecha >= _desde AND factura.fecha <= _hasta
        UNION
        SELECT '','Total',SUM(detalle.precio)
        FROM detalle
        INNER JOIN factura on detalle.factura_id_fk = factura.id
        WHERE factura.fecha >= _desde AND factura.fecha <= _hasta;
    ELSE
        SELECT 'No Existe Ventas en la Fecha Indicada' AS 'Alerta';
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

-- INSERT
-- CLIENTE
INSERT INTO cliente VALUES(NULL,'20852522-2','Benito Martinez');
INSERT INTO cliente VALUES(NULL,'20213321-5','Roberto Dueñas');
INSERT INTO cliente VALUES(NULL,'20521984-1','Hugo Lloris');
INSERT INTO cliente VALUES(NULL,'19321333-2','Francisco Perez');
INSERT INTO cliente VALUES(NULL,'09232321-6','Esteban Jimenez');

-- Facturas
INSERT INTO factura VALUES (NULL,1,'2020-08-15 22:49:59');
INSERT INTO factura VALUES (NULL,3,'2020-08-15 21:32:30');
INSERT INTO factura VALUES (NULL,2,'2020-08-15 18:42:50');
INSERT INTO factura VALUES (NULL,2,'2020-08-02 13:20:20');
INSERT INTO factura VALUES (NULL,4,'2020-08-21 12:43:15');

INSERT INTO factura VALUES (NULL,5,'2020-09-19 14:20:39');
INSERT INTO factura VALUES (NULL,5,'2020-09-17 22:21:50');
INSERT INTO factura VALUES (NULL,1,'2020-09-18 11:12:30');
INSERT INTO factura VALUES (NULL,1,'2020-09-17 17:40:32');
INSERT INTO factura VALUES (NULL,3,'2020-09-18 13:33:35');

-- Productos
INSERT INTO producto VALUES (NULL,'Coca-Cola 3lt',2100,1);
INSERT INTO producto VALUES (NULL,'Sprite 3lt',2000,1);
INSERT INTO producto VALUES (NULL,'Pack Escudo-12 Latas',5900,1);
INSERT INTO producto VALUES (NULL,'Misiones de Rengo 1lt',2300,1);
INSERT INTO producto VALUES (NULL,'Alto del Carmen 1lt',2700,1);
INSERT INTO producto VALUES (NULL,'Corona 710ml',1100,1);
INSERT INTO producto VALUES (NULL,'Pack Cristal-12 Latas',6000,1);
INSERT INTO producto VALUES (NULL,'Dorada 1.2Lt',1400,1);
INSERT INTO producto VALUES (NULL,'Cerveza Mestra Stout 330ml',900,1);
INSERT INTO producto VALUES (NULL,'Cerveza Nomade IPA 333ml',600,1);
INSERT INTO producto VALUES (NULL,'Cerveza Cuello Negro Ámbar Botella 330ml',750,1);
INSERT INTO producto VALUES (NULL,'Cerveza Bundor Troll Oatmeal Stout botella 330ml',800,1);

-- Detalle                     Factura, Producto, Cantidad,              precio  
INSERT INTO detalle VALUES (NULL,     1,      1,        1,          (SELECT precio FROM producto WHERE id = 1) * 1);
INSERT INTO detalle VALUES (NULL,     1,      10,        3,          (SELECT precio FROM producto WHERE id = 10) * 3);
INSERT INTO detalle VALUES (NULL,     2,      9,        4,          (SELECT precio FROM producto WHERE id = 9) * 4);
INSERT INTO detalle VALUES (NULL,     3,      2,        1,          (SELECT precio FROM producto WHERE id = 2) * 1);
INSERT INTO detalle VALUES (NULL,     4,      2,        8,          (SELECT precio FROM producto WHERE id = 2) * 8);
INSERT INTO detalle VALUES (NULL,     4,      5,        3,          (SELECT precio FROM producto WHERE id = 5) * 3);
INSERT INTO detalle VALUES (NULL,     5,      5,        2,          (SELECT precio FROM producto WHERE id = 5) * 2);
INSERT INTO detalle VALUES (NULL,     6,      6,        1,          (SELECT precio FROM producto WHERE id = 6) * 1);
INSERT INTO detalle VALUES (NULL,     7,      1,        5,          (SELECT precio FROM producto WHERE id = 1) * 5);
INSERT INTO detalle VALUES (NULL,     8,      10,        8,          (SELECT precio FROM producto WHERE id = 10) * 8);
INSERT INTO detalle VALUES (NULL,     9,      11,        9,          (SELECT precio FROM producto WHERE id = 11) * 9);
INSERT INTO detalle VALUES (NULL,     10,      4,        10,          (SELECT precio FROM producto WHERE id = 4) * 10);




