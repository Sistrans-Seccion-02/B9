package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Lavanderia;

import java.util.Collection;

public interface LavanderiaRepo extends JpaRepository<Lavanderia, Integer> {

    @Query(value = "SELECT * FROM lavanderias", nativeQuery = true)
    Collection<Lavanderia> darLavanderias();

    @Query(value = "SELECT * FROM lavanderias WHERE id = :id", nativeQuery = true)
    Lavanderia darLavanderia(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM lavanderias WHERE id = :id", nativeQuery = true)
    void eliminarLavanderia(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE lavanderias SET tipoPrenda = :tipoPrenda, numeroPrendas = :numeroPrendas, paresZapatos = :paresZapatos, costo = :costo WHERE id = :id", nativeQuery = true)
    void actualizarLavanderia(@Param("id") long id, @Param("tipoPrenda") String tipoPrenda,
            @Param("numeroPrendas") Integer numeroPrendas,
            @Param("paresZapatos") Integer paresZapatos, @Param("costo") Integer costo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO lavanderias (id, tipoPrenda, numeroPrendas, paresZapatos, costo) VALUES ( hotelandes_sequence.nextval , :tipoPrenda, :numeroPrendas, :paresZapatos, :costo)", nativeQuery = true)
    void insertarLavanderia(@Param("tipoPrenda") String tipoPrenda,
            @Param("numeroPrendas") Integer numeroPrendas,
            @Param("paresZapatos") Integer paresZapatos, @Param("costo") Integer costo);

}