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

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicioreservas WHERE id = :id", nativeQuery = true)
    void eliminarReserva(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE servicioreservas SET fechainicial = :fechainicial, fechafinal = :fechafinal, idhabitacion = :idhabitacion, idspa = :idspa, idsalon = :idsalon, idlavanderia = :idlavanderia WHERE id = :id", nativeQuery = true)
    void actualizarReservas(@Param("id") long id, 
    @Param("fechainicial") Date fechainicial, 
    @Param("fechafinal") Date fechafinal,
    @Param("idhabitacion") Integer idhabitacion,
    @Param("idspa") Integer idspa,
    @Param("idsalon") Integer idsalon,
    @Param("idlavanderia") Integer idlavanderia

    );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicioreservas (id, fechainicial, fechafinal, idhabitacion, idspa, idsalon, idlavanderia) VALUES ( hotelandes_sequence.nextval , :fechainicial, :fechafinal, :idhabitacion, :idspa, :idsalon, :idlavanderia)", nativeQuery = true)
    void insertarReservas(@Param("fechainicial") Date fechainicial, 
                @Param("fechafinal") Date fechafinal,
                @Param("idhabitacion") Integer idhabitacion,
                @Param("idspa") Integer idspa,
                @Param("idsalon") Integer idsalon,
                @Param("idlavanderia") Integer idlavanderia

    );

}