package com.speedroller.speed_roller.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Eventos", description = "Controlador para la visualización de eventos de la escuela")
@Controller
@RequestMapping("/")
public class EventsController {
    @Operation(summary = "Mostrar página de eventos", description = "Muestra la página con los eventos de la escuela.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Página de eventos mostrada correctamente")
    })
    @GetMapping("/eventos")
    public String eventos() {
        return "events";
    }
}