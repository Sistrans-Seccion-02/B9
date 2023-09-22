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

    private Long precioTotal;

    public Factura() {
        ;
    }

    public Factura(Long precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public Long getPrecioTotal() {
        return precioTotal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrecioTotal(Long precioTotal) {
        this.precioTotal = precioTotal;
    }

}