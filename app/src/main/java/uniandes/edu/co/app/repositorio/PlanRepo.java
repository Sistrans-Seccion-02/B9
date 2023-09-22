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
        @Query(value = "DELETE FROM planes WHERE id = :id", nativeQuery = true)
        void eliminarPlan(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "UPDATE planes SET tipo = :tipo, inicio = :inicio, final = :final WHERE id = :id", nativeQuery = true)
        void actualizarPlan(@Param("id") long id, @Param("tipo") String tipo, @Param("inicio") String inicio,
                        @Param("final") String finalD);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO planes (id, tipo, inicio, final) VALUES ( hotelandes_sequence.nextval , :tipo, :inicio, :final)", nativeQuery = true)
        void insertarPlan(@Param("tipo") String tipo, @Param("inicio") String inicio,
                        @Param("final") String finalD);

}
