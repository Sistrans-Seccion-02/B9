package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.ReservaSalon;
import uniandes.edu.co.app.repositorio.ReservaSalonRepo;

@Controller
public class ReservaSalonController {

    @Autowired
    private ReservaSalonRepo reservaSalonRepo;

    @GetMapping("/reservaSalon")
    public String reservaSalon(Model model, String nombre) {

        model.addAttribute("reservasSalon", reservaSalonRepo.darReservasSalon());

        return "reservaSalon";
    }

    @GetMapping("/reservasSalon/new")
    public String reservaSalonForm(Model model) {
        model.addAttribute("reservaSalon", new ReservaSalon());
        return "reservaSalonNuevo";
    }

    @PostMapping("/reservasSalon/new/save")
    public String reservaSalonGuardar(@ModelAttribute ReservaSalon reservaSalon) {
        reservaSalonRepo.insertarReservaSalon(reservaSalon.getDia(), reservaSalon.getHora(),
                reservaSalon.getDuracion());
        return "redirect:/reservasSalon";
    }

    @GetMapping("/reservasSalon/{id}/edit")
    public String reservaSalonEditarForm(@PathVariable("id") long id, Model model) {
        ReservaSalon reservaSalon = reservaSalonRepo.darReservaSalon(id);
        if (reservaSalon != null) {
            model.addAttribute("reservaSalon", reservaSalon);
            return "reservaSalonEditar";
        } else {
            return "redirect:/reservasSalon";
        }
    }

    @PostMapping("/reservasSalon/{id}/edit/save")
    public String reservaSalonEditarGuardar(@PathVariable("id") long id, @ModelAttribute ReservaSalon reservaSalon) {
        reservaSalonRepo.actualizarReservaSalon(((long) id), reservaSalon.getDia(), reservaSalon.getHora(),
                reservaSalon.getDuracion());
        return "redirect:/reservasSalon";
    }

    @GetMapping("/reservasSalon/{id}/delete")
    public String reservaSalonBorrar(@PathVariable("id") long id) {
        reservaSalonRepo.eliminarReservaSalon(id);
        return "redirect:/reservasSalon";
    }

}