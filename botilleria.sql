CREATE DATABASE botilleria;

USE botilleria;

-- TABLAS

CREATE TABLE trabajador (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50),
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



INSERT INTO trabajador VALUES(NULL,'nico', SHA2('hola',0));
INSERT INTO trabajador VALUES(NULL,'carlos', SHA2('perrito',0));



INSERT INTO cliente VALUES(NULL,'20213','maikol');

INSERT INTO producto VALUES (NULL,'Coca-cola',1000), -- 1
                            (NULL,'Queso 3/8 kg',800), -- 2
                            (NULL,'Katana Fulltang T-10 BattleReady',800), -- 3
                            (NULL,'Sprite',500); -- 4

INSERT INTO factura VALUES (NULL,1,NOW(),1); -- 1    
INSERT INTO factura VALUES (NULL,1,NOW(),2); -- 1     



INSERT INTO detalle VALUES (NULL,     1,      1,        5,          (SELECT precio FROM producto WHERE id = 1) * 5); -- COLA COLA -> X5
INSERT INTO detalle VALUES (NULL,     1,      2,        1,          (SELECT precio FROM producto WHERE id = 2) * 1);

INSERT INTO detalle VALUES (NULL,     2,      1,        4,          (SELECT precio FROM producto WHERE id = 1) * 4); -- COLA COLA -> X5
INSERT INTO detalle VALUES (NULL,     2,      2,        4,          (SELECT precio FROM producto WHERE id = 2) * 4);

SELECT trabajador.nombre, SUM(detalle.precio)
FROM detalle
INNER JOIN trabajador ON trabajador.id = factura.factura_id_fk;

SELECT trabajador.nombre, SUM(detalle.precio)
FROM producto
INNER JOIN detalle ON producto.id = detalle.producto_id_fk
INNER JOIN factura ON factura.id = detalle.factura_id_fk
INNER JOIN trabajador ON trabajador.id = factura.trabajador_id_fk
GROUP BY trabajador.nombre;