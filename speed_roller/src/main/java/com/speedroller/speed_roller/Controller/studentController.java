package com.speedroller.speed_roller.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.service.StudentService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/estudiantes")   
public class StudentController {

    @Autowired
    private StudentService estudianteService;

    @RequestMapping(value = "/listar") 
    public String getAllStudens(Model model) {
        List<Student> studentsList = estudianteService.getStudents();
        model.addAttribute("Estudiantesdb", studentsList); 
        return "estudiantes/listStudents";
    }
    
     @GetMapping("/perfil")
    public String mostrarPerfilEstudiante(Model model, Principal principal) {
    Student student = estudianteService.findByEmail(principal.getName())
        .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    model.addAttribute("Estudiante", student);
    return "estudiantes/studentProfile";
}
    
    
}
