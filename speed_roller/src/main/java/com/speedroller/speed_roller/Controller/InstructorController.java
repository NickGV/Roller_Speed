package com.speedroller.speed_roller.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/actualizarPerfil")
    public String actualizarPerfilInstructor(@ModelAttribute("instructor") Instructor instructor,
                                           Principal principal,
                                           RedirectAttributes redirectAttributes) {
        try {
            // Verificar que el instructor está actualizando su propio perfil
            Instructor existingInstructor = instructorService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
            
            if (existingInstructor.getId() != instructor.getId()) {
                throw new RuntimeException("No autorizado para actualizar este perfil");
            }

            // Mantener el rol y la contraseña originales
            instructor.setRole(existingInstructor.getRole());
            instructor.setPassword(existingInstructor.getPassword());

            instructorService.saveInstructor(instructor);
            redirectAttributes.addFlashAttribute("mensaje", "Perfil actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el perfil: " + e.getMessage());
        }
        
        return "redirect:/instructor/perfil";
    }
}
