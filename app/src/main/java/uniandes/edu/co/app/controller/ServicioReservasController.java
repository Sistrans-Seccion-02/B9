package uniandes.edu.co.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.app.modelo.ServicioReservas;
import uniandes.edu.co.app.repositorio.ServicioReservasRepo;
import uniandes.edu.co.app.repositorio.ServicioReservasRepo.RespuestaDineroRecolectadoPorServicio;
import uniandes.edu.co.app.repositorio.ServicioReservasRepo.respuestaFrecuenciaServicios;

@Controller
public class ServicioReservasController {

    @Autowired
    private ServicioReservasRepo repo;

    

    @GetMapping("/servicioreservas")
    public String serviciosreservas(Model model, Integer id) {
        if (id != null) {
            model.addAttribute("servicioreservas", repo.darReserva(id));
            
        } else {
            model.addAttribute("servicioreservas", repo.darReservas());
        }
        return "servicioreservas";
    }

    @GetMapping("/servicioreservas/frecuencia")
    public String serviciosFrecuencia(Model model) {
        Collection<respuestaFrecuenciaServicios> frecuenciaServicios = repo.frecuenciaServicios();
        model.addAttribute("servicioreservas", frecuenciaServicios);
        return "servicioreservasFrecuencia";
    }

     @GetMapping("/dineroRecolectadoPorServicios")
    public String mostrarDineroRecolectadoPorServicios(Model model) {
        Collection<RespuestaDineroRecolectadoPorServicio> resultados = repo.obtenerDineroRecolectadoPorDiferentesServicios();
        // Agrega los resultados al modelo para mostrar en la vista
        model.addAttribute("dineroRecolectadoPorServicios", resultados);

        return "dineroRecolectadoPorServicios"; // Debes crear una vista para mostrar los resultados
    }

    @GetMapping("/servicioreservas/populares")
    public String mostrarServiciosPopulares(Model model,
        @RequestParam(name = "fechainicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechainicio,
        @RequestParam(name = "fechafin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechafin) {
    
        // Verifica si los parámetros se proporcionaron en la solicitud
        if (fechainicio != null && fechafin != null) {
            java.sql.Date sqlfechaI = new java.sql.Date(fechainicio.getTime());
            java.sql.Date sqlfechaF = new java.sql.Date(fechafin.getTime());
    
            Collection<ServicioReservasRepo.RespuestaServiciosPopulares> serviciosPopulares = repo.serviciosPopulares(sqlfechaI, sqlfechaF);
            model.addAttribute("serviciosPopulares", serviciosPopulares);
        }
    
        return "serviciosPopulares"; // Debes crear una vista para mostrar los resultados
    }
    

    @GetMapping("/servicioreservas/new")
    public String reservaForm(Model model) {
        model.addAttribute("reserva", new ServicioReservas());
        return "servicioreservasNuevo";
    }

    @PostMapping("/servicioreservas/new/save")
    public String reservaGuardar(@ModelAttribute ServicioReservas res) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaInicial = dateFormat.parse(res.getFechainicial());
        Date fechaFinal = dateFormat.parse(res.getFechafinal());


        java.sql.Date sqlFechaInicial = new java.sql.Date(fechaInicial.getTime());
        java.sql.Date sqlFechaFinal = new java.sql.Date(fechaFinal.getTime());

        Double precio = res.getPrecio();
        repo.insertarReservas(
            sqlFechaInicial,
            sqlFechaFinal,
            res.getIdhabitacion(),
            res.getTipo(),
            precio);

        return "redirect:/servicioreservas";
    } catch (ParseException e) {
        e.printStackTrace();
        return null;}
    }


    @GetMapping("/servicioreservas/{id}/edit")
    public String reservaEditarForm(@PathVariable("id") long id, Model model) {
        ServicioReservas reserva = repo.darReserva(id);
        if (reserva != null) {
            model.addAttribute("reserva", reserva);
            return "servicioreservasEditar";
        } else {
            return "redirect:/servicioreservas";
        }
    }

    @PostMapping("/servicioreservas/{id}/edit/save")
    public String reservaEditarGuardar(@PathVariable("id") long id, @ModelAttribute ServicioReservas res) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date fechaInicial = dateFormat.parse(res.getFechainicial());
        Date fechaFinal = dateFormat.parse(res.getFechafinal());

        java.sql.Date sqlFechaInicial = new java.sql.Date(fechaInicial.getTime());
        java.sql.Date sqlFechaFinal = new java.sql.Date(fechaFinal.getTime());

        repo.actualizarReservas(id, sqlFechaInicial, sqlFechaFinal, res.getIdhabitacion(), res.getTipo(), res.getPrecio());

        return "redirect:/servicioreservas";
    } catch (ParseException e) {
        e.printStackTrace();
        return null;
    }
}

    @GetMapping("/servicioreservas/{id}/delete")
    public String reservaBorrar(@PathVariable("id") long id) {
        repo.eliminarReserva(id);
        return "redirect:/servicioreservas";
    }

}