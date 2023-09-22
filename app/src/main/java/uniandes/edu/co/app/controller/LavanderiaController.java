package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Lavanderia;
import uniandes.edu.co.app.repositorio.LavanderiaRepo;

@Controller
public class LavanderiaController {

    @Autowired
    private LavanderiaRepo lavanderiaRepository;

    @GetMapping("/lavanderias")
    public String lavanderias(Model model, String nombre) {

        model.addAttribute("lavanderias", lavanderiaRepository.darLavanderias());

        return "lavanderias";
    }

    @GetMapping("/lavanderias/new")
    public String lavanderiaForm(Model model) {
        model.addAttribute("lavanderia", new Lavanderia());
        return "lavanderiaNuevo";
    }

    @PostMapping("/lavanderias/new/save")
    public String lavanderiaGuardar(@ModelAttribute Lavanderia lavanderia) {
        lavanderiaRepository.insertarLavanderia(lavanderia.getTipoPrenda(), lavanderia.getNumeroPrendas(),
                lavanderia.getParesZapatos(), lavanderia.getCosto());
        return "redirect:/lavanderias";
    }

    @GetMapping("/lavanderias/{id}/edit")
    public String lavanderiaEditarForm(@PathVariable("id") long id, Model model) {
        Lavanderia lavanderia = lavanderiaRepository.darLavanderia(id);
        if (lavanderia != null) {
            model.addAttribute("lavanderia", lavanderia);
            return "lavanderiaEditar";
        } else {
            return "redirect:/lavanderias";
        }
    }

    @PostMapping("/lavanderias/{id}/edit/save")
    public String lavanderiaEditarGuardar(@PathVariable("id") long id, @ModelAttribute Lavanderia lavanderia) {
        lavanderiaRepository.actualizarLavanderia(((long) id), lavanderia.getTipoPrenda(),
                lavanderia.getNumeroPrendas(),
                lavanderia.getParesZapatos(), lavanderia.getCosto());
        return "redirect:/lavanderias";
    }

    @GetMapping("/lavanderias/{id}/delete")
    public String lavanderiaBorrar(@PathVariable("id") long id) {
        lavanderiaRepository.eliminarLavanderia(id);
        return "redirect:/lavanderias";
    }

}