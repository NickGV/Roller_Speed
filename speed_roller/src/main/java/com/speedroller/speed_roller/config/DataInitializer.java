package com.speedroller.speed_roller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.speedroller.speed_roller.model.Admin;
import com.speedroller.speed_roller.repository.AdminRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
    //Esta clase es la encargada de inicializar la base de datos con datos por defecto
    //para este caso inicializara la base de datos con un usuario administrador por defecto

    @Autowired
    private AdminRepository adminRepository;

    @Autowired PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Verificar si ya existe un usuario administrador
        if (adminRepository.count() == 0) {
            // Crear un nuevo usuario administrador
            Admin admin = new Admin();
            admin.setEmail("admin@academia.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMINISTRADOR");

            // Guardar el usuario administrador en la base de datos
            adminRepository.save(admin);
        }
    }

    
}
