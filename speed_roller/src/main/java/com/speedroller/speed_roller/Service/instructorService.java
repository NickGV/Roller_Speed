package com.speedroller.speed_roller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.repository.InstructorRepository;

@Service
public class InstructorService {

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectamos el codificador de contraseñas

    @Autowired
    private InstructorRepository instructorRepository;

    public Instructor saveInstructor(Instructor instructor) {
        instructor.setPassword(passwordEncoder.encode(instructor.getPassword())); // Codificamos la contraseña antes de guardarla
        return instructorRepository.save(instructor);
    }

    public List<Instructor> getInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
    
}

