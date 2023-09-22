package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Factura;
import uniandes.edu.co.app.repositorio.FacturaRepo;

@Controller
public class FacturaController {

    @Autowired
    private FacturaRepo facturaRepository;

    @GetMapping("/facturas")
    public String facturas(Model model, String nombre) {

        model.addAttribute("facturas", facturaRepository.darFacturas());
        return "facturas";
    }

    @GetMapping("/facturas/new")
    public String facturaForm(Model model) {
        model.addAttribute("factura", new Factura());
        return "facturaNuevo";
    }

    @PostMapping("/facturas/new/save")
    public String facturaGuardar(@ModelAttribute Factura factura) {
        facturaRepository.insertarFactura(factura.getPrecioTotal());
        return "redirect:/facturas";
    }

    @GetMapping("/facturas/{id}/edit")
    public String bebedorEditarForm(@PathVariable("id") long id, Model model) {
        Factura factura = facturaRepository.darFactura(id);
        if (factura != null) {
            model.addAttribute("factura", factura);
            return "facturaEditar";
        } else {
            return "redirect:/facturas";
        }
    }

    @PostMapping("/facturas/{id}/edit/save")
    public String facturaEditarGuardar(@PathVariable("id") long id, @ModelAttribute Factura factura) {
        facturaRepository.actualizarFactura(((long) id), factura.getPrecioTotal());
        return "redirect:/facturas";
    }

    @GetMapping("/facturas/{id}/delete")
    public String facturaBorrar(@PathVariable("id") long id) {
        facturaRepository.eliminarFactura(id);
        return "redirect:/facturas";
    }

}