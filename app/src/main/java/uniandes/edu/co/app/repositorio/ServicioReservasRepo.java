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

}