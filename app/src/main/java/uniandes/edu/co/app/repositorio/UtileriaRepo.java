package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Utileria;

import java.util.Collection;

public interface UtileriaRepo extends JpaRepository<Utileria, Integer> {

    @Query(value = "SELECT * FROM utileria", nativeQuery = true)
    Collection<Utileria> darUtileria();

    @Query(value = "SELECT * FROM utileria WHERE id = :id", nativeQuery = true)
    Utileria darUtileriaPorId(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM utileria WHERE id = :id", nativeQuery = true)
    void eliminarUtileria(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE utileria SET nombre = :nombre, costoPenalizacion = :costoPenalizacion WHERE id = :id", nativeQuery = true)
    void actualizarUtileria(@Param("id") long id, @Param("nombre") String nombre,
            @Param("costoPenalizacion") Double costoPenalizacion);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO utileria (id, nombre, costoPenalizacion) VALUES ( hotelandes_sequence.nextval , :nombre, :costoPenalizacion)", nativeQuery = true)
    void insertarUtileria(@Param("nombre") String nombre, @Param("costoPenalizacion") Double costoPenalizacion);

}