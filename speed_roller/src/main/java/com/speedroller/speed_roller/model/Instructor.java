package com.speedroller.speed_roller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Entidad que representa a un instructor de la escuela de patinaje")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del instructor", example = "1")
    private long id;

    @Schema(description = "Nombre completo del instructor", example = "Carlos Pérez")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Schema(description = "Documento de identidad", example = "123456789")
    @Column(nullable = false, length = 20)
    private String documento;

    @Schema(description = "Email del instructor", example = "carlos.perez@email.com")
    @Column(nullable = false, length = 100)
    private String email;

    @Schema(description = "Teléfono del instructor", example = "3001234567")
    @Column(nullable = false, length = 50)
    private String telefono;

    @Schema(description = "Especialidad del instructor", example = "Velocidad")
    @Column(nullable = false, length = 250)
    private String especialidad;

    @Schema(description = "Años de experiencia", example = "5")
    @Column(nullable = false, length = 40)
    private Integer aniosExperiencia;

    @Schema(description = "Certificaciones del instructor", example = "Certificado Nacional de Patinaje")
    @Column(nullable = false, length = 255)
    private String certificaciones;

    @Schema(description = "Disponibilidad horaria", example = "Mañanas")
    @Column(nullable = false, length = 55)
    private String disponibilidad;

    @Schema(description = "Contraseña encriptada")
    @Column(nullable = false, length = 255)
    private String password;

    @Schema(description = "Rol del instructor en el sistema", example = "INSTRUCTOR", defaultValue = "INSTRUCTOR")
    @Column(nullable = false, length = 50)
    private String role = "INSTRUCTOR"; // ROL POR DEFECTO

}
