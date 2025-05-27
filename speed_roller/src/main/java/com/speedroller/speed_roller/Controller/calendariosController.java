package com.speedroller.speed_roller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/calendarios")
public class calendariosController {
    @GetMapping("/estudiantes")
    public String calendarioEstudiantes() {
        return "calendario/horarioEstudiantes";
    }

    @GetMapping("/instructores")
    public String calendarioInstructores () {
        return "calendario/horarioInstructores";
    }


}
