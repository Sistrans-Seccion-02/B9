
package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "salones")
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer capacidad;
    private String tipo;
    private Double costoHora;
    private Integer limpieza;
    private Boolean equipos;

    public Salon() {
        ;
    }

    public Salon(Integer capacidad, String tipo, Double costoHora, Integer limpieza, Boolean equipos) {
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.costoHora = costoHora;
        this.limpieza = limpieza;
        this.equipos = equipos;
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

    public Double getCostoHora() {
        return costoHora;
    }

    public Integer getLimpieza() {
        return limpieza;
    }

    public Boolean getEquipos() {
        return equipos;
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

    public void setCostoHora(Double costoHora) {
        this.costoHora = costoHora;
    }

    public void setLimpieza(Integer limpieza) {
        this.limpieza = limpieza;
    }

    public void setEquipos(Boolean equipos) {
        this.equipos = equipos;
    }

}