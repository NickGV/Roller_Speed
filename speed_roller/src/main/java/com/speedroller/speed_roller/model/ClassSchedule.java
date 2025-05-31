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

    @Column(name = "dia", nullable = false, length = 50)
    private String dia;

    @Column(name = "hora_inicio", nullable = false, length = 50)
    private String horaInicio;

    @Column(name = "hora_fin", nullable = false, length = 50)
    private String horaFin;

    @Column(name = "nombre_clase", nullable = false, length = 100)
    private String nombreClase;

    @Column(name = "tipo_clase", nullable = false, length = 50)
    private String tipoClase; 

    @Column(name = "nivel", nullable = false, length = 50)
    private String nivel; 

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "tbl_clases_estudiantes",
        joinColumns = @JoinColumn(name = "clase_id"),
        inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private List<Student> estudiantes;
}