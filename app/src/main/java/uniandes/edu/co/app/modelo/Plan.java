package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "planes")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tipo;

    private String inicio;

    private String finalD;

    public Plan() {
        ;
    }

    public Plan(String tipo, String inicio, String finalD) {
        this.tipo = tipo;
        this.inicio = inicio;
        this.finalD = finalD;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFinal() {
        return finalD;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setFinal(String finalD) {
        this.finalD = finalD;
    }

}