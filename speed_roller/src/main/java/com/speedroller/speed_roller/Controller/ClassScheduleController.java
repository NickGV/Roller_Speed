package com.speedroller.speed_roller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.speedroller.speed_roller.model.ClassSchedule;
import com.speedroller.speed_roller.service.ClassScheduleService;
import com.speedroller.speed_roller.service.InstructorService;
import com.speedroller.speed_roller.service.StudentService;

@Controller
@RequestMapping("/administrador/clases")
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
        return "redirect:/administrador/clases";
    }

    // Asignar instructor a una clase
    @PostMapping("/asignar-instructor")
    public String asignarInstructor(@RequestParam("classId") Long classId, @RequestParam("instructorId") Long instructorId) {
        scheduleService.assignInstructorToClass(classId, instructorId);
        return "redirect:/administrador/clases";
    }

    // Agregar alumno a una clase
    @PostMapping("/agregar-alumno")
    public String agregarAlumno(@RequestParam("classId") Long classId, @RequestParam("studentId") Long studentId) {
        scheduleService.addStudentToClass(classId, studentId);
        return "redirect:/administrador/clases";
    }
}