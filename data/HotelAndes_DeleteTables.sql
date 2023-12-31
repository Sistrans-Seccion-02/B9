-- Eliminar todas las tablas de la base de datos
-- Eliminar todas las tablas de la base de datos
DROP TABLE SERVICIOSCONSUMO CASCADE CONSTRAINTS;
DROP TABLE PRODUCTOS CASCADE CONSTRAINTS;
DROP TABLE USUARIOS CASCADE CONSTRAINTS;
DROP TABLE HOTELES CASCADE CONSTRAINTS;
DROP TABLE PLANES CASCADE CONSTRAINTS;
DROP TABLE RESERVAS CASCADE CONSTRAINTS;
DROP TABLE HABITACIONES CASCADE CONSTRAINTS;
DROP TABLE LAVANDERIAS CASCADE CONSTRAINTS;
DROP TABLE SALONES CASCADE CONSTRAINTS;
DROP TABLE UTILERIA CASCADE CONSTRAINTS;
DROP TABLE FACTURAS CASCADE CONSTRAINTS;
DROP TABLE REGISTROS CASCADE CONSTRAINTS;
DROP TABLE PISCINA CASCADE CONSTRAINTS;
DROP TABLE GIMNASIO CASCADE CONSTRAINTS;
DROP TABLE INTERNET CASCADE CONSTRAINTS;
DROP TABLE BARES CASCADE CONSTRAINTS;
DROP TABLE RESTAURANTES CASCADE CONSTRAINTS;
DROP TABLE SUPERMERCADOS CASCADE CONSTRAINTS;
DROP TABLE TIENDAS CASCADE CONSTRAINTS;
DROP TABLE SERVICIORESERVAS CASCADE CONSTRAINTS;
DROP TABLE SPAS CASCADE CONSTRAINTS;

COMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos

-- Elimina los datos de las tablas
DELETE FROM SERVICIORESERVAS;
DELETE FROM SPAS;
DELETE FROM PRODUCTOS;
DELETE FROM USUARIOS;
DELETE FROM HOTELES;
DELETE FROM PLANES;
DELETE FROM RESERVAS;
DELETE FROM HABITACIONES;
DELETE FROM LAVANDERIAS;
DELETE FROM SALONES;
DELETE FROM UTILERIA;
DELETE FROM FACTURAS;
DELETE FROM REGISTROS;
DELETE FROM SERVICIOSCONSUMO;
DELETE FROM PISCINA;
DELETE FROM INTERNET;
DELETE FROM GIMNASIO;
DELETE FROM BARES;
DELETE FROM RESTAURANTES;
DELETE FROM SUPERMERCADOS;
DELETE FROM TIENDAS;

