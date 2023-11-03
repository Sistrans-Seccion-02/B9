package uniandes.edu.co.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String documento;
    private String tipodocumento;
    private String rol;
    private String correo;
    private String contrasena;

    public Usuario(String nombre, String documento, String tipodocumento, String rol, String correo,
            String contrasena) {
        this.nombre = nombre;
        this.documento = documento;
        this.tipodocumento = tipodocumento;
        this.rol = rol;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Usuario() {
        ;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public String getRol() {
        return rol;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}