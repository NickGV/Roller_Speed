package com.speedroller.speed_roller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_asistencias")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Student estudiante;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private ClassSchedule clase;

    @Column(name = "fecha_asistencia", nullable = false)
    private LocalDateTime fechaAsistencia;

    @Column(nullable = false)
    private boolean presente;

    @Column(length = 255)
    private String observaciones;
}