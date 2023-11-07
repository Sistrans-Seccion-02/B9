package uniandes.edu.co.app.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Reserva;
import uniandes.edu.co.app.repositorio.ReservaRepo;
import uniandes.edu.co.app.repositorio.ReservaRepo.IndiceOcupacionPorHabitacion;

@Controller
public class ReservaController {

    @Autowired
    private ReservaRepo reservaRepository;

    @GetMapping("/reservas")
    public String reservas(Model model, Integer id) {
        if (id != null) {
            model.addAttribute("reservas", reservaRepository.darReserva(id));

        } else {
            model.addAttribute("reservas", reservaRepository.darReservas());
        }
        return "reservas";
    }

    @GetMapping("/reservas/new")
    public String reservaForm(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "reservaNuevo";
    }

    @PostMapping("/reservas/new/save")
    public String reservaGuardar(@ModelAttribute Reserva reserva) {
        reservaRepository.insertarReserva(reserva.getFecha(), reserva.getFechafinal(), reserva.getPersonas(),
                reserva.getHabtacionid(), reserva.getPlanesid(), reserva.getUsuarioid());
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/{id}/edit")
    public String reservaEditarForm(@PathVariable("id") long id, Model model) {
        Reserva reserva = reservaRepository.darReserva(id);
        if (reserva != null) {
            model.addAttribute("reserva", reserva);
            return "reservaEditar";
        } else {
            return "redirect:/reservas";
        }
    }

    @PostMapping("/reservas/{id}/edit/save")
    public String reservaEditarGuardar(@PathVariable("id") long id, @ModelAttribute Reserva reserva) {
        reservaRepository.actualizarReserva(((long) id), reserva.getFecha(), reserva.getFechafinal(),
                reserva.getPersonas(), reserva.getHabtacionid(), reserva.getPlanesid(), reserva.getUsuarioid());
        return "redirect:/reservas";
    }

    @GetMapping("/reservas/{id}/delete")
    public String reservaBorrar(@PathVariable("id") long id) {
        reservaRepository.eliminarReserva(id);
        return "redirect:/reservas";
    }

   @GetMapping("/reservas/indiceocupacion")
    public String mostrarIndiceOcupacion(Model model) {
        // Obtén los datos del índice de ocupación para el último año
        Collection<IndiceOcupacionPorHabitacion> resultados = reservaRepository.calcularIndiceOcupacionPorHabitacion();

        // Agrega los resultados al modelo para mostrar en la vista
        model.addAttribute("indiceOcupacion", resultados);

        return "indiceOcupacion"; // Debes crear una vista para mostrar los resultados
    }

}