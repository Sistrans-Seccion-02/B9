package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Habitacion;

import java.util.Collection;

public interface HabitacionRepo extends JpaRepository<Habitacion, Integer> {

        @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
        Collection<Habitacion> darHabitaciones();

        @Query(value = "SELECT * FROM habitaciones WHERE id = :id", nativeQuery = true)
        Habitacion darHabitacion(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM habitaciones WHERE id = :id", nativeQuery = true)
        void eliminarHabitacion(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "UPDATE habitaciones SET capacidad = :capacidad, tipo = :tipo, dotacion = :dotacion, precioNoche = :precioNoche, consumoExtra = :consumoExtra WHERE id = :id", nativeQuery = true)
        void actualizarHabitacion(@Param("id") long id, @Param("capacidad") Integer capacidad,
                        @Param("tipo") String tipo,
                        @Param("dotacion") String dotacion, @Param("precioNoche") Double precioNoche,
                        @Param("consumoExtra") Double consumoExtra);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO habitaciones (id, capacidad, tipo, dotacion, precioNoche, consumoExtra) VALUES ( hotelandes_sequence.nextval , :capacidad, :tipo, :dotacion, :precioNoche, :consumoExtra)", nativeQuery = true)
        void insertarHabitacion(@Param("capacidad") Integer capacidad, @Param("tipo") String tipo,
                        @Param("dotacion") String dotacion, @Param("precioNoche") Double precioNoche,
                        @Param("consumoExtra") Double consumoExtra);

}