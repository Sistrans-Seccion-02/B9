
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
    private Integer idspa;
    private Integer idsalon;
    private Integer idlavanderia;

    public ServicioReservas() {
        ;
    }

    public ServicioReservas(String fechainicial, String fechafinal, Integer idhabitacion, Integer idspa, Integer idsalon, Integer idlavanderia) {

            this.fechainicial = fechainicial;
            this.fechafinal = fechafinal;

        
        this.idhabitacion = idhabitacion;
        this.idspa = idspa;
        this.idsalon = idsalon;
        this.idlavanderia = idlavanderia;
    }

    public Integer getId() {
        return id;
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

    public Integer getIdspa() {
        return idspa;
    }

    public Integer getIdsalon() {
        return idsalon;
    }

    public Integer getIdlavanderia() {
        return idlavanderia;
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

    public void setIdspa(Integer idspa) {
        this.idspa = idspa;
    }

    public void setIdsalon(Integer idsalon) {
        this.idsalon = idsalon;
    }

    public void setIdlavanderia(Integer idlavanderia) {
        this.idlavanderia = idlavanderia;
    }



}