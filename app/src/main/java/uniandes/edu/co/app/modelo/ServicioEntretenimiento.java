package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "serviciosentretenimiento")
public class ServicioEntretenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String horarioServicio;

    private Integer capacidad;

    public ServicioEntretenimiento() {
        ;
    }

    public ServicioEntretenimiento(String horarioServicio, Integer capacidad) {
        this.horarioServicio = horarioServicio;
        this.capacidad = capacidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHorarioServicio() {
        return horarioServicio;
    }

    public void setHorarioServicio(String horarioServicio) {
        this.horarioServicio = horarioServicio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return this.horarioServicio + "|" + this.capacidad;
    }

}
