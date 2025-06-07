package com.speedroller.speed_roller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Entidad que representa a un administrador del sistema")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del administrador", example = "1")
    private long id;

    @Schema(description = "Nombre del administrador", example = "Admin Principal")
    @Column(nullable = false, length = 100)
    private String name;

    @Schema(description = "Contraseña encriptada")
    @Column(nullable = false, length = 255)
    private String password;

    @Schema(description = "Email del administrador", example = "admin@email.com")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Schema(description = "Rol del administrador", example = "ADMIN")
    @Column(nullable = false, length = 50)
    private String role; // ROL POR DEFECTO

}