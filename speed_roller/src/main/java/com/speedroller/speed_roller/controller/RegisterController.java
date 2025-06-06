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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Registro", description = "API para el registro de estudiantes e instructores")
@Controller
@RequestMapping("/registro")
public class RegisterController {

    @Autowired
    private StudentService estudianteService;

    @Autowired
    private InstructorService instructorService;

    @Operation(summary = "Formulario de registro de estudiante", description = "Muestra el formulario para registrar un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulario mostrado correctamente", content = @Content(mediaType = "text/html"))
    })
    @GetMapping("/nuevoEstudiante")
    public String mostrarFormularioNuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new Student());
        return "registro/studentRegister";
    }

    @Operation(summary = "Procesar registro de estudiante", description = "Procesa el formulario de registro de estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección después de un registro exitoso"),
            @ApiResponse(responseCode = "302", description = "Redirección por error (email duplicado o error de validación)")
    })
    @PostMapping("/guardarEstudiante")
    public String guardarEstudiante(
            @Schema(description = "Datos del estudiante a registrar") @ModelAttribute("estudiante") Student estudiante,
            RedirectAttributes redirectAttributes) {
        try {
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

    @Operation(summary = "Formulario de registro de instructor", description = "Muestra el formulario para registrar un nuevo instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formulario mostrado correctamente", content = @Content(mediaType = "text/html"))
    })
    @GetMapping("/nuevoInstructor")
    public String mostrarFormularioNuevoInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "registro/instructorRegister";
    }

    @Operation(summary = "Procesar registro de instructor", description = "Procesa el formulario de registro de instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección después de un registro exitoso"),
            @ApiResponse(responseCode = "302", description = "Redirección por error (email duplicado o error de validación)")
    })
    @PostMapping("/guardarInstructor")
    public String guardarInstructor(
            @Schema(description = "Datos del instructor a registrar") @ModelAttribute("instructor") Instructor instructor,
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
