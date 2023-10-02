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

import uniandes.edu.co.app.modelo.ServicioConsumo;
import uniandes.edu.co.app.repositorio.ServicioConsumoRepo;

@Controller
public class ServicioConsumoController {

    @Autowired
    private ServicioConsumoRepo servicioConsumoRepository;

    @GetMapping("/serviciosconsumo")
    public String serviciosConsumo(Model model, Integer id) {

        if (id != null) {
            model.addAttribute("serviciosconsumo", servicioConsumoRepository.darServicioConsumo(id));
        } else {
            model.addAttribute("serviciosconsumo", servicioConsumoRepository.darServiciosConsumo());
        }

        return "serviciosconsumo";
    }

    @GetMapping("/serviciosconsumo/new")
    public String servicioConsumoForm(Model model) {
        model.addAttribute("consumo", new ServicioConsumo());
        return "serviciosconsumoNuevo";
    }

    @PostMapping("/serviciosconsumo/new/save")
    public String servicioConsumoGuardar(@ModelAttribute ServicioConsumo servicio) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDada = dateFormat.parse(servicio.getFecha());
            java.sql.Date sqlfecha = new java.sql.Date(fechaDada.getTime());

            servicioConsumoRepository.insertarServicioConsumo(
                    servicio.getDescripcion(),
                    servicio.getCosto(),
                    sqlfecha,
                    servicio.getIdhabitacion(),
                    servicio.getIdproducto());

            return "redirect:/serviciosconsumo";
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/serviciosconsumo/{id}/edit")
    public String servicioConsumoEditarForm(@PathVariable("id") long id, Model model) {
        ServicioConsumo servicio = servicioConsumoRepository.darServicioConsumo(id);
        if (servicio != null) {
            model.addAttribute("consumo", servicio);
            return "serviciosconsumoEditar";
        } else {
            return "redirect:/serviciosconsumo";
        }
    }

    @PostMapping("/serviciosconsumo/{id}/edit/save")
    public String serviciosConsumoEditarGuardar(@PathVariable("id") long id, @ModelAttribute ServicioConsumo servicio) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = dateFormat.parse(servicio.getFecha());

            java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());

            servicioConsumoRepository.actualizarServicioConsumo(id, servicio.getDescripcion(),
                    servicio.getCosto(), sqlFecha, servicio.getIdhabitacion(),
                    servicio.getIdproducto());

            return "redirect:/serviciosconsumo";
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/serviciosconsumo/{id}/delete")
    public String servicioConsumoBorrar(@PathVariable("id") long id) {
        servicioConsumoRepository.eliminarServicioConsumo(id);
        return "redirect:/serviciosconsumo";
    }

}