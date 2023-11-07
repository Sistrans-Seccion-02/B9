package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.ServicioConsumo;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

          public interface RespuestaServicioConsumoPorCliente {
               String getNOMBRE_USUARIO();
               String getNOMBRE_PRODUCTO();
               int getCOSTO_CONSUMO();
               //Date getfecha();
          }

          @Query(value= "SELECT u.nombre as nombre_usuario, p.nombre as nombre_producto, "+//
                         "sc.costo as costo_consumo "+// 
                         "FROM serviciosconsumo sc "+//
                         "JOIN productos p ON sc.idproducto = p.id "+//
                         "JOIN reservas r ON sc.idhabitacion = r.habtacionid "+//
                         "JOIN usuarios u ON r.usuarioid = u.id "+//
                         "WHERE u.id = :idusuario "+//
                         "AND sc.fecha BETWEEN :fechainicio AND :fechafin", nativeQuery = true)
                         
          Collection<RespuestaServicioConsumoPorCliente> darServicioConsumoPorCliente(@Param("idusuario") long idusuario, @Param("fechainicio") Date fechainicio, @Param("fechafin") Date fechafin);
        

               // RFC AVANZADA 11:
     public interface consultaFuncionamientoAtts {
          String getINICIO();

          String getFIN();

          String getSERVICIOMASCONSUMIDO();

          String getSERVICIOMENOSCONSUMIDO();

          String getHABITACIONMASSOLICITADA();

          String getHABITACIONMENOSSOLICITADA();
     }

     @Query(value = "WITH WeekNumbers AS (" +
               "    SELECT LEVEL AS WeekNumber" +
               "    FROM DUAL" +
               "    CONNECT BY LEVEL <= 52)" +
               ", WeekDates AS (" +
               "    SELECT" +
               "        TO_DATE('01-01-2023', 'DD-MM-YYYY') + (WeekNumber - 1) * 7 AS StartDate," +
               "        TO_DATE('01-01-2023', 'DD-MM-YYYY') + (WeekNumber - 1) * 7 + 6 AS EndDate" +
               "    FROM WeekNumbers)" +
               " SELECT " +
               "    TO_CHAR(StartDate, 'DD-MM-YYYY') AS Inicio," +
               "    TO_CHAR(EndDate, 'DD-MM-YYYY') AS Fin," +
               "    (SELECT Descripcion FROM (" +
               "        SELECT s.Descripcion, COUNT(*) AS ConsumptionCount" +
               "        FROM SERVICIOSCONSUMO s" +
               "        WHERE s.FECHA BETWEEN StartDate AND EndDate" +
               "        GROUP BY s.Descripcion" +
               "        ORDER BY COUNT(*) DESC" +
               "    ) WHERE ROWNUM = 1) AS servicioMasConsumido," +
               "    (SELECT Descripcion FROM (" +
               "        SELECT s.Descripcion, COUNT(*) AS ConsumptionCount" +
               "        FROM SERVICIOSCONSUMO s" +
               "        WHERE s.FECHA BETWEEN StartDate AND EndDate" +
               "        GROUP BY s.Descripcion" +
               "        ORDER BY COUNT(*) ASC" +
               "    ) WHERE ROWNUM = 1) AS servicioMenosConsumido," +
               "    (SELECT HabtacionID FROM (" +
               "        SELECT r.HabtacionID, COUNT(*) AS ReservationCount" +
               "        FROM RESERVAS r" +
               "        WHERE r.FECHA BETWEEN StartDate AND EndDate" +
               "        GROUP BY r.HabtacionID" +
               "        ORDER BY COUNT(*) DESC" +
               "    ) WHERE ROWNUM = 1) AS habitacionMasSolicitada," +
               "    (SELECT HabtacionID FROM (" +
               "        SELECT r.HabtacionID, COUNT(*) AS ReservationCount" +
               "        FROM RESERVAS r" +
               "        WHERE r.FECHA BETWEEN StartDate AND EndDate" +
               "        GROUP BY r.HabtacionID" +
               "        ORDER BY COUNT(*) ASC" +
               "    ) WHERE ROWNUM = 1) AS habitacionMenosSolicitada" +
               " FROM WeekDates", nativeQuery = true)

     Collection<consultaFuncionamientoAtts> consultaFuncionamiento();
}