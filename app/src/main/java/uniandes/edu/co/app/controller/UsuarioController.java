package uniandes.edu.co.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.app.modelo.Usuario;
import uniandes.edu.co.app.repositorio.UsuarioRepo;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepo usuarioRepository;

    @GetMapping("/usuarios")
    public String usuarios(Model model, String nombre) {

        if (nombre != null && !nombre.equals("")) {
            model.addAttribute("usuarios", usuarioRepository.darUsuariosPorNombre(nombre));
        } else {
            model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        }

        return "usuarios";
    }

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.insertarUsuario(usuario.getNombre(), usuario.getDocumento(), usuario.getTipoDocumento(),
                usuario.getRol(), usuario.getoCorreo(), usuario.getContrasena());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/edit")
    public String usuarioEditarForm(@PathVariable("id") long id, Model model) {
        Usuario usuario = usuarioRepository.darUsuario(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/usuarios/{id}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("id") long id, @ModelAttribute Usuario usuario) {
        usuarioRepository.actualizarUsuario(((long) id), usuario.getNombre(), usuario.getDocumento(),
                usuario.getTipoDocumento(),
                usuario.getRol(), usuario.getoCorreo(), usuario.getContrasena());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/delete")
    public String usuarioBorrar(@PathVariable("id") long id) {
        usuarioRepository.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

}