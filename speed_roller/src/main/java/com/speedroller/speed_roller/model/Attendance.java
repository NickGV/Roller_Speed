package com.speedroller.speed_roller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Schema(description = "Entidad que representa la asistencia de un estudiante a una clase")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_asistencias")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la asistencia", example = "1")
    private long id;

    @Schema(description = "Estudiante que asiste a la clase")
    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Student estudiante;

    @Schema(description = "Clase a la que asiste el estudiante")
    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private ClassSchedule clase;

    @Schema(description = "Fecha y hora de la asistencia", example = "2025-06-07T10:30:00")
    @Column(name = "fecha_asistencia", nullable = false)
    private LocalDateTime fechaAsistencia;

    @Schema(description = "Indica si el estudiante estuvo presente", example = "true")
    @Column(nullable = false)
    private boolean presente;

    @Schema(description = "Observaciones adicionales sobre la asistencia")
    @Column(length = 255)
    private String observaciones;
}