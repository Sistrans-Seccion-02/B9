package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.ServicioConsumo;
import uniandes.edu.co.app.repositorio.ServicioConsumoRepo;

@Controller
public class ServicioConsumoController {

    @Autowired
    private ServicioConsumoRepo servicioConsumoRepository;

    @GetMapping("/serviciosconsumo")
    public String serviciosConsumo(Model model, String nombre) {

        if (nombre != null && !nombre.equals("")) {
            model.addAttribute("serviciosconsumo", servicioConsumoRepository.darServicioConsumoPorNombre(nombre));
        } else {
            model.addAttribute("serviciosconsumo", servicioConsumoRepository.darServiciosConsumo());
        }

        return "serviciosconsumo";
    }

    @GetMapping("/serviciosconsumo/new")
    public String servicioConsumoForm(Model model) {
        model.addAttribute("servicioConsumo", new ServicioConsumo());
        return "servicioConsumoNuevo";
    }

    @PostMapping("/serviciosconsumo/new/save")
    public String servicioConsumoGuardar(@ModelAttribute ServicioConsumo servicio) {
        servicioConsumoRepository.insertarServicioConsumo(servicio.getDescripcion(), servicio.getCosto(),
                servicio.getFecha(), servicio.getIdhabitacion(), servicio.getProducto());
        return "redirect:/serviciosconsumo";
    }

    @GetMapping("/serviciosconsumo/{id}/edit")
    public String servicioConsumoEditarForm(@PathVariable("id") long id, Model model) {
        ServicioConsumo servicio = servicioConsumoRepository.darServicioConsumo(id);
        if (servicio != null) {
            model.addAttribute("servicioConsumo", servicio);
            return "servicioConsumoEditar";
        } else {
            return "redirect:/serviciosconsumo";
        }
    }

    @PostMapping("/serviciosconsumo/{id}/edit/save")
    public String servicioConsumoEditarGuardar(@PathVariable("id") long id, @ModelAttribute ServicioConsumo servicio) {
        servicioConsumoRepository.actualizarServicioConsumo(((long) id), servicio.getDescripcion(), servicio.getCosto(),
                servicio.getFecha(), servicio.getIdhabitacion(), servicio.getProducto());
        return "redirect:/serviciosconsumo";
    }

    @GetMapping("/serviciosconsumo/{id}/delete")
    public String servicioConsumoBorrar(@PathVariable("id") long id) {
        servicioConsumoRepository.eliminarServicioConsumo(id);
        return "redirect:/serviciosconsumo";
    }

}