package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Utileria;
import uniandes.edu.co.app.repositorio.UtileriaRepo;

@Controller
public class UtileriaController {

    @Autowired
    private UtileriaRepo utileriaRepository;

    @GetMapping("/utileria")
    public String utileria(Model model, String nombre) {

        model.addAttribute("utileria", utileriaRepository.darUtileria());

        return "utileria";
    }

    @GetMapping("/utileria/new")
    public String utileriaForm(Model model) {
        model.addAttribute("utileria", new Utileria());
        return "utileriaNuevo";
    }

    @PostMapping("/utileria/new/save")
    public String utileriaGuardar(@ModelAttribute Utileria utileria) {
        utileriaRepository.insertarUtileria(utileria.getNombre(), utileria.getCostoPenalizacion());
        return "redirect:/utileria";
    }

    @GetMapping("/utileria/{id}/edit")
    public String utileriaEditarForm(@PathVariable("id") long id, Model model) {
        Utileria utileria = utileriaRepository.darUtileriaPorId(id);
        if (utileria != null) {
            model.addAttribute("utileria", utileria);
            return "utileriaEditar";
        } else {
            return "redirect:/utileria";
        }
    }

    @PostMapping("/utileria/{id}/edit/save")
    public String utileriaEditarGuardar(@PathVariable("id") long id, @ModelAttribute Utileria utileria) {
        utileriaRepository.actualizarUtileria(((long) id), utileria.getNombre(), utileria.getCostoPenalizacion());
        return "redirect:/utileria";
    }

    @GetMapping("/utileria/{id}/delete")
    public String utileriaBorrar(@PathVariable("id") long id) {
        utileriaRepository.eliminarUtileria(id);
        return "redirect:/utileria";
    }

}