package uniandes.edu.co.app.controller;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.app.modelo.Usuario;
import uniandes.edu.co.app.repositorio.UsuarioRepo;
import uniandes.edu.co.app.repositorio.UsuarioRepo.RespuestaEncontrarBuenosClientes;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepo usuarioRepository;

    @GetMapping("/usuarios")
    public String usuarios(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id != null) {
            // Realizar la búsqueda por ID aquí y asignar el resultado a "usuarios"
            // usuarioRepository.darUsuario(id);
            model.addAttribute("usuarios", usuarioRepository.darUsuario(id));
            
        } else {
            // Obtener todos los usuarios
            model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        }
        return "usuarios";
    }

    @GetMapping("/usuarios/rol")
    public String usuariosPorRol(@RequestParam(name = "rol", required = true) String rol, Model model) {
        if (rol.isEmpty()) {
            model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        } else{
            model.addAttribute("usuarios", usuarioRepository.darUsuariosPorRol(rol));
        }
        return "usuarios";
    }

    @GetMapping("/usuarios/buenosClientesEstadia")
    public String buenosClientesEstadia(Model model){
        Collection<RespuestaEncontrarBuenosClientes> buenosClientesEstadia = usuarioRepository.encontrarBuenosClientes();
        model.addAttribute("usuarios", buenosClientesEstadia);
        return "buenosClientesEstadia";
    }

    @GetMapping("/usuarios/mayorConsumo")
    public String mayorConsumo(Model model){
        Collection<UsuarioRepo.RespuestaEncontrarMayorConsumo> mayorConsumo = usuarioRepository.encontrarMayorConsumo();
        model.addAttribute("usuarios", mayorConsumo);
        return "mayorConsumo";
    }

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.insertarUsuario(usuario.getNombre(), usuario.getDocumento(), usuario.getTipodocumento(),
                usuario.getRol(), usuario.getCorreo(), usuario.getContrasena());
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
                usuario.getTipodocumento(),
                usuario.getRol(), usuario.getCorreo(), usuario.getContrasena());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/delete")
    public String usuarioBorrar(@PathVariable("id") long id) {
        usuarioRepository.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

}