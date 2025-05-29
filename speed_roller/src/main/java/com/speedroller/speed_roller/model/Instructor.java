package com.speedroller.speed_roller.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String documento;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String telefono;

    @Column(nullable = false, length = 250)
    private String especialidad;

    @Column(nullable = false, length = 40)
    private Integer aniosExperiencia;

    @Column(nullable = false, length = 255)
    private String certificaciones;

    @Column(nullable = false, length = 55)
    private String disponibilidad;

    @Column(nullable = false, length = 255)
    private String password;
}

