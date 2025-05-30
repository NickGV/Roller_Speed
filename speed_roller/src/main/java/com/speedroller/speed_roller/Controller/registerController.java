package com.speedroller.speed_roller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.service.InstructorService;
import com.speedroller.speed_roller.service.StudentService;


@Controller
@RequestMapping("/registro")
public class RegisterController {

    @Autowired
    private StudentService estudianteService;

    @GetMapping("/nuevoEstudiante")
    public String mostrarFormularioNuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new Student());
        return "registro/studentRegister"; 
    }

    @PostMapping("/guardarEstudiante")
    public String guardarEstudiante(@ModelAttribute("estudiante") Student estudiante) {
        estudianteService.saveStudent(estudiante);
        return "redirect:/registro/studentRegister";
    }
    @Autowired
    private InstructorService instructorService;

    @GetMapping("/nuevoInstructor")
    public String mostrarFormularioNuevoInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "registro/instructorRegister"; 
    }
    

    @PostMapping("/guardarInstructor")
    public String guardarInstructor(@ModelAttribute("instructor") Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/registro/instructorRegister";
        
    }
    
}
