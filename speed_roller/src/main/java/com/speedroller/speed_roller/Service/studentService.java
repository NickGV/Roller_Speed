package com.speedroller.speed_roller.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectamos el codificador de contraseñas

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword())); // Codificamos la contraseña antes de guardarla
        return studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }


    
}
