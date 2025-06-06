package com.speedroller.speed_roller.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Login", description = "Controlador para la autenticación de usuarios")
@Controller
@RequestMapping("/")
public class LoginController {

    @Operation(summary = "Mostrar formulario de login", description = "Muestra el formulario de inicio de sesión para usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulario de login mostrado correctamente")
    })
    @GetMapping("login")
    public String mostrarLogin() {
        return "login";
    }
}