package com.speedroller.speed_roller.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedroller.speed_roller.Model.User;
import com.speedroller.speed_roller.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Aquí puedes agregar métodos para manejar las solicitudes relacionadas con los usuarios

    //Obtenemos todos los usuarios
    @GetMapping(value = "/listar")
    public String getAllUsers(Model model) { 
        List<User> usersList = userService.getUsers();
        model.addAttribute("usuariosdb", usersList);
        return "Usuarios";
    }
    
    

    
}
