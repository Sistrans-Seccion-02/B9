package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Salon;
import uniandes.edu.co.app.repositorio.SalonRepo;

@Controller
public class SalonController {

    @Autowired
    private SalonRepo salonRepository;

    @GetMapping("/salones")
    public String salones(Model model, String nombre) {

        model.addAttribute("salones", salonRepository.darSalones());

        return "salones";
    }

    @GetMapping("/salones/new")
    public String salonForm(Model model) {
        model.addAttribute("salon", new Salon());
        return "salonNuevo";
    }

    @PostMapping("/salones/new/save")
    public String bebedorGuardar(@ModelAttribute Salon salon) {
        salonRepository.insertarSalon(salon.getCapacidad(), salon.getTipo(), salon.getCostoHora(), salon.getLimpieza(),
                salon.getEquipos());
        return "redirect:/salones";
    }

    @GetMapping("/salones/{id}/edit")
    public String salonEditarForm(@PathVariable("id") long id, Model model) {
        Salon salon = salonRepository.darSalon(id);
        if (salon != null) {
            model.addAttribute("salon", salon);
            return "salonEditar";
        } else {
            return "redirect:/salones";
        }
    }

    @PostMapping("/salones/{id}/edit/save")
    public String salonEditarGuardar(@PathVariable("id") long id, @ModelAttribute Salon salon) {
        salonRepository.actualizarSalon(((long) id), salon.getCapacidad(), salon.getTipo(), salon.getCostoHora(),
                salon.getLimpieza(),
                salon.getEquipos());
        return "redirect:/salones";
    }

    @GetMapping("/salones/{id}/delete")
    public String salonBorrar(@PathVariable("id") long id) {
        salonRepository.eliminarSalon(id);
        return "redirect:/salones";
    }

}