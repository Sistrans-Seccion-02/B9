package uniandes.edu.co.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.app.modelo.Habitacion;

import java.util.Collection;

public interface HabitacionRepo extends JpaRepository<Habitacion, Integer> {

        @Query(value = "SELECT * FROM habitaciones WHERE tipo = :tipo", nativeQuery = true)
        Collection<Habitacion> darHabitacionesPorTipo(@Param("tipo") String tipo);

        @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
        Collection<Habitacion> darHabitaciones();

        public interface RespuestaOcupacionHabitacion {
                String getFECHA();
                Integer getHABITACIONES_OCUPADAS();   
        }
        @Query(value = "SELECT FECHA, COUNT(*) AS HABITACIONES_OCUPADAS "+//
                       "FROM RESERVAS "+//
                       "GROUP BY FECHA ORDER BY HABITACIONES_OCUPADAS DESC", nativeQuery = true)
        Collection<RespuestaOcupacionHabitacion> darHabitacionesOcupadas();

        public interface RespuestaFechasMayoresIngresos {
                String getFECHA();
                Integer getINGRESOS();   
        }
        @Query(value = "SELECT SC.FECHA, SUM(SC.COSTO) AS INGRESOS "+//
                       "FROM SERVICIOSCONSUMO SC "+//
                       "GROUP BY SC.FECHA ORDER BY INGRESOS DESC", nativeQuery = true)
        Collection<RespuestaFechasMayoresIngresos> darFechasMayoresIngresos();

        public interface RespuestaMenorDemanda{
                String getFECHA();
                Integer getHABITACIONES_OCUPADAS();
        }
        @Query(value = "SELECT FECHA, COUNT(*) AS HABITACIONES_OCUPADAS "+//
                       "FROM RESERVAS "+//
                       "GROUP BY FECHA ORDER BY HABITACIONES_OCUPADAS ASC", nativeQuery = true)
        Collection<RespuestaMenorDemanda> darMenorDemanda();

        @Query(value = "SELECT * FROM habitaciones WHERE id = :id", nativeQuery = true)
        Habitacion darHabitacion(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM habitaciones WHERE id = :id", nativeQuery = true)
        void eliminarHabitacion(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "UPDATE habitaciones SET capacidad = :capacidad, tipo = :tipo, dotacion = :dotacion, precioNoche = :precioNoche, consumoExtra = :consumoExtra, hotelid = :hotelid, reservaid = :reservaid, usuarioid = :usuarioid, servicioconsumoid = :servicioconsumoid WHERE id = :id", nativeQuery = true)
        void actualizarHabitacion(@Param("id") long id, @Param("capacidad") Integer capacidad,
                        @Param("tipo") String tipo,
                        @Param("dotacion") String dotacion, @Param("precioNoche") Integer precioNoche,
                        @Param("consumoExtra") Integer consumoExtra,
                        @Param("hotelid") Integer hotelid,
                        @Param("reservaid") Integer reservaid,
                        @Param("usuarioid") Integer usuarioid,
                        @Param("servicioconsumoid") Integer servicioconsumoid);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO habitaciones (id, capacidad, tipo, dotacion, precioNoche, consumoExtra, hotelid, reservaid, usuarioid, servicioconsumoid) VALUES ( hotelandes_sequence.nextval , :capacidad, :tipo, :dotacion, :precioNoche, :consumoExtra, :hotelid, :reservaid, :usuarioid, :servicioconsumoid)", nativeQuery = true)
        void insertarHabitacion(@Param("capacidad") Integer capacidad, @Param("tipo") String tipo,
                        @Param("dotacion") String dotacion, @Param("precioNoche") Integer precioNoche,
                        @Param("consumoExtra") Integer consumoExtra, @Param("hotelid") Integer hotelid,
                        @Param("reservaid") Integer reservaid,
                        @Param("usuarioid") Integer usuarioid,
                        @Param("servicioconsumoid") Integer servicioconsumoid);

}