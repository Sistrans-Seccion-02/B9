package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.ServicioReservas;

import java.sql.Date;
import java.util.Collection;

public interface ServicioReservasRepo extends JpaRepository<ServicioReservas, Integer> {

    @Query(value = "SELECT * FROM servicioreservas", nativeQuery = true)
    Collection<ServicioReservas> darReservas();

    @Query(value = "SELECT * FROM servicioreservas WHERE id = :id", nativeQuery = true)
    ServicioReservas darReserva(@Param("id") long id);

    public interface respuestaFrecuenciaServicios{
        String getSERVICIO();
        Integer getFRECUENCIA_TOTAL();
    }
    @Query(value = "SELECT S.TIPO AS SERVICIO, "+//
                   "COUNT(*) AS FRECUENCIA_TOTAL "+//
                   "FROM SERVICIORESERVAS S "+//
                   "WHERE FECHAINICIAL >= ADD_MONTHS(SYSDATE, -12) "+//
                   "GROUP BY S.TIPO "+//
                   "HAVING COUNT(*) < 3", nativeQuery = true)
    Collection<respuestaFrecuenciaServicios> frecuenciaServicios();


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicioreservas WHERE id = :id", nativeQuery = true)
    void eliminarReserva(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE servicioreservas SET fechainicial = :fechainicial, fechafinal = :fechafinal, idhabitacion = :idhabitacion, tipo = :tipo, precio = :precio WHERE id = :id", nativeQuery = true)
    void actualizarReservas(@Param("id") long id, 
    @Param("fechainicial") Date fechainicial, 
    @Param("fechafinal") Date fechafinal,
    @Param("idhabitacion") Integer idhabitacion,
    @Param("tipo") String tipo,
    @Param("precio") Double precio
    );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicioreservas (id, fechainicial, fechafinal, idhabitacion, tipo) VALUES ( hotelandes_sequence.nextval , :fechainicial, :fechafinal, :idhabitacion, :tipo, :precio)", nativeQuery = true)
    void insertarReservas(@Param("fechainicial") Date fechainicial, 
                @Param("fechafinal") Date fechafinal,
                @Param("idhabitacion") Integer idhabitacion,
                @Param("tipo") String tipo,
                @Param("precio") Double precio
    );

    public interface RespuestaDineroRecolectadoPorServicio {
        Long getid_habitacion();
        Double getdinero_recolectado();
    }

    @Query(value =
               "SELECT " +
                    "h.id AS id_habitacion, " +
                    "SUM(CASE " +
                         "WHEN sr.precio IS NOT NULL THEN sr.precio " +
                         "ELSE 0 " +
                    "END) AS dinero_recolectado " +
               "FROM HABITACIONES h " +
               "LEFT JOIN SERVICIORESERVAS sr ON h.id = sr.idhabitacion " +
               "WHERE sr.fechainicial BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE " +
               "GROUP BY h.id", nativeQuery = true)
               Collection<RespuestaDineroRecolectadoPorServicio> obtenerDineroRecolectadoPorDiferentesServicios();
                
               public interface RespuestaServiciosPopulares {
                String getSERVICIO();
                Long getFRECUENCIA_TOTAL();
            }
        
            @Query(value =
                "SELECT UPPER(S.TIPO) AS SERVICIO, " +
                "COUNT(*) AS FRECUENCIA_TOTAL " +
                "FROM SERVICIORESERVAS S " +
                "WHERE S.FECHAINICIAL BETWEEN :fechaInicio AND :fechaFin " +
                "GROUP BY UPPER(S.TIPO) " +
                "ORDER BY FRECUENCIA_TOTAL DESC " +
                "FETCH FIRST 20 ROWS ONLY",
                nativeQuery = true)
            Collection<RespuestaServiciosPopulares> serviciosPopulares(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

            public interface RespuestaServiciosConCaracteristicas {
                Long getservicio_id();
                Date getfecha_inicial();
                Date getfecha_final();
                Long getid_habitacion();
                String gettipo_servicio();
                Double getprecio_servicio();

            }
            
            @Query(value = 
            "SELECT S.id as servicio_id, " +
            "S.fechainicial as fecha_inicial, " +
            "S.fechafinal as fecha_final, " +
            "S.idhabitacion as id_habitacion, " +
            "S.tipo as tipo_servicio, " +
            "S.precio as precio_servicio " +
            "FROM SERVICIORESERVAS S " +
            "WHERE (:fechaInicio IS NULL OR S.FECHAINICIAL BETWEEN :fechaInicio AND :fechaFin) " +
            "AND (:rangoPrecioMin IS NULL OR S.PRECIO >= :rangoPrecioMin) " +
            "AND (:rangoPrecioMax IS NULL OR S.PRECIO <= :rangoPrecioMax) " +
            "AND (:tipoServicio IS NULL OR UPPER(S.TIPO) = UPPER(:tipoServicio))",
            nativeQuery = true)
        Collection<RespuestaServiciosConCaracteristicas> serviciosConCaracteristicas(
            @Param("fechaInicio") Date fechaInicio, 
            @Param("fechaFin") Date fechaFin,
            @Param("rangoPrecioMin") Double rangoPrecioMin, 
            @Param("rangoPrecioMax") Double rangoPrecioMax,
            @Param("tipoServicio") String tipoServicio
        );
        
            
}