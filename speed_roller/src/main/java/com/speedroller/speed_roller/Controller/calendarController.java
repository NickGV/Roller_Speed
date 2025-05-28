package com.speedroller.speed_roller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/calendarios")
public class calendarController {
    @GetMapping("/estudiantes")
    public String calendarioEstudiantes() {
        return "calendario/studentSchedule";
    }

    @GetMapping("/instructores")
    public String calendarioInstructores () {
        return "calendario/instructorSchedule";
    }
}

