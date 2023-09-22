package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservaSalon")
public class ReservaSalon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String dia;
    private String hora;
    private String duracion;

    public ReservaSalon() {
        ;
    }

    public ReservaSalon(String dia, String hora, String duracion) {
        this.dia = dia;
        this.hora = hora;
        this.duracion = duracion;
    }

    public Integer getId() {
        return id;
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

}