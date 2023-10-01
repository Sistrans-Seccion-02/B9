
package uniandes.edu.co.app.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registros")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date llegada;
    private Date salida;
    private Integer idreserva;
    private Integer idusuario;

    public Registro() {
        ;
    }

    public Registro(Date llegada, Date salida, Integer idreserva, Integer idusuario) {
        this.llegada = llegada;
        this.salida = salida;
        this.idreserva = idreserva;
        this.idusuario = idusuario;
    }

    public Integer getId() {
        return id;
    }

    public Date getLlegada() {
        return llegada;
    }

    public Date getSalida() {
        return salida;
    }

    public Integer getIdreserva() {
        return idreserva;
    }

    public Integer getIdusuario() {
        return idusuario;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setLlegada(Date llegada) {
        this.llegada = llegada;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public void setIdreserva(Integer idreserva) {
        this.idreserva = idreserva;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

}