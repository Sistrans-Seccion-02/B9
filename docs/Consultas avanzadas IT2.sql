SELECT U.ID, U.NOMBRE, U.DOCUMENTO, U.TIPODOCUMENTO, U.CORREO, S.DESCRIPCION, S.COSTO, S.FECHA, S.IDHABITACION, S.IDPRODUCTO
FROM USUARIOS U
    JOIN HABITACIONES H ON U.ID = H.USUARIOID
    JOIN SERVICIOSCONSUMO S ON H.ID = S.IDHABITACION
WHERE U.ROL = 'Cliente' AND  S.FECHA BETWEEN TO_DATE('19/04/2023', 'DD-MM-YYYY')
                                        AND TO_DATE('02/11/2023', 'DD-MM-YYYY');
                                        
select * from usuarios;
select * from habitaciones;
select * from serviciosconsumo;

insert into habitaciones values(100, 1, 'SUITE', 'NADA', 200000, 0, 1, null, 3, null);

update usuarios set rol = 'Cliente' where id = 2;
commit;


SELECT U.ID, U.NOMBRE, U.DOCUMENTO, U.TIPODOCUMENTO, U.CORREO
FROM USUARIOS U
JOIN HABITACIONES H ON U.ID = H.USUARIOID
LEFT JOIN SERVICIOSCONSUMO S ON H.ID = S.IDHABITACION
    AND S.FECHA BETWEEN TO_DATE('19/04/2023', 'DD-MM-YYYY') AND TO_DATE('02/11/2023', 'DD-MM-YYYY')
WHERE U.ROL = 'Cliente' 
HAVING COUNT((U.ID)) = 1
GROUP BY U.ID, U.NOMBRE, U.DOCUMENTO, U.TIPODOCUMENTO, U.CORREO;

-------------------------------------------------------------------------------------------------------------


WITH WeekNumbers AS (
    SELECT LEVEL AS WeekNumber
    FROM DUAL
    CONNECT BY LEVEL <= 52)
, WeekDates AS (
    SELECT
        TO_DATE('01-01-2023', 'DD-MM-YYYY') + (WeekNumber - 1) * 7 AS StartDate,
        TO_DATE('01-01-2023', 'DD-MM-YYYY') + (WeekNumber - 1) * 7 + 6 AS EndDate
    FROM WeekNumbers)
SELECT
    TO_CHAR(StartDate, 'DD-MM-YYYY') AS Inicio,
    TO_CHAR(EndDate, 'DD-MM-YYYY') AS Fin,
    (SELECT Descripcion FROM (
        SELECT s.Descripcion, COUNT(*) AS ConsumptionCount
        FROM SERVICIOSCONSUMO s
        WHERE s.FECHA BETWEEN StartDate AND EndDate
        GROUP BY s.Descripcion
        ORDER BY COUNT(*) DESC
    ) WHERE ROWNUM = 1) AS servicioMasConsumido,
    (SELECT Descripcion FROM (
        SELECT s.Descripcion, COUNT(*) AS ConsumptionCount
        FROM SERVICIOSCONSUMO s
        WHERE s.FECHA BETWEEN StartDate AND EndDate
        GROUP BY s.Descripcion
        ORDER BY COUNT(*) ASC
    ) WHERE ROWNUM = 1) AS servicioMenosConsumido,
    (SELECT HabtacionID FROM (
        SELECT r.HabtacionID, COUNT(*) AS ReservationCount
        FROM RESERVAS r
        WHERE r.FECHA BETWEEN StartDate AND EndDate
        GROUP BY r.HabtacionID
        ORDER BY COUNT(*) DESC
    ) WHERE ROWNUM = 1) AS habitacionMasSolicitada,
    (SELECT HabtacionID FROM (
        SELECT r.HabtacionID, COUNT(*) AS ReservationCount
        FROM RESERVAS r
        WHERE r.FECHA BETWEEN StartDate AND EndDate
        GROUP BY r.HabtacionID
        ORDER BY COUNT(*) ASC
    ) WHERE ROWNUM = 1) AS habitacionMenosSolicitada
FROM WeekDates;

---------------------------------------------------------------------

SELECT 
    U.ID AS ID,
    U.NOMBRE AS NOMBRE,
    U.CORREO AS CORREO,
    U.DOCUMENTO AS DOCUMENTO,
    CASE 
        WHEN EXISTS (
            SELECT 1 
            FROM RESERVAS R
            WHERE R.USUARIOID = U.ID
            AND R.FECHA BETWEEN TRUNC(SYSDATE, 'Q') - INTERVAL '3' MONTH AND SYSDATE
        ) THEN 'Cliente de Estancias Trimestrales'
        WHEN EXISTS (
            SELECT 1
            FROM SERVICIOSCONSUMO SC
            JOIN HABITACIONES ON HABITACIONES.USUARIOID = U.ID
            WHERE HABITACIONES.USUARIOID = U.ID
            AND SC.COSTO > 300000
        ) THEN 'Cliente de Servicio Costoso'
    END AS TipoClienteExcelente
FROM USUARIOS U;

