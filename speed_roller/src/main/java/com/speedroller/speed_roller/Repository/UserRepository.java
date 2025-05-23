package com.speedroller.speed_roller.Repository;

import com.speedroller.speed_roller.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar un usuario por su correo electrónico:
    // Optional<Usuario> findByEmail(String email);
}
