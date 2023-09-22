package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.ReservaSalon;

import java.util.Collection;

public interface ReservaSalonRepo extends JpaRepository<ReservaSalon, Integer> {

    @Query(value = "SELECT * FROM reservaSalon", nativeQuery = true)
    Collection<ReservaSalon> darReservasSalon();

    @Query(value = "SELECT * FROM reservaSalon WHERE id = :id", nativeQuery = true)
    ReservaSalon darReservaSalon(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservaSalon WHERE id = :id", nativeQuery = true)
    void eliminarReservaSalon(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservaSalon SET dia = :dia, hora = :hora, duracion = :duracion WHERE id = :id", nativeQuery = true)
    void actualizarReservaSalon(@Param("id") long id, @Param("dia") String dia, @Param("hora") String hora,
            @Param("duracion") String duracion);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservaSalon (id, dia, hora, duracion) VALUES ( hotelandes_sequence.nextval , :dia, :hora, :duracion)", nativeQuery = true)
    void insertarReservaSalon(@Param("dia") String dia, @Param("hora") String hora,
            @Param("duracion") String duracion);

}