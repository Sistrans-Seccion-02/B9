
package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "serviciosconsumo")
public class ServicioConsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private Integer capacidad;

    private String consumo;

    private Integer registroConsumo;

    public ServicioConsumo() {
        ;
    }

    public ServicioConsumo(String nombre, Integer capacidad, String consumo, Integer registroConsumo) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.consumo = consumo;
        this.registroConsumo = registroConsumo;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getConsumo() {
        return consumo;
    }

    public Integer getRegistroConsumo() {
        return registroConsumo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public void setRegistroConsumo(Integer registroConsumo) {
        this.registroConsumo = registroConsumo;
    }

}