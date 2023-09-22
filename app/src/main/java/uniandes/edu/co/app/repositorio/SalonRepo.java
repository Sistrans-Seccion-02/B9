package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Salon;

import java.util.Collection;

public interface SalonRepo extends JpaRepository<Salon, Integer> {

    @Query(value = "SELECT * FROM salones", nativeQuery = true)
    Collection<Salon> darSalones();

    @Query(value = "SELECT * FROM salones WHERE id = :id", nativeQuery = true)
    Salon darSalon(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM salones WHERE id = :id", nativeQuery = true)
    void eliminarSalon(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE salones SET capacidad = :capacidad, tipo = :tipo, costoHora = :costoHora, limpieza = :limpieza, equipos = :equipos WHERE id = :id", nativeQuery = true)
    void actualizarSalon(@Param("id") long id, @Param("capacidad") Integer capacidad, @Param("tipo") String tipo,
            @Param("costoHora") Double costoHora,
            @Param("limpieza") Integer limpieza, @Param("equipos") Boolean equipos);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO salones (id, capacidad, tipo, costoHora, limpieza, equipos) VALUES ( hotelandes_sequence.nextval , :capacidad, :tipo, :costoHora, :limpieza, :equipos)", nativeQuery = true)
    void insertarSalon(@Param("capacidad") Integer capacidad, @Param("tipo") String tipo,
            @Param("costoHora") Double costoHora,
            @Param("limpieza") Integer limpieza, @Param("equipos") Boolean equipos);

}