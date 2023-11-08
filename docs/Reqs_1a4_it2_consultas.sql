-- RFC1 - Mostrar el dinero recolectado por servicios
--  en cada habitación en el último año corrido.  

SELECT
                    h.id AS id_habitacion,
                    SUM(CASE 
                         WHEN sr.precio IS NOT NULL THEN sr.precio
                         ELSE 0
                    END) AS dinero_recolectado
               FROM HABITACIONES h
               LEFT JOIN SERVICIORESERVAS sr ON h.id = sr.idhabitacion
               WHERE sr.fechainicial BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE 
               GROUP BY h.id;


-- RFC2 - Mostrar los 20 servicios más populares.  

SELECT UPPER(S.TIPO) AS SERVICIO, 
                COUNT(*) AS FRECUENCIA_TOTAL
                FROM SERVICIORESERVAS S
                WHERE S.FECHAINICIAL BETWEEN :fechaInicio AND :fechaFin
                GROUP BY UPPER(S.TIPO) 
                ORDER BY FRECUENCIA_TOTAL DESC 
                FETCH FIRST 20 ROWS ONLY;


-- RFC3 - Mostrar el índice de ocupación de cada una de las habitaciones del hotel


SELECT r.habtacionid AS numeroHabitacion, 
        ROUND(SUM(MONTHS_BETWEEN(r.fechafinal, r.fecha) / 12.0) * 100, 2) AS porcentajeOcupacion 
        FROM reservas r 
        WHERE r.fecha BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
        GROUP BY r.habtacionid;

-- RFC4 - Mostrar los servicios que cumplen con cierta característica

SELECT S.id as servicio_id,
            S.fechainicial as fecha_inicial, 
            S.fechafinal as fecha_final, 
            S.idhabitacion as id_habitacion, 
            S.tipo as tipo_servicio, 
            S.precio as precio_servicio 
            FROM SERVICIORESERVAS S 
            WHERE (:fechaInicio IS NULL OR S.FECHAINICIAL BETWEEN :fechaInicio AND :fechaFin)
            AND (:rangoPrecioMin IS NULL OR S.PRECIO >= :rangoPrecioMin)
            AND (:rangoPrecioMax IS NULL OR S.PRECIO <= :rangoPrecioMax)
            AND (:tipoServicio IS NULL OR UPPER(S.TIPO) = UPPER(:tipoServicio));