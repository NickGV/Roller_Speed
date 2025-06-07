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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Instructor", description = "Controlador para la gestión del perfil de instructores")
@Controller
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @Operation(summary = "Mostrar perfil de instructor", description = "Muestra el perfil del instructor autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil mostrado correctamente", content = @Content(mediaType = "text/html"))
    })
    @GetMapping("/perfil")
    public String mostrarPerfilInstructor(Model model, Principal principal) {
        Instructor instructor = instructorService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        model.addAttribute("instructor", instructor);
        return "instructor/instructorProfile";
    }

    @Operation(summary = "Actualizar perfil de instructor", description = "Actualiza los datos del perfil del instructor autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras actualizar correctamente"),
            @ApiResponse(responseCode = "302", description = "Redirección por error")
    })
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
