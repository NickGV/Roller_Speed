package com.speedroller.speed_roller.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_usuarios")
public class User {

    //Identificador único del usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Genera un valor único para cada usuario
    private long id;

    //Nombre del usuario
    @Column(nullable = false, length = 50)
    //Nombre del usuario, no puede ser nulo y tiene una longitud máxima de 50 caracteres
    private String nombre;

    //dirección de correo electrónico del usuario
    @Column(nullable = false, unique = true, length = 100)
    /*Dirección de correo electrónico del usuario, no puede ser nulo,
    debe ser único y tiene una longitud máxima de 100 caracteres */
    private String email;

    //contraseña del usuario
    @Column(nullable = false, length = 50)  
    private String password;
    
}
