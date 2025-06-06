package com.speedroller.speed_roller.controller.api;

import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "API de Estudiantes", description = "Endpoints para gestionar estudiantes")
@RestController
@RequestMapping("/api/students")
@SecurityRequirement(name = "JWT")
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Listar todos los estudiantes", description = "Obtiene la lista completa de estudiantes registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "403", description = "No autorizado para ver estudiantes",
                content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @Operation(summary = "Obtener estudiante por ID", description = "Busca un estudiante por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante encontrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado",
                content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @Parameter(description = "ID del estudiante", required = true) @PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo estudiante", description = "Registra un nuevo estudiante en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Estudiante creado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "400", description = "Datos de estudiante inválidos",
                content = @Content),
        @ApiResponse(responseCode = "409", description = "Email ya registrado",
                content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        // Verificar si el email ya está registrado
        if (studentService.findByEmail(student.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El email ya está registrado");
        }

        try {
            Student savedStudent = studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar: " + e.getMessage());
        }
    }

    @Operation(summary = "Actualizar estudiante", description = "Actualiza los datos de un estudiante existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante actualizado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado",
                content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos de estudiante inválidos",
                content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @Parameter(description = "ID del estudiante", required = true) @PathVariable Long id, 
            @RequestBody Student student) {
        if (!studentService.getStudentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        student.setId(id); // Asegurar que el ID coincida con la URL
        
        try {
            Student updatedStudent = studentService.saveStudent(student);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar estudiante", description = "Elimina un estudiante del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Estudiante eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado",
                content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "ID del estudiante", required = true) @PathVariable Long id) {
        if (!studentService.getStudentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}