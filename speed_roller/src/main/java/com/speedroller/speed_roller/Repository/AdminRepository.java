package com.speedroller.speed_roller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speedroller.speed_roller.model.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar un administrador por su correo electrónico:
    // Optional<Admin> findByEmail(String email);

    
 
    
}
