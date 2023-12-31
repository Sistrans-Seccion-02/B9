package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Reserva;

import java.sql.Date;
import java.util.Collection;

public interface ReservaRepo extends JpaRepository<Reserva, Integer> {

    @Query(value = "SELECT * FROM reservas", nativeQuery = true)
    Collection<Reserva> darReservas();

    @Query(value = "SELECT * FROM reservas WHERE id = :id", nativeQuery = true)
    Reserva darReserva(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservas WHERE id = :id", nativeQuery = true)
    void eliminarReserva(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservas SET fecha = :fecha, fechaFinal = :fechaFinal, personas = :personas, habtacionid = :habtacionid, planesid = :planesid, usuarioid = :usuarioid WHERE id = :id", nativeQuery = true)
    void actualizarReserva(@Param("id") long id, @Param("fecha") Date fecha, @Param("fechaFinal") Date fechaFinal,
            @Param("personas") Integer personas, @Param("habtacionid") Integer habitacionid, @Param("planesid") Integer planesid,
            @Param("usuarioid") Integer usuarioid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservas (id, fecha, fechaFinal, personas, habtacionid, planesid, usuarioid) VALUES ( hotelandes_sequence.nextval , :fecha, :fechaFinal, :personas, :habtacionid, :planesid, :usuarioid)", nativeQuery = true)
    void insertarReserva(@Param("fecha") Date fecha, @Param("fechaFinal") Date fechaFinal,
            @Param("personas") Integer personas, @Param("habtacionid") Integer habitacionid, @Param("planesid") Integer planesid, @Param("usuarioid") Integer usuarioid);

        
        public interface IndiceOcupacionPorHabitacion {
        Long getnumeroHabitacion();
        Double getporcentajeOcupacion();
        }   
        @Query(value =
        "SELECT r.habtacionid AS numeroHabitacion, " +
        "ROUND(SUM(MONTHS_BETWEEN(r.fechafinal, r.fecha) / 12.0) * 100, 2) AS porcentajeOcupacion " +
        "FROM reservas r " +
        "WHERE r.fecha BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE " +
        "GROUP BY r.habtacionid",
        nativeQuery = true)
    Collection<IndiceOcupacionPorHabitacion> calcularIndiceOcupacionPorHabitacion();
    
        

}