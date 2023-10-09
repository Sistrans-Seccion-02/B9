create sequence hotelandes_sequence;

CREATE TABLE SERVICIORESERVAS 
(
  ID NUMBER,
  FECHAINICIAL DATE,
  FECHAFINAL DATE,
  IDHABITACION NUMBER, 
  IDSPA NUMBER,
  IDSALON NUMBER,
  IDLAVANDERIA NUMBER,
  CONSTRAINT SERVICIORESERVAS_PK PRIMARY KEY (ID));

  ALTER TABLE SERVICIORESERVAS
ADD CONSTRAINT fk_a_id_habitacion
    FOREIGN KEY (IDHABITACION)
    REFERENCES HABITACIONES(ID)
    ON DELETE CASCADE
ENABLE;

  ALTER TABLE SERVICIORESERVAS
ADD CONSTRAINT fk_a_id_spa
    FOREIGN KEY (IDSPA)
    REFERENCES SPA(ID)
    ON DELETE CASCADE
ENABLE;

  ALTER TABLE SERVICIORESERVAS
ADD CONSTRAINT fk_a_id_salon
    FOREIGN KEY (IDSALON)
    REFERENCES SALONES(ID)
    ON DELETE CASCADE
ENABLE;

  ALTER TABLE SERVICIORESERVAS
ADD CONSTRAINT fk_a_id_lavanderia
    FOREIGN KEY (IDLAVANDERIA)
    REFERENCES LAVANDERIAS(ID)
    ON DELETE CASCADE
ENABLE;

--Tabla SPA
CREATE TABLE SPA
(ID NUMBER,
DURACION NUMBER
COSTO NUMBER
CAPACIDAD NUMBER
DISPONIBILIDAD VARCHAR2(255 BYTE),
CONSTRAINT SPA_PK PRIMARY KEY (ID));

ALTER TABLE SPA
	ADD CONSTRAINT CK_DISPONIBILIDAD_SPA 
	CHECK (DISPONIBILIDAD IN ('si','no'))
ENABLE;


-- Tabla Producto
CREATE TABLE PRODUCTOS
   (ID NUMBER, 
	NOMBRE VARCHAR2(255 BYTE),
  COSTO NUMBER,
  RESTAURANTEID NUMBER,
  TIENDAID NUMBER,
  SUPERMERCADOID NUMBER,
  BARID NUMBER,
  SERVICIOCONSUMOID NUMBER, 
	CONSTRAINT PRODUCTOS_PK PRIMARY KEY (ID));
	 
ALTER TABLE PRODUCTOS
ADD CONSTRAINT fk_a_restaurante_id
    FOREIGN KEY (RESTAURANTEID)
    REFERENCES RESTAURANTES(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE PRODUCTOS
ADD CONSTRAINT fk_a_tienda_id
    FOREIGN KEY (TIENDAID)
    REFERENCES TIENDAS(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE PRODUCTOS
ADD CONSTRAINT fk_a_supermercado_id
    FOREIGN KEY (SUPERMERCADOID)
    REFERENCES SUPERMERCADOS(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE PRODUCTOS
ADD CONSTRAINT fk_a_bar_id
    FOREIGN KEY (BARID)
    REFERENCES BARES(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE PRODUCTOS
ADD CONSTRAINT fk_a_servicio_consumo_id
    FOREIGN KEY (SERVICIOCONSUMOID)
    REFERENCES SERVICIOSCONSUMO(ID)
    ON DELETE CASCADE
ENABLE;

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
  HOTELID NUMBER,
  REGISTROID NUMBER,
  RESERVAID NUMBER,
  CONSTRAINT USUARIOS_PK PRIMARY KEY (ID));

ALTER TABLE USUARIOS
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY (HOTELID)
    REFERENCES HOTELES(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE PRODUCTOS
ADD CONSTRAINT fk_a_registro_id
    FOREIGN KEY (REGISTROID)
    REFERENCES REGISTROS(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE PRODUCTOS
ADD CONSTRAINT fk_a_reserva_id
    FOREIGN KEY (RESERVAID)
    REFERENCES RESERVAS(ID)
    ON DELETE CASCADE
ENABLE;


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
  HORARIO DATE,
  DESCRIPCION VARCHAR2(255 BYTE),
  HOTELID NUMBER,
  CONSTRAINT PLANES_PK PRIMARY KEY (ID));

ALTER TABLE PLANES
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY (HOTELID)
    REFERENCES HOTELES(ID)
    ON DELETE CASCADE
ENABLE;


-- Tabla Reserva
CREATE TABLE RESERVAS
(
  ID NUMBER,
  FECHA DATE,
  FECHAFINAL DATE, 
  PERSONAS NUMBER,
  HABITACIONID NUMBER,
  PLANESID NUMBER,
  USUARIOID NUMBER,
  CONSTRAINT RESERVAS_PK PRIMARY KEY (ID));

ALTER TABLE RESERVAS
ADD CONSTRAINT fk_a_habitacion_id
    FOREIGN KEY (HABITACIONID)
    REFERENCES HABITACIONES(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT fk_a_planes_id
    FOREIGN KEY (PLANESID)
    REFERENCES PLANES(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT fk_a_usuario_id
    FOREIGN KEY (USUARIOID)
    REFERENCES USUARIOS(ID)
    ON DELETE CASCADE
ENABLE;

-- Tabla Habitacion
CREATE TABLE HABITACIONES 
(
  ID NUMBER,
  CAPACIDAD NUMBER,
  TIPO VARCHAR2(255 BYTE), 
  DOTACION VARCHAR2(255 BYTE), 
  PRECIONOCHE NUMBER,
  CONSUMOEXTRA NUMBER,
  HOTELID NUMBER,
  RESERVAID NUMBER,
  USUARIOID NUMBER,
  SERVICIOCONSUMOID NUMBER,
  CONSTRAINT HABITACIONES_PK PRIMARY KEY (ID));

ALTER TABLE HABITACIONES
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY (HOTELID)
    REFERENCES HOTELES(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE HABITACIONES
ADD CONSTRAINT fk_a_reserva_id
    FOREIGN KEY (RESERVAID)
    REFERENCES RESERVAS(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE HABITACIONES
ADD CONSTRAINT fk_a_usuario_id
    FOREIGN KEY (USUARIOID)
    REFERENCES USUARIOS(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE HABITACIONES
ADD CONSTRAINT fk_a_servicio_consumo_id
    FOREIGN KEY (SERVICIOCONSUMOID)
    REFERENCES SERVICIOSCONSUMO(ID)
    ON DELETE CASCADE
ENABLE;
  
  
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
  DISPONIBILDAD VARCHAR2(255 BYTE),
  CONSTRAINT SALONES_PK PRIMARY KEY (ID));

ALTER TABLE SALONES
ADD CONSTARINT CK_DISPONIBILIDAD_SALONES
 CHECK DISPONIBILDAD IN("si","no")
 ENABLE;
  
-- Tabla Utileria
CREATE TABLE UTILERIA 
(
  ID NUMBER,
  NOMBRE VARCHAR2(255 BYTE),
  COSTOPENALIZACION NUMBER,
  HABITACIONID NUMBER,
  CONSTRAINT UTILERIA_PK PRIMARY KEY (ID));

  ALTER TABLE UTILERIA
ADD CONSTRAINT fk_a_habitacion_id
    FOREIGN KEY (HABITACIONID)
    REFERENCES HABITACIONES(ID)
    ON DELETE CASCADE
ENABLE;
  
  
  
  
-- Tabla Factura
CREATE TABLE FACTURAS
(
  ID NUMBER,
  precioTotal NUMBER, 
  RESERVASID NUMBER,
  CONSTRAINT FACTURAS_PK PRIMARY KEY (ID));
  
ALTER TABLE FACTURAS
ADD CONSTRAINT fk_a_reservas_id
    FOREIGN KEY (RESERVASID)
    REFERENCES RESERVAS(ID)
ENABLE;
  
  CREATE TABLE REGISTROS 
(
  ID NUMBER,
  LLEGADA DATE,
  SALIDA DATE,
  IDRESERVA NUMBER, 
  IDUSUARIO NUMBER,
  CONSTRAINT REGISTROS_PK PRIMARY KEY (ID));

  ALTER TABLE REGISTROS
ADD CONSTRAINT fk_a_reserva_id
    FOREIGN KEY (IDRESERVA)
    REFERENCES RESERVAS(ID)
ENABLE;

ALTER TABLE REGISTROS
ADD CONSTRAINT fk_a_usuario_id
    FOREIGN KEY (IDUSUARIO)
    REFERENCES USUARIOS(ID)
ENABLE;
  
  
CREATE TABLE SERVICIOSCONSUMO 
(
  ID NUMBER,
  DESCRIPCION VARCHAR2(255 BYTE),
  COSTO NUMBER,
  FECHA DATE, 
  IDHABITACION NUMBER,
  IDPRODUCTO NUMBER,
  CONSTRAINT SERVICIOSCONSUMO_PK PRIMARY KEY (ID));
  
ALTER TABLE SERVICIOSCONSUMO
ADD CONSTRAINT fk_a_habitacion_id
    FOREIGN KEY (IDHABITACION)
    REFERENCES HABITACIONES(ID)
    ON DELETE CASCADE
ENABLE;

ALTER TABLE SERVICIOSCONSUMO
ADD CONSTRAINT fk_a_habitacion_id
    FOREIGN KEY (IDPRODUCTO)
    REFERENCES PRODUCTOS(ID)
    ON DELETE CASCADE
ENABLE;

CREATE TABLE PISCINA
( 
  ID NUMBER,
  PROFUNDIDAD NUMBER,
  COSTO NUMBER,
  CAPACIDAD NUMBER,
  DISPONIBILDAD VARCHAR2(255),
  HOTELID NUMBER,
  CONSTRAINT PISCINA_PK PRIMARY KEY (ID));

ALTER TABLE PISCINA
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY HOTELID
    REFERENCES HOTELES(ID)
ENABLE;

ALTER TABLE PISCINA
ADD CONSTRAINT CK_DISPONIBILIDAD_PISCINA
    CHECK DISPONIBILDAD IN ("si", "no")

CREATE TABLE INTERNET
( 
  ID NUMBER,
  CAPACIDAD NUMBER,
  COSTODIARIO NUMBER,
  HOTELID NUMBER,
  CONSTRAINT INTERNET_PK PRIMARY KEY (ID));

ALTER TABLE INTERNET
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY HOTELID
    REFERENCES HOTELES(ID)
ENABLE;

CREATE TABLE GIMNASIO
( 
  ID NUMBER,
  MAQUINAS NUMBER,
  HORARIO NUMBER,
  CAPACIDAD NUMBER,
  HOTELID NUMBER,
  CONSTRAINT GIMNASIO_PK PRIMARY KEY (ID));

ALTER TABLE GIMNASIO
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY HOTELID
    REFERENCES HOTELES(ID)
ENABLE;

CREATE TABLE BARES
( 
  ID NUMBER,
  CAPACIDAD NUMBER,
  ESTILO VARCHAR2(255),
  NOMBRE VARCHAR2(255),
  HOTELID NUMBER,
  CONSTRAINT BAR_PK PRIMARY KEY (ID));

ALTER TABLE BARES
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY HOTELID
    REFERENCES HOTELES(ID)
ENABLE;  

CREATE TABLE RESTAURANTES
( 
  ID NUMBER,
  CAPACIDAD NUMBER,
  ESTILO VARCHAR2(255),
  NOMBRE VARCHAR2(255),
  HOTELID NUMBER,
  CONSTRAINT RESTAURANTE_PK PRIMARY KEY (ID));

ALTER TABLE RESTAURANTES
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY HOTELID
    REFERENCES HOTELES(ID)
ENABLE;

CREATE TABLE SUPERMERCADOS
( 
  ID NUMBER,
  NOMBRE VARCHAR2(255),
  HOTELID NUMBER,
  CONSTRAINT SUPERMERCADO_PK PRIMARY KEY (ID));

ALTER TABLE SUPERMERCADOS
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY HOTELID
    REFERENCES HOTELES(ID)
ENABLE; 

CREATE TABLE TIENDAS
( 
  ID NUMBER,
  NOMBRE VARCHAR2(255),
  HOTELID NUMBER,
  CONSTRAINT TIENDAS_PK PRIMARY KEY (ID));

ALTER TABLE TIENDAS
ADD CONSTRAINT fk_a_hotel_id
    FOREIGN KEY HOTELID
    REFERENCES HOTELES(ID)
ENABLE; 
COMMIT;
