package com.speedroller.speed_roller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speedroller.speed_roller.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar un usuario por su correo electrónico:
    
}
