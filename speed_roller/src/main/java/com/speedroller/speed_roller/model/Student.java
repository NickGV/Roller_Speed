package com.speedroller.speed_roller.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_estudiantes")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    private long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 20)
    private String fechaNacimiento;

    @Column(nullable = false, length = 20)
    private String genero;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 50)  
    private String telefono;

    @Column(nullable = false, length = 50)  
    private String experiencia;

    @Column(nullable = false, length = 400)  
    private String comentarios;

    @Column(nullable = false, length = 50)
    private String metodoPago;

    @Column(name = "rol", nullable = false, length = 20)
    private String role = "ESTUDIANTE"; 

    @Column(nullable = false, length = 255)
    private String password;
}

