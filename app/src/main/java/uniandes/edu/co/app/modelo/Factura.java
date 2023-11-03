package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Long preciototal;

    public Factura() {
        ;
    }

    public Factura(Long preciototal) {
        this.preciototal = preciototal;
    }

    public Integer getId() {
        return id;
    }

    public Long getPreciototal() {
        return preciototal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPreciototal(Long preciototal) {
        this.preciototal = preciototal;
    }

}