package uniandes.edu.co.app.controller;

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
    public String registros(Model model, String nombre) {

        model.addAttribute("registros", repo.darRegistros());

        return "registros";
    }

    @GetMapping("/registros/new")
    public String registroForm(Model model) {
        model.addAttribute("registro", new Registro());
        return "registroNuevo";
    }

    @PostMapping("/registros/new/save")
    public String bebedorGuardar(@ModelAttribute Registro registro) {
        repo.insertarRegistro(registro.getLlegada(), registro.getSalida(), registro.getIdreserva(), registro.getIdusuario());
        return "redirect:/registros";
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
        repo.actualizarRegistro(((long) id), registro.getLlegada(), registro.getSalida(), registro.getIdreserva(),
                registro.getIdusuario());
        return "redirect:/registros";
    }

    @GetMapping("/registros/{id}/delete")
    public String salonBorrar(@PathVariable("id") long id) {
        repo.eliminarRegistro(id);
        return "redirect:/registros";
    }

}