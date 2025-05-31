package com.speedroller.speed_roller.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import com.speedroller.speed_roller.model.ClassSchedule;
//import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.service.ClassScheduleService;
import com.speedroller.speed_roller.service.InstructorService;
import com.speedroller.speed_roller.service.StudentService;

@Controller
@RequestMapping("/horarios")
public class ClassScheduleController {

    @Autowired
    private ClassScheduleService scheduleService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private StudentService studentService;

    // Mostrar todas las clases
    @GetMapping
    public String listarClases(Model model) {
        List<ClassSchedule> clases = scheduleService.getAllClasses();
        model.addAttribute("clases", clases);
        return "administrador/listarClases";
    }

    // Formulario para crear una nueva clase
    @GetMapping("/nueva-clase")
    public String mostrarFormularioNuevaClase(Model model) {
        model.addAttribute("clase", new ClassSchedule());
        model.addAttribute("instructores", instructorService.getInstructors());
        model.addAttribute("estudiantes", studentService.getStudents());
        return "administrador/nuevaClase";
    }

    // Guardar una nueva clase
    @PostMapping("/guardar-clase")
    public String guardarClase(@ModelAttribute("clase") ClassSchedule clase) {
        scheduleService.saveClass(clase);
        return "redirect:/horarios";
    }

    // Asignar instructor a una clase
    @PostMapping("/asignar-instructor")
    public String asignarInstructor(@RequestParam("classId") Long classId, @RequestParam("instructorId") Long instructorId) {
        scheduleService.assignInstructorToClass(classId, instructorId);
        return "redirect:/horarios";
    }

    // Agregar alumno a una clase
    @PostMapping("/agregar-alumno")
    public String agregarAlumno(@RequestParam("classId") Long classId, @RequestParam("studentId") Long studentId) {
        scheduleService.addStudentToClass(classId, studentId);
        return "redirect:/horarios";
    }

    // Vista del horario para estudiantes
    @GetMapping("/estudiantes")
    public String showStudentSchedule(Model model, Authentication authentication) {
        List<ClassSchedule> clases = scheduleService.getAllClasses();
        
        // Filtrar clases basadas en la experiencia del estudiante
        if (authentication != null) {
            try {
                Student student = studentService.findByEmail(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
                
                // Obtener el nivel de experiencia del estudiante
                String experienciaEstudiante = student.getExperiencia();
                
                // Filtrar clases que coincidan con el nivel de experiencia del estudiante
                clases = clases.stream()
                    .filter(clase -> clase.getNivel().equalsIgnoreCase(experienciaEstudiante) ||
                           // Si la experiencia es "Avanzado", mostrar todos los niveles
                           (experienciaEstudiante.equalsIgnoreCase("Avanzado")) ||
                           // Si la experiencia es "Intermedio", mostrar niveles Principiante e Intermedio
                           (experienciaEstudiante.equalsIgnoreCase("Intermedio") && 
                            (clase.getNivel().equalsIgnoreCase("Principiante") || 
                             clase.getNivel().equalsIgnoreCase("Intermedio"))))
                    .toList();
                
                model.addAttribute("experienciaEstudiante", experienciaEstudiante);
            } catch (Exception e) {
                // Si hay algún error, mostrar todas las clases
                System.out.println("Error al obtener estudiante: " + e.getMessage());
            }
        }

        model.addAttribute("clases", clases);
        model.addAttribute("totalClases", clases.size());
        model.addAttribute("niveles", Arrays.asList("Principiante", "Intermedio", "Avanzado"));
        
        return "calendario/studentSchedule";
    }

    // Vista del horario para instructores
    @GetMapping("/instructores")
    public String showInstructorSchedule(Model model, Authentication authentication) {
        List<ClassSchedule> clases = scheduleService.getAllClasses();

        // Comentamos temporalmente el filtrado para mostrar todas las clases
        /*
        // Si el usuario es un instructor, filtrar solo sus clases
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_INSTRUCTOR"))) {
            Instructor instructor = instructorService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
            clases = clases.stream()
                .filter(clase -> clase.getInstructor() != null && 
                        clase.getInstructor().getId() == instructor.getId())
                .toList();
        }
        */

        model.addAttribute("clases", clases);
        
        return "calendario/instructorSchedule";
    }
}