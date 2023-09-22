package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.ServicioEntretenimiento;

import java.util.Collection;

public interface ServicioEntretenimientoRepo extends JpaRepository<ServicioEntretenimiento, Integer> {

    @Query(value = "SELECT * FROM servicioentretenimiento", nativeQuery = true)
    Collection<ServicioEntretenimiento> darServiciosEntretenimiento();

    @Query(value = "SELECT * FROM servicioentretenimiento WHERE id = :id", nativeQuery = true)
    ServicioEntretenimiento darServicioEntretenimiento(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicioentretenimiento WHERE id = :id", nativeQuery = true)
    void eliminarServicioEntretenimiento(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE servicioentretenimiento SET horarioServicio = :horarioServicio, capacidad = :capacidad WHERE id = :id", nativeQuery = true)
    void actualizarServicioEntretenimiento(@Param("id") long id, @Param("horarioServicio") String horarioServicio,
            @Param("capacidad") Integer capacidad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicioentretenimiento (id, horarioServicio) VALUES ( hotelandes_sequence.nextval , :horarioServicio, :capacidad)", nativeQuery = true)
    void insertarServicioEntretenimiento(@Param("horarioServicio") String horarioServicio,
            @Param("capacidad") Integer capacidad);

}
