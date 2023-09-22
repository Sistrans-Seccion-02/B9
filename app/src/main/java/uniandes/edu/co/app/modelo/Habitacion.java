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
    private Double precioNoche;
    private Double consumoExtra;

    public Habitacion() {
        ;
    }

    public Habitacion(Integer capacidad, String tipo, String dotacion, Double precioNoche, Double consumoExtra) {
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.dotacion = dotacion;
        this.precioNoche = precioNoche;
        this.consumoExtra = consumoExtra;
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

    public Double getPrecioNoche() {
        return precioNoche;
    }

    public Double getConsumoExtra() {
        return consumoExtra;
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

    public void setPrecioNoche(Double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public void setConsumoExtra(Double consumoExtra) {
        this.consumoExtra = consumoExtra;
    }

}