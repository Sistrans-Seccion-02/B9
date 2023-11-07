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

        public interface RespuestaEncontrarBuenosClientes {
                String getNOMBRE_CLIENTE();

                String getUSUARIOID();

                String getTOTAL_DIAS();
        }

        @Query(value = "SELECT U.NOMBRE as NOMBRE_CLIENTE, " +
                        "R.USUARIOID, SUM(R.FECHAFINAL - R.FECHA) as TOTAL_DIAS " +
                        "FROM RESERVAS R " +
                        "JOIN USUARIOS U ON R.USUARIOID = U.ID " +
                        "GROUP BY U.NOMBRE, R.USUARIOID " +
                        "HAVING SUM(R.FECHAFINAL - R.FECHA) >= 14", nativeQuery = true)

        Collection<RespuestaEncontrarBuenosClientes> encontrarBuenosClientes();

        public interface RespuestaEncontrarMayorConsumo {
                String getNOMBRE_CLIENTE();

                String getUSUARIOID();

                String getTOTAL_CONSUMO();
        }

        @Query(value = "SELECT U.NOMBRE as NOMBRE_CLIENTE, " + //
                        "R.USUARIOID, SUM(SC.COSTO) as TOTAL_CONSUMO " + //
                        "FROM SERVICIOSCONSUMO SC " + //
                        "JOIN RESERVAS R ON SC.IDHABITACION = R.HABTACIONID " + //
                        "JOIN USUARIOS U ON R.USUARIOID = U.ID " + //
                        "WHERE SC.FECHA >= SYSDATE - 365 " + //
                        "GROUP BY U.NOMBRE, R.USUARIOID " + //
                        "HAVING SUM(SC.COSTO) >= 15000000", nativeQuery = true)
        Collection<RespuestaEncontrarMayorConsumo> encontrarMayorConsumo();

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

        // Requerimiento consulta avanzada 9

        public interface consultaConsumoAtts {
                String getID();

                String getNOMBRE();

                String getDOCUMENTO();

                String getTIPODOCUMENTO();

                String getCORREO();

                String getDESCRIPCION();

                String getCOSTO();

                String getFECHA();

                String getIDHABITACION();

                String getIDPRODUCTO();
        }

        @Query(value = "SELECT U.ID, U.NOMBRE, U.DOCUMENTO, U.TIPODOCUMENTO, U.CORREO, S.DESCRIPCION, S.COSTO, S.FECHA, S.IDHABITACION, S.IDPRODUCTO "
                        + //
                        "FROM USUARIOS U " + //
                        "JOIN HABITACIONES H ON U.ID = H.USUARIOID " + //
                        "JOIN SERVICIOSCONSUMO S ON H.ID = S.IDHABITACION " + //
                        "WHERE U.ROL = 'Cliente' AND S.FECHA BETWEEN TO_DATE(:fechainicio, 'DD-MM-YYYY') " + //
                        "AND TO_DATE(:fechafin, 'DD-MM-YYYY')", nativeQuery = true)
        Collection<consultaConsumoAtts> consultaConsumo(@Param("fechainicio") String fechainicio,
                        @Param("fechafin") String fechafin);

        // Requerimiento avanzado 10:
        public interface consultaConsumoAttsV2 {
                String getID();

                String getNOMBRE();

                String getDOCUMENTO();

                String getTIPODOCUMENTO();

                String getCORREO();
        }

        @Query(value = "SELECT U.ID, U.NOMBRE, U.DOCUMENTO, U.TIPODOCUMENTO, U.CORREO "
                        + //
                        "FROM USUARIOS U " + //
                        "JOIN HABITACIONES H ON U.ID = H.USUARIOID " + //
                        "LEFT JOIN SERVICIOSCONSUMO S ON H.ID = S.IDHABITACION " + //
                        "AND S.FECHA BETWEEN TO_DATE(:fechainicio, 'DD-MM-YYYY') AND TO_DATE(:fechafin, 'DD-MM-YYYY') "
                        + //
                        "WHERE U.ROL = 'Cliente'  " + //
                        "HAVING COUNT((U.ID)) = 1  " + //
                        "GROUP BY U.ID, U.NOMBRE, U.DOCUMENTO, U.TIPODOCUMENTO, U.CORREO ", nativeQuery = true)
        Collection<consultaConsumoAttsV2> consultaConsumoV2(@Param("fechainicio") String fechainicio,
                        @Param("fechafin") String fechafin);

        // Requerimiento avanzado 12:
        public interface clientesExcelentesAtts {
                String getID();

                String getNOMBRE();

                String getCORREO();

                String getDOCUMENTO();

                String getTIPOCLIENTEEXCELENTE();
        }

        @Query(value = "SELECT " +
                        " U.ID AS ID, " +
                        " U.NOMBRE AS NOMBRE, " +
                        " U.CORREO AS CORREO, " +
                        " U.DOCUMENTO AS DOCUMENTO, " +
                        " CASE " +
                        " WHEN EXISTS ( " +
                        " SELECT 1 " +
                        " FROM RESERVAS R " +
                        " WHERE R.USUARIOID = U.ID " +
                        " AND R.FECHA BETWEEN TRUNC(SYSDATE, 'Q') - INTERVAL '3' MONTH AND SYSDATE " +
                        " ) THEN 'Cliente de Estancias Trimestrales' " +
                        " WHEN EXISTS ( " +
                        " SELECT 1 " +
                        " FROM SERVICIOSCONSUMO SC " +
                        " JOIN HABITACIONES ON HABITACIONES.USUARIOID = U.ID " +
                        " WHERE HABITACIONES.USUARIOID = U.ID " +
                        " AND SC.COSTO > 300000 " +
                        " ) THEN 'Cliente de Servicio Costoso' " +
                        " END AS TipoClienteExcelente " +
                        " FROM USUARIOS U"

                        , nativeQuery = true)
        Collection<clientesExcelentesAtts> clientesExcelentes();

}