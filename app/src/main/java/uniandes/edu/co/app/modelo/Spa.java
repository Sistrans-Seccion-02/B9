
package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "spas")
public class Spa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String duracion;
    private Float costo;
    private Integer capacidad;
    private Boolean disponibilidad;

    public Spa() {
        ;
    }

    public Spa(String duracion, Float costo, Integer capacidad, Boolean disponibilidad) {
        this.duracion = duracion;
        this.costo = costo;
        this.capacidad = capacidad;
        this.disponibilidad = disponibilidad;
    }

    public Integer getId() {
        return id;
    }

    public String getDuracion() {
        return duracion;
    }

    public Float getCosto() {
        return costo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}