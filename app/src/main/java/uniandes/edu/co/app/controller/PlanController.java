package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Plan;
import uniandes.edu.co.app.repositorio.PlanRepo;

@Controller
public class PlanController {

    @Autowired
    private PlanRepo planRepository;

    @GetMapping("/planes")
    public String planes(Model model) {

        model.addAttribute("planesconsumo", planRepository.darPlanes());

        return "planes";
    }

    @GetMapping("/planes/new")
    public String planForm(Model model) {
        model.addAttribute("plan", new Plan());
        return "planNuevo";
    }

    @PostMapping("/planes/new/save")
    public String planGuardar(@ModelAttribute Plan plan) {
        planRepository.insertarPlan(plan.getTipo(), plan.getDescripcion());
        return "redirect:/planes";
    }

    @GetMapping("/planes/{id}/edit")
    public String planEditarForm(@PathVariable("id") long id, Model model) {
        Plan plan = planRepository.darPlan(id);
        if (plan != null) {
            model.addAttribute("plan", plan);
            return "planEditar";
        } else {
            return "redirect:/planes";
        }
    }

    @PostMapping("/planes/{id}/edit/save")
    public String planEditarGuardar(@PathVariable("id") long id, @ModelAttribute Plan plan) {
        planRepository.actualizarPlan(((long) id), plan.getTipo(), plan.getDescripcion());
        return "redirect:/planes";
    }

    @GetMapping("/planes/{id}/delete")
    public String planBorrar(@PathVariable("id") long id) {
        planRepository.eliminarPlan(id);
        return "redirect:/planes";
    }

}