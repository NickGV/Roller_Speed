package com.speedroller.speed_roller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "Entidad que representa a un estudiante de la escuela de patinaje")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_estudiantes")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del estudiante", example = "1")
    private long id;

    @Schema(description = "Nombre del estudiante", example = "Juan", required = true)
    @Column(nullable = false, length = 50)
    private String nombre;

    @Schema(description = "Apellido del estudiante", example = "Pérez", required = true)
    @Column(nullable = false, length = 50)
    private String apellido;

    @Schema(description = "Email del estudiante", example = "juan.perez@email.com", required = true)
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Schema(description = "Teléfono del estudiante", example = "3001234567")
    @Column(length = 20)
    private String telefono;

    @Schema(description = "Fecha de nacimiento del estudiante", example = "2005-06-15")
    private String fechaNacimiento;

    @Schema(description = "Género del estudiante", example = "Masculino", allowableValues = { "Masculino", "Femenino",
            "Otro", "No especificado" })
    private String genero;

    @Schema(description = "Nivel de experiencia del estudiante", example = "Principiante", allowableValues = {
            "Principiante", "Intermedio", "Avanzado" })
    private String experiencia;

    @Schema(description = "Comentarios o notas sobre el estudiante")
    @Column(length = 500)
    private String comentarios;

    @Schema(description = "Contraseña del estudiante (encriptada)")
    @Column(nullable = false)
    private String password;

    @Schema(description = "Rol del estudiante en el sistema", example = "ESTUDIANTE", defaultValue = "ESTUDIANTE")
    private String role = "ESTUDIANTE";
}