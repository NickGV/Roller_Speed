package com.speedroller.speed_roller.Repository;

import org.springframework.stereotype.Repository;

import com.speedroller.speed_roller.Model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar un estudiante por su correo electrónico:
    // Optional<Student> findByEmail(String email);
    
}
