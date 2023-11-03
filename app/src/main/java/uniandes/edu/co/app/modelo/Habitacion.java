package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer capacidad;
    private String tipo;
    private String dotacion;
    private Integer precionoche;
    private Integer consumoextra;

    private Integer hotelid;
    private Integer reservaid;
    private Integer usuarioid;
    private Integer servicioconsumoid;

    public Habitacion() {
        ;
    }

    public Habitacion(Integer capacidad, String tipo, String dotacion, Integer precionoche, Integer consumoextra,
            Integer hotelid, Integer reservaid, Integer usuarioid, Integer servicioconsumoid) {
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.dotacion = dotacion;
        this.precionoche = precionoche;
        this.consumoextra = consumoextra;
        this.hotelid = hotelid;
        this.reservaid = reservaid;
        this.usuarioid = usuarioid;
        this.servicioconsumoid = servicioconsumoid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDotacion() {
        return dotacion;
    }

    public Integer getPrecionoche() {
        return precionoche;
    }

    public Integer getConsumoextra() {
        return consumoextra;
    }

    public Integer getHotelid() {
        return hotelid;
    }

    public Integer getReservaid() {
        return reservaid;
    }

    public Integer getUsuarioid() {
        return usuarioid;
    }

    public Integer getServicioconsumoid() {
        return servicioconsumoid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDotacion(String dotacion) {
        this.dotacion = dotacion;
    }

    public void setPrecionoche(Integer precionoche) {
        this.precionoche = precionoche;
    }

    public void setConsumoextra(Integer consumoextra) {
        this.consumoextra = consumoextra;
    }

    public void setHotelid(Integer hotelid) {
        this.hotelid = hotelid;
    }

    public void setReservaid(Integer reservaid) {
        this.reservaid = reservaid;
    }

    public void setUsuarioid(Integer usuarioid) {
        this.usuarioid = usuarioid;
    }

    public void setServicioconsumoid(Integer servicioconsumoid) {
        this.servicioconsumoid = servicioconsumoid;
    }

}