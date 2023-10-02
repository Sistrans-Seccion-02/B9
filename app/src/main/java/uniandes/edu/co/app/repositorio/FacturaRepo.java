package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Factura;

import java.util.Collection;

public interface FacturaRepo extends JpaRepository<Factura, Integer> {

    @Query(value = "SELECT * FROM facturas", nativeQuery = true)
    Collection<Factura> darFacturas();

    @Query(value = "SELECT * FROM facturas WHERE id = :id", nativeQuery = true)
    Factura darFactura(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM facturas WHERE id = :id", nativeQuery = true)
    void eliminarFactura(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE facturas SET preciototal = :preciototal WHERE id = :id", nativeQuery = true)
    void actualizarFactura(@Param("id") long id, @Param("preciototal") Long preciototal);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO facturas (id, preciototal) VALUES ( hotelandes_sequence.nextval , :preciototal)", nativeQuery = true)
    void insertarFactura(@Param("preciototal") Long preciototal);

}