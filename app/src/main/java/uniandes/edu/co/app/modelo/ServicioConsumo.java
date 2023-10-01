
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

    private String descripcion;

    private Integer costo;

    private String fecha;

    private Integer idhabitacion;
    private Integer idproducto;

    public ServicioConsumo() {
        ;
    }

    public ServicioConsumo(String descripcion, Integer costo, String fecha, Integer idhabitacion, Integer idproducto) {
        this.descripcion = descripcion;
        this.costo = costo;
        this.fecha = fecha;
        this.idhabitacion = idhabitacion;
        this.idproducto = idproducto;
    }

    public Integer getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCosto() {
        return costo;
    }

    public String getFecha() {
        return fecha;
    }

    public Integer getIdhabitacion() {
        return idhabitacion;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIdhabitacion(Integer idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

}