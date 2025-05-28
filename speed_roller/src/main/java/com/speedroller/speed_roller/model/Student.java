package com.speedroller.speed_roller.Model;
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

    // Nuevo campo: Método de pago
    @Column(nullable = false, length = 50)
    private String metodoPago;

    
    // Nuevo campo: Rol (automático)
    @Column(nullable = false, length = 20)
    private String rol = "ALUMNO"; // Valor predeterminado

}