package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utileria")
public class Utileria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private Double costoPenalizacion;

    public Utileria() {
        ;
    }

    public Utileria(String nombre, Double costoPenalizacion) {
        this.nombre = nombre;
        this.costoPenalizacion = costoPenalizacion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getCostoPenalizacion() {
        return costoPenalizacion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCostoPenalizacion(Double costoPenalizacion) {
        this.costoPenalizacion = costoPenalizacion;
    }

}