package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Spa;
import uniandes.edu.co.app.repositorio.SpaRepo;

@Controller
public class SpaController {

    @Autowired
    private SpaRepo spaRepository;

    @GetMapping("/spas")
    public String spas(Model model, String nombre) {

        model.addAttribute("spas", spaRepository.darSpas());

        return "spas";
    }

    @GetMapping("/spas/new")
    public String spaForm(Model model) {
        model.addAttribute("spa", new Spa());
        return "spaNuevo";
    }

    @PostMapping("/spas/new/save")
    public String spaGuardar(@ModelAttribute Spa spa) {
        spaRepository.insertarSpa(
            spa.getDuracion(), 
            spa.getCosto(), 
            spa.getCapacidad(), 
            spa.getDisponibilidad());
        return "redirect:/spas";
    }

    @GetMapping("/spas/{id}/edit")
    public String spaEditarForm(@PathVariable("id") long id, Model model) {
        Spa spa = spaRepository.darSpa(id);
        if (spa != null) {
            model.addAttribute("spa", spa);
            return "spaEditar";
        } else {
            return "redirect:/spas";
        }
    }

    @PostMapping("/spas/{id}/edit/save")
    public String spaEditarGuardar(@PathVariable("id") long id, @ModelAttribute Spa spa) {
        spaRepository.actualizarSpas(((long) id), 
            spa.getDuracion(), 
            spa.getCosto(), 
            spa.getCapacidad(), 
            spa.getDisponibilidad());
        return "redirect:/spas";
    }

    @GetMapping("/spas/{id}/delete")
    public String spaBorrar(@PathVariable("id") long id) {
        spaRepository.eliminarSpa(id);
        return "redirect:/spas";
    }

}