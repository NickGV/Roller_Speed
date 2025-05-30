package com.speedroller.speed_roller.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.speedroller.speed_roller.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar un instructor por su correo electrónico:
    Optional<Instructor> findByEmail(String email);
    
}
