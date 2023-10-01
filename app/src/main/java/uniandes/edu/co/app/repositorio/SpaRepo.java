package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Spa;

import java.util.Collection;

public interface SpaRepo extends JpaRepository<Spa, Integer> {

    @Query(value = "SELECT * FROM spas", nativeQuery = true)
    Collection<Spa> darSpas();

    @Query(value = "SELECT * FROM spas WHERE id = :id", nativeQuery = true)
    Spa darSpa(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM spas WHERE id = :id", nativeQuery = true)
    void eliminarSpa(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE spas SET duracion = :duracion, costo = :costo, capacidad = :capacidad, disponibilidad = :disponibilidad WHERE id = :id", nativeQuery = true)
    void actualizarSpas(@Param("id") long id, @Param("duracion") String duracion, @Param("costo") Float costo,
            @Param("capacidad") Integer capacidad,
            @Param("disponibilidad") Boolean disponibilidad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO spas (id, duracion, costo, capacidad, disponibilidad) VALUES ( hotelandes_sequence.nextval , :duracion, :costo, :capacidad, :disponibilidad)", nativeQuery = true)
    void insertarSpa(@Param("duracion") String duracion, @Param("costo") Float costo,
            @Param("capacidad") Integer capacidad,
            @Param("disponibilidad") Boolean disponibilidad);

}