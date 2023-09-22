package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Hotel;

import java.util.Collection;

public interface HotelRepo extends JpaRepository<Hotel, Integer> {

    @Query(value = "SELECT * FROM hoteles", nativeQuery = true)
    Collection<Hotel> darHoteles();

    @Query(value = "SELECT * FROM hoteles WHERE id = :id", nativeQuery = true)
    Hotel darHotel(@Param("id") long id);

    @Query(value = "SELECT * FROM hoteles b WHERE b.nombre LIKE '%' || :nombre || '%'", nativeQuery = true)
    Collection<Hotel> darHotelesPorNombre(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM hoteles WHERE id = :id", nativeQuery = true)
    void eliminarHotel(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hoteles SET nombre = :nombre, tipo = :tipo, estrellas = :estrellas, categoria = :categoria, ciudad = :ciudad WHERE id = :id", nativeQuery = true)
    void actualizarHotel(@Param("id") long id, @Param("nombre") String nombre, @Param("tipo") String tipo,
            @Param("estrellas") Integer estrellas, @Param("categoria") String categoria,
            @Param("ciudad") String ciudad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hoteles (id, nombre, tipo, estrellas, categoria, ciudad) VALUES ( hotelandes_sequence.nextval , :nombre, :tipo, :estrellas, :categoria, :ciudad)", nativeQuery = true)
    void insertarHotel(@Param("nombre") String nombre, @Param("tipo") String tipo,
            @Param("estrellas") Integer estrellas, @Param("categoria") String categoria,
            @Param("ciudad") String ciudad);

}