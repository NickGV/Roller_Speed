package com.speedroller.speed_roller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Schema(description = "Entidad que representa una clase o sesión de entrenamiento")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_clases")
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la clase", example = "1")
    private Long id;

    @Schema(description = "Día de la clase", example = "Lunes")
    @Column(name = "dia", nullable = false, length = 50)
    private String dia;

    @Schema(description = "Hora de inicio", example = "08:00")
    @Column(name = "hora_inicio", nullable = false, length = 50)
    private String horaInicio;

    @Schema(description = "Hora de fin", example = "10:00")
    @Column(name = "hora_fin", nullable = false, length = 50)
    private String horaFin;

    @Schema(description = "Nombre de la clase", example = "Entrenamiento de velocidad")
    @Column(name = "nombre_clase", nullable = false, length = 100)
    private String nombreClase;

    @Schema(description = "Tipo de clase", example = "Velocidad")
    @Column(name = "tipo_clase", nullable = false, length = 50)
    private String tipoClase;

    @Schema(description = "Nivel de la clase", example = "Intermedio")
    @Column(name = "nivel", nullable = false, length = 50)
    private String nivel;

    @Schema(description = "Descripción de la clase", example = "Entrenamiento enfocado en técnica y resistencia")
    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Schema(description = "Instructor asignado a la clase")
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Schema(description = "Lista de estudiantes inscritos en la clase")
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "tbl_clases_estudiantes", joinColumns = @JoinColumn(name = "clase_id"), inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
    private List<Student> estudiantes;
}