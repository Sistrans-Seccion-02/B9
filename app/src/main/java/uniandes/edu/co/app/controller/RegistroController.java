package uniandes.edu.co.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Registro;

import uniandes.edu.co.app.repositorio.RegistroRepo;

@Controller
public class RegistroController {

    @Autowired
    private RegistroRepo repo;

    @GetMapping("/registros")
    public String registros(Model model, Integer id) {
        if (id != null && !id.equals("")) {
            model.addAttribute("registros", repo.darRegistro(id));
        } else {
            model.addAttribute("registros", repo.darRegistros());
        }

        return "registros";
    }

    @GetMapping("/registros/new")
    public String registroForm(Model model) {
        model.addAttribute("registro", new Registro());
        return "registroNuevo";
    }

    @PostMapping("/registros/new/save")
    public String registroGuardar(@ModelAttribute Registro registro) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaInicial = dateFormat.parse(registro.getLlegada());

        java.sql.Date sqlFechaInicial = new java.sql.Date(fechaInicial.getTime());
        java.sql.Date sqlFechaFinal;
        if (!"n/a".equals(registro.getSalida())) {
            Date fechaFinal = dateFormat.parse(registro.getSalida());
            sqlFechaFinal = new java.sql.Date(fechaFinal.getTime());
        }
        else{
            sqlFechaFinal = null;
        }

        repo.insertarRegistro(
            sqlFechaInicial,
            sqlFechaFinal,
            registro.getIdreserva(),
            registro.getIdusuario());

        return "redirect:/registros";
    } catch (ParseException e) {
        e.printStackTrace();
        return null;}
    }

    @GetMapping("/registros/{id}/edit")
    public String registroEditarForm(@PathVariable("id") long id, Model model) {
        Registro registro = repo.darRegistro(id);
        if (registro != null) {
            model.addAttribute("registro", registro);
            return "registroEditar";
        } else {
            return "redirect:/registros";
        }
    }

    @PostMapping("/registros/{id}/edit/save")
    public String registroEditarGuardar(@PathVariable("id") long id, @ModelAttribute Registro registro) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaInicial = dateFormat.parse(registro.getLlegada());
        Date fechaFinal = dateFormat.parse(registro.getSalida());

        java.sql.Date sqlFechaInicial = new java.sql.Date(fechaInicial.getTime());
        java.sql.Date sqlFechaFinal = new java.sql.Date(fechaFinal.getTime());

        repo.actualizarRegistro(id, sqlFechaInicial, sqlFechaFinal, registro.getIdreserva(), registro.getIdusuario());

        return "redirect:/registros";
    } catch (ParseException e) {
        e.printStackTrace();
        return null;
    }
    }

    @GetMapping("/registros/{id}/delete")
    public String salonBorrar(@PathVariable("id") long id) {
        repo.eliminarRegistro(id);
        return "redirect:/registros";
    }

}