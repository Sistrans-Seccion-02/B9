package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.ServicioConsumo;

import java.sql.Date;
import java.util.Collection;

public interface ServicioConsumoRepo extends JpaRepository<ServicioConsumo, Integer> {

        @Query(value = "SELECT * FROM serviciosconsumo", nativeQuery = true)
        Collection<ServicioConsumo> darServiciosConsumo();

        @Query(value = "SELECT * FROM serviciosconsumo WHERE id = :id", nativeQuery = true)
        ServicioConsumo darServicioConsumo(@Param("id") long id);

         @Query(value = "SELECT * FROM serviciosconsumo WHERE idhabitacion = :idhabitacion", nativeQuery = true)
        Collection<ServicioConsumo> darServicioConsumoPorIdHabitacion(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM serviciosconsumo WHERE id = :id", nativeQuery = true)
        void eliminarServicioConsumo(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "UPDATE serviciosconsumo SET descripcion = :descripcion, costo = :costo, fecha = :fecha, idhabitacion = :idhabitacion, idproducto = :idproducto WHERE id = :id", nativeQuery = true)
        void actualizarServicioConsumo(@Param("id") long id, @Param("descripcion") String descripcion,
                        @Param("costo") Integer costo,
                        @Param("fecha") Date fecha, @Param("idhabitacion") Integer idhabitacion, @Param("idproducto") Integer idproducto) ;

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO serviciosconsumo (id, descripcion, costo, fecha, idhabitacion, idproducto) VALUES ( hotelandes_sequence.nextval , :descripcion, :costo, :fecha, :idhabitacion, :idproducto)", nativeQuery = true)
        void insertarServicioConsumo(@Param("descripcion") String descripcion,
                        @Param("costo") Integer costo,
                        @Param("fecha") Date fecha, @Param("idhabitacion") Integer idhabitacion, @Param("idproducto") Integer idproducto) ;

}