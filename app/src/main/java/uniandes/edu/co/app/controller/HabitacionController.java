package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Habitacion;
import uniandes.edu.co.app.repositorio.HabitacionRepo;

@Controller
public class HabitacionController {

    @Autowired
    private HabitacionRepo habitacionRepository;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model, String nombre) {

        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());

        return "habitaciones";
    }

    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        return "habitacionNuevo";
    }

    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(@ModelAttribute Habitacion habitacion) {
        habitacionRepository.insertarHabitacion(habitacion.getCapacidad(), habitacion.getTipo(),
                habitacion.getDotacion(), habitacion.getPrecioNoche(), habitacion.getConsumoExtra());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/edit")
    public String habitacionEditarForm(@PathVariable("id") long id, Model model) {
        Habitacion habitacion = habitacionRepository.darHabitacion(id);
        if (habitacion != null) {
            model.addAttribute("habitacion", habitacion);
            return "habitacionEditar";
        } else {
            return "redirect:/habitaciones";
        }
    }

    @PostMapping("/habitaciones/{id}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("id") long id, @ModelAttribute Habitacion habitacion) {
        habitacionRepository.actualizarHabitacion(((long) id), habitacion.getCapacidad(), habitacion.getTipo(),
                habitacion.getDotacion(), habitacion.getPrecioNoche(), habitacion.getConsumoExtra());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/delete")
    public String habitacionBorrar(@PathVariable("id") long id) {
        habitacionRepository.eliminarHabitacion(id);
        return "redirect:/habitaciones";
    }

}