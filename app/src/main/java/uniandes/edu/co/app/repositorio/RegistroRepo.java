package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Registro;

import java.sql.Date;
import java.util.Collection;

public interface RegistroRepo extends JpaRepository<Registro, Integer> {

    @Query(value = "SELECT * FROM registros", nativeQuery = true)
    Collection<Registro> darRegistros();

    @Query(value = "SELECT * FROM registros WHERE id = :id", nativeQuery = true)
    Registro darRegistro(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM registros WHERE id = :id", nativeQuery = true)
    void eliminarRegistro(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE registros SET llegada = :llegada, salida = :salida, idreserva = :idreserva, idusuario = :idusuario WHERE id = :id", nativeQuery = true)
    void actualizarRegistro(@Param("id") long id, @Param("llegada") Date llegada, @Param("salida") Date salida,
            @Param("idreserva") Integer idreserva,
            @Param("idusuario") Integer idusuario);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO registros (id, llegada, salida, idreserva, idusuario) VALUES ( hotelandes_sequence.nextval , :llegada, :salida, :idreserva, :idusuario)", nativeQuery = true)
    void insertarRegistro(@Param("llegada") Date llegada, @Param("salida") Date salida,
            @Param("idreserva") Integer idreserva,
            @Param("idusuario") Integer idusuario);

}