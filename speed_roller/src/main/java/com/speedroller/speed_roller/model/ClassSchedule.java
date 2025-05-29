package com.speedroller.speed_roller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_clases")
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String dia; // Ejemplo: Lunes, Martes, etc.

    @Column(nullable = false, length = 50)
    private String horaInicio; // Ejemplo: 8:00 AM

    @Column(nullable = false, length = 50)
    private String horaFin; // Ejemplo: 9:30 AM

    @Column(nullable = false, length = 100)
    private String nombreClase; // Ejemplo: Principiante Niños, Intermedio Adolescentes, etc.

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor; // Instructor asignado a la clase

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "tbl_clases_estudiantes",
        joinColumns = @JoinColumn(name = "clase_id"),
        inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private List<Student> estudiantes; // Lista de alumnos inscritos en la clase

    @Column(nullable = false, length = 20)
    private String nivel; // Ejemplo: Principiante, Intermedio, Avanzado
}