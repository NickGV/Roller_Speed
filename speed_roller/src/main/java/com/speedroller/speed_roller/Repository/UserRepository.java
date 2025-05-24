package com.speedroller.speed_roller.Repository;

import com.speedroller.speed_roller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar un usuario por su correo electrónico:
    
}
