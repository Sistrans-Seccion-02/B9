
package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Float costo;
    private String nombre;

    public Producto() {
        ;
    }

    public Producto(String nombre, Float costo) {
        this.costo = costo;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public Float getCosto() {
        return costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}