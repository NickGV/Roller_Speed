package com.speedroller.speed_roller.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/actualizarPerfil")
    public String actualizarPerfilEstudiante(@ModelAttribute("Estudiante") Student student, 
                                           Principal principal,
                                           RedirectAttributes redirectAttributes) {
        try {
            Student existingStudent = estudianteService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
            
            if (existingStudent.getId() != student.getId()) {
                throw new RuntimeException("No autorizado para actualizar este perfil");
            }

            student.setRole(existingStudent.getRole());
            student.setPassword(existingStudent.getPassword());

            estudianteService.saveStudent(student);
            redirectAttributes.addFlashAttribute("mensaje", "Perfil actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el perfil: " + e.getMessage());
        }
        
        return "redirect:/estudiantes/perfil";
    }
}
