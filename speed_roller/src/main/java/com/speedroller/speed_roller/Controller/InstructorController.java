package com.speedroller.speed_roller.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.service.InstructorService;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

   @GetMapping("/perfil")
public String mostrarPerfilInstructor(Model model, Principal principal) {
    Instructor instructor = instructorService.findByEmail(principal.getName())
        .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
    model.addAttribute("instructor", instructor);
    return "instructor/instructorProfile";
}
    
    
}
