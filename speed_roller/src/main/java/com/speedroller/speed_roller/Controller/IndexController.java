package com.speedroller.speed_roller.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
@Tag(name = "Inicio", description = "Controlador para la página principal del sistema")
public class IndexController {
    @Operation(summary = "Mostrar página de inicio", description = "Muestra la página principal de la aplicación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Página de inicio mostrada correctamente")
    })
    @GetMapping("home")
    public String index() {
        return "index";
    }
}
