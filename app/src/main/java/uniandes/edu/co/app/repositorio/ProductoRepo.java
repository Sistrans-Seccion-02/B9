package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Producto;

import java.util.Collection;

public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM producto", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM producto WHERE id = :id", nativeQuery = true)
    Producto darProducto(@Param("id") long id);

    @Query(value = "SELECT * FROM producto WHERE b.nombre LIKE '%' || :nombre || '%'", nativeQuery = true)
    Collection<Producto> darProductosPorNombre(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM producto WHERE id = :id", nativeQuery = true)
    void eliminarProducto(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE producto SET nombre = :nombre WHERE id = :id", nativeQuery = true)
    void actualizarProducto(@Param("id") long id, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO producto (id, costo, nombre) VALUES ( hotelandes_sequence.nextval , :costo, :nombre)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, @Param("costo") Float costo);

}