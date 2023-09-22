package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hoteles")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private String tipo;

    private Integer estrellas;
    private String categoria;
    private String ciudad;

    public Hotel() {
        ;
    }

    public Hotel(String nombre, String tipo, Integer estrellas, String categoria, String ciudad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.estrellas = estrellas;
        this.categoria = categoria;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}