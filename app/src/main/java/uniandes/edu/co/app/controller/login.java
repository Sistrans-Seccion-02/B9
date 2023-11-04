package uniandes.edu.co.app.controller;

import org.springframework.ui.Model;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.app.modelo.Usuario;
import uniandes.edu.co.app.repositorio.UsuarioRepo;

@Controller
public class login {

    private final UsuarioRepo usuarioRepo;

    @Autowired
    public login(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(Model model, @RequestParam String username, @RequestParam String password) {
        // Check the username and password against the Oracle database
        // Assuming UsuarioRepo.loginUsuario returns a Collection of matching users
        Collection<Usuario> matchingUsers = usuarioRepo.loginUsuario(username, password);

        if (!matchingUsers.isEmpty()) {
            for (Usuario user : matchingUsers) {
                System.out.println("User ID: " + user.getId());
                if (user.getRol().equals("Cliente")) {
                    return "indexCliente";
                }
                if (user.getRol().equals("Recepcionista")) {
                    return "indexRecepcionista";
                }
                if (user.getRol().equals("Empleado")) {
                    return "indexEmpleado";
                }
                if (user.getRol().equals("Administrador")) {
                    return "indexAdmin";
                }
                if (user.getRol().equals("Gerente")) {
                    return "indexGerente";
                }
                if (user.getRol().equals("Gerente HotelAndes")) {
                    return "indexGerenteHA";
                }
            }
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }

        return "login";
    }
}
