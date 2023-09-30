package uniandes.edu.co.app.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date fecha;

    private Date fechafinal;

    private Integer personas;

    public Reserva() {
        ;
    }

    public Reserva(Date fecha, Date fechafinal, Integer personas) {
        this.fecha = fecha;
        this.fechafinal = fechafinal;
        this.personas = personas;
    }



    public Integer getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public Integer getPersonas() {
        return personas;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFechafinal(Date fechaFinal) {
        this.fechafinal = fechaFinal;
    }

    public void setPersonas(Integer personas) {
        this.personas = personas;
    }

}