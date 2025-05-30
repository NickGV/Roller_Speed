package com.speedroller.speed_roller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/dashboard")
    public String showDashboard() {
        return "administrador/dashboard"; // Retorna la vista del dashboard de administración
    }
/* 
    @RequestMapping("/users")
    public String manageUsers() {
        return "admin/manageUsers"; // Retorna la vista para gestionar usuarios
    }

    @RequestMapping("/settings")
    public String settings() {
        return "admin/settings"; // Retorna la vista de configuración del administrador
    }
    */
}
