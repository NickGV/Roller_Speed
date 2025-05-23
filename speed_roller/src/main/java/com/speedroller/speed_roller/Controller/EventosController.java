package com.speedroller.speed_roller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/eventos")
// Controlador para la página de eventos
public class EventosController {
    @GetMapping("/eventos")
    public String eventos() {
        return "eventos";
    }
}