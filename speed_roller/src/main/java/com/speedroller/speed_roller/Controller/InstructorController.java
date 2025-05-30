package com.speedroller.speed_roller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/instructor")
public class InstructorController {

    @GetMapping("/profile")
    public String getInstructorProfile() {
        return "instructor/instructorProfile"; // Retorna la vista del perfil del instructor";
    }
    
    
}
