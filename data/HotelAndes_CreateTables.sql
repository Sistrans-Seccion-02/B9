create sequence hotelandes_sequence;

-- Tabla ServicioEntretenimiento
CREATE TABLE SERVICIOSENTRENIMIENTO
   (ID NUMBER, 
	HORARIOSERVICIO VARCHAR2(255 BYTE), 
    CAPACIDAD NUMBER,
	CONSTRAINT SERVICIOSENTRENIMIENTO_PK PRIMARY KEY (ID));
	

-- Tabla ServicioConsumo
CREATE TABLE SERVICIOSCONSUMO
   (ID NUMBER, 
	NOMBRE VARCHAR2(20 BYTE), 
	CAPACIDAD NUMBER, 
	CONSUMO VARCHAR(250 BYTE), 
    REGISTROCONSUMO VARCHAR(250 BYTE), 
	CONSTRAINT SERVICIOSCONSUMO_PK PRIMARY KEY (ID));


-- Tabla Producto
CREATE TABLE PRODUCTOS
   (ID NUMBER, 
	NOMBRE VARCHAR2(255 BYTE), 
	CONSTRAINT PRODUCTOS_PK PRIMARY KEY (ID));
	 

-- Tabla Servicio Compras
--
--
--      Pendiente
--
--
--


-- Tabla Usuario
CREATE TABLE USUARIOS
(
  ID NUMBER,
  NOMBRE VARCHAR2(255 BYTE),
  DOCUMENTO VARCHAR2(255 BYTE),
  TIPODOCUMENTO VARCHAR2(255 BYTE),
  ROL VARCHAR2(255 BYTE),
  CORREO VARCHAR2(255 BYTE),
  CONTRASENA VARCHAR2(255 BYTE),
  CONSTRAINT USUARIOS_PK PRIMARY KEY (ID));



-- Tabla Hotel
CREATE TABLE HOTELES
(
  ID NUMBER,
  NOMBRE VARCHAR2(255 BYTE),
  TIPO VARCHAR2(255 BYTE), 
  ESTRELLAS NUMBER, 
  CATEGORIA VARCHAR2(255 BYTE), 
  CIUDAD VARCHAR2(255 BYTE), 
  CONSTRAINT HOTELES_PK PRIMARY KEY (ID));



-- Tabla Plan
CREATE TABLE PLANES 
(
  ID NUMBER,
  TIPO VARCHAR2(255 BYTE),
  INICIO VARCHAR2(255 BYTE), 
  FINALD VARCHAR2(255 BYTE), 
  CONSTRAINT PLANES_PK PRIMARY KEY (ID));




-- Tabla Reserva
CREATE TABLE RESERVAS
(
  ID NUMBER,
  FECHA DATE,
  FECHAFINAL DATE, 
  PERSONAS NUMBER, 
  CONSTRAINT RESERVAS_PK PRIMARY KEY (ID));


-- Tabla Habitacion
CREATE TABLE HABITACIONES 
(
  ID NUMBER,
  CAPACIDAD NUMBER,
  TIPO VARCHAR2(255 BYTE), 
  DOTACION VARCHAR2(255 BYTE), 
  PRECIONOCHE NUMBER,
  CONSUMOEXTRA NUMBER,
  CONSTRAINT HABITACIONES_PK PRIMARY KEY (ID));
  
  
  
-- Tabla Lavanderia
CREATE TABLE LAVANDERIAS 
(
  ID NUMBER,
  TIPOPRENDA VARCHAR2(255 BYTE),
  NUMEROPRENDAS NUMBER, 
  PARESZAPATOS NUMBER, 
  COSTO NUMBER,
  CONSTRAINT LAVANDERIAS_PK PRIMARY KEY (ID));
  
  
  
-- Tabla Salon
CREATE TABLE SALONES 
(
  ID NUMBER,
  CAPACIDAD NUMBER,
  TIPO VARCHAR2(255 BYTE), 
  COSTOHORA NUMBER, 
  LIMPIEZA NUMBER,
  EQUIPOS BOOLEAN,
  CONSTRAINT SALONES_PK PRIMARY KEY (ID));
  
  
  
-- Tabla Utileria
CREATE TABLE UTILERIA 
(
  ID NUMBER,
  NOMBRE VARCHAR2(255 BYTE),
  COSTOPENALIZACION NUMBER, 
  CONSTRAINT UTILERIA_PK PRIMARY KEY (ID));
  
  
  
-- Tabla Factura
CREATE TABLE FACTURAS
(
  ID NUMBER,
  precioTotal NUMBER, 
  CONSTRAINT FACTURAS_PK PRIMARY KEY (ID));
  
  
  
-- Tabla ReservaSalon
CREATE TABLE RESERVASALON
(
  ID NUMBER,
  DIA VARCHAR2(255 BYTE),
  HORA VARCHAR2(255 BYTE),
  DURACION VARCHAR2(255 BYTE),
  CONSTRAINT RESERVASALON_PK PRIMARY KEY (ID));
  


  
COMMIT;
