package com.speedroller.speed_roller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EventosController {
    @GetMapping("/eventos")
    public String eventos() {
        return "eventos";
    }
}