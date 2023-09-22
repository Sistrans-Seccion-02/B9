package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lavanderias")
public class Lavanderia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tipoPrenda;
    private Integer numeroPrendas;
    private Integer paresZapatos;
    private Integer costo;

    public Lavanderia() {
        ;
    }

    public Lavanderia(String tipoPrenda, Integer numeroPrendas, Integer paresZapatos, Integer costo) {
        this.tipoPrenda = tipoPrenda;
        this.numeroPrendas = numeroPrendas;
        this.paresZapatos = paresZapatos;
        this.costo = costo;
    }

    public Integer getId() {
        return id;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public Integer getNumeroPrendas() {
        return numeroPrendas;
    }

    public Integer getParesZapatos() {
        return paresZapatos;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public void setNumeroPrendas(Integer numeroPrendas) {
        this.numeroPrendas = numeroPrendas;
    }

    public void setParesZapatos(Integer paresZapatos) {
        this.paresZapatos = paresZapatos;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

}