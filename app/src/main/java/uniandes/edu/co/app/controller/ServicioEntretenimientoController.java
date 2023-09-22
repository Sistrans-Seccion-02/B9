package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.ServicioEntretenimiento;
import uniandes.edu.co.app.repositorio.ServicioEntretenimientoRepo;

@Controller
public class ServicioEntretenimientoController {

    @Autowired
    private ServicioEntretenimientoRepo servicioEntretenimientoRepository;

    @GetMapping("/serviciosentretenimiento")
    public String serviciosEntrenenimiento(Model model, Integer id) {

        if (id != null && !id.equals(0)) {
            model.addAttribute("serviciosentretenimiento",
                    servicioEntretenimientoRepository.darServicioEntretenimiento(id));
        } else {
            model.addAttribute("serviciosentretenimiento",
                    servicioEntretenimientoRepository.darServiciosEntretenimiento());
        }

        return "serviciosentretenimiento";
    }

    @GetMapping("/serviciosentretenimiento/new")
    public String bebedorForm(Model model) {
        model.addAttribute("servicioentretenimiento", new ServicioEntretenimiento());
        return "servicioentretenimientoNuevo";
    }

    @PostMapping("/serviciosentretenimiento/new/save")
    public String servicioEntretenimientoGuardar(@ModelAttribute ServicioEntretenimiento servicio) {
        servicioEntretenimientoRepository.insertarServicioEntretenimiento(servicio.getHorarioServicio(),
                servicio.getCapacidad());
        return "redirect:/serviciosentretenimiento";
    }

    @GetMapping("/serviciosentretenimiento/{id}/edit")
    public String servicioEntretenimientoEditarForm(@PathVariable("id") long id, Model model) {
        ServicioEntretenimiento servicio = servicioEntretenimientoRepository.darServicioEntretenimiento(id);
        if (servicio != null) {
            model.addAttribute("servicioentretenimiento", servicio);
            return "servicioentretenimientoEditar";
        } else {
            return "redirect:/serviciosentretenimiento";
        }
    }

    @PostMapping("/serviciosentretenimiento/{id}/edit/save")
    public String servicioEntretenimientoEditarGuardar(@PathVariable("id") long id,
            @ModelAttribute ServicioEntretenimiento servicio) {
        servicioEntretenimientoRepository.actualizarServicioEntretenimiento(((long) id), servicio.getHorarioServicio(),
                servicio.getCapacidad());
        return "redirect:/serviciosentretenimiento";
    }

    @GetMapping("/serviciosentretenimiento/{id}/delete")
    public String servicioEntretenimientoBorrar(@PathVariable("id") long id) {
        servicioEntretenimientoRepository.eliminarServicioEntretenimiento(id);
        return "redirect:/serviciosentretenimiento";
    }

}
