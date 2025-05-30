package com.speedroller.speed_roller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.service.InstructorService;
import com.speedroller.speed_roller.service.StudentService;

@Controller
@RequestMapping("/registro")
public class RegisterController {

    @Autowired
    private StudentService estudianteService;

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/nuevoEstudiante")
    public String mostrarFormularioNuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new Student());
        return "registro/studentRegister"; 
    }

    @PostMapping("/guardarEstudiante")
    public String guardarEstudiante(@ModelAttribute("estudiante") Student estudiante, 
                                  RedirectAttributes redirectAttributes) {
        try {
            // Verificar si el email ya está registrado
            if (estudianteService.findByEmail(estudiante.getEmail()).isPresent()) {
                redirectAttributes.addFlashAttribute("error", "El email ya está registrado");
                return "redirect:/registro/nuevoEstudiante";
            }

            // Establecer el rol por defecto
            estudiante.setRole("ESTUDIANTE");

            // Guardar el estudiante
            estudianteService.saveStudent(estudiante);
            
            redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso. Por favor inicia sesión.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "redirect:/registro/nuevoEstudiante";
        }
    }

    @GetMapping("/nuevoInstructor")
    public String mostrarFormularioNuevoInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "registro/instructorRegister"; 
    }

    @PostMapping("/guardarInstructor")
    public String guardarInstructor(@ModelAttribute("instructor") Instructor instructor,
                                  RedirectAttributes redirectAttributes) {
        try {
            // Verificar si el email ya está registrado
            if (instructorService.findByEmail(instructor.getEmail()).isPresent()) {
                redirectAttributes.addFlashAttribute("error", "El email ya está registrado");
                return "redirect:/registro/nuevoInstructor";
            }

            // Establecer el rol por defecto
            instructor.setRole("INSTRUCTOR");

            // Guardar el instructor
            instructorService.saveInstructor(instructor);
            
            redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso. Por favor inicia sesión.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "redirect:/registro/nuevoInstructor";
        }
    }
}
