
package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicioreservas")
public class ServicioReservas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String fechainicial;
    private String fechafinal;
    private Integer idhabitacion;
    private String tipo;
    private Double precio;

    public ServicioReservas() {
        ;
    }

    public ServicioReservas(String fechainicial, String fechafinal, Integer idhabitacion, String tipo, Double precio) {

            this.fechainicial = fechainicial;
            this.fechafinal = fechafinal;
            this.idhabitacion = idhabitacion;
            this.tipo = tipo;
            this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
    public Double getPrecio() {
        return precio;
    }

    public String getFechainicial() {
        return fechainicial;
    }

    public String getFechafinal() {
        return fechafinal;
    }

    public Integer getIdhabitacion() {
        return idhabitacion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFechainicial(String fechainicial) {
        this.fechainicial = fechainicial;
    }

    public void setFechafinal(String fechafinal) {
        this.fechafinal = fechafinal;
    }

    public void setIdhabitacion(Integer idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(Double precio){
        this.precio = precio;
    }

}