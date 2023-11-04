package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Usuario;

import java.util.Collection;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

        // do query to get user with a type of rol
        @Query(value = "SELECT * FROM usuarios WHERE rol = :rol", nativeQuery = true)
        Collection<Usuario> darUsuariosPorRol(@Param("rol") String rol);

        @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
        Collection<Usuario> darUsuarios();

        @Query(value = "SELECT * FROM usuarios WHERE id = :id", nativeQuery = true)
        Usuario darUsuario(@Param("id") long id);

        @Query(value = "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :contrasena")
        Collection<Usuario> loginUsuario(@Param("correo") String correo, @Param("contrasena") String contrasena);

        @Query(value = "SELECT * FROM usuarios u WHERE b.nombre LIKE '%' || :nombre || '%'", nativeQuery = true)
        Collection<Usuario> darUsuariosPorNombre(@Param("nombre") String nombre);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
        void eliminarUsuario(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "UPDATE usuarios SET nombre = :nombre, documento = :documento, tipodocumento = :tipodocumento, rol = :rol, correo = :correo, contrasena = :contrasena WHERE id = :id", nativeQuery = true)
        void actualizarUsuario(@Param("id") long id, @Param("nombre") String nombre,
                        @Param("documento") String documento,
                        @Param("tipodocumento") String tipoDocumento, @Param("rol") String rol,
                        @Param("correo") String correo,
                        @Param("contrasena") String contrasena);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO usuarios (id, nombre, documento, tipodocumento, rol, correo, contrasena) VALUES ( hotelandes_sequence.nextval , :nombre, :documento, :tipodocumento, :rol, :correo, :contrasena)", nativeQuery = true)
        void insertarUsuario(@Param("nombre") String nombre, @Param("documento") String documento,
                        @Param("tipodocumento") String tipodocumento, @Param("rol") String rol,
                        @Param("correo") String correo,
                        @Param("contrasena") String contrasena);

}