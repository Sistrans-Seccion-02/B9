package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Plan;

import java.util.Collection;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

        @Query(value = "SELECT * FROM planes", nativeQuery = true)
        Collection<Plan> darPlanes();

        @Query(value = "SELECT * FROM planes WHERE id = :id", nativeQuery = true)
        Plan darPlan(@Param("id") long id);


        @Query(value = "SELECT * FROM planes b WHERE b.tipo LIKE '%' || :tipo || '%'", nativeQuery = true)
        Collection<Plan> darPlanesPorTipo(@Param("tipo") String tipo);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO planesconsumo (nombre, descripcion) VALUES ( :nombre, :descripcion)", nativeQuery = true)
        void insertarPlan(@Param("tipo") String tipo, @Param("descripcion") String descripcion);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM planes WHERE id = :id", nativeQuery = true)
        void eliminarPlan(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "UPDATE planes SET descripcion=:descripcion WHERE tipo=:tipo", nativeQuery = true)
        void actualizarPlan(@Param("id") long id, @Param("tipo") String tipo, @Param("descripcion") String descripcion);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM planes WHERE tipo=:tipo", nativeQuery = true)
        void eliminarPlan(@Param("tipo") String nombre);

}
