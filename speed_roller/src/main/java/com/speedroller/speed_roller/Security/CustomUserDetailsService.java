package com.speedroller.speed_roller.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.speedroller.speed_roller.model.Admin;
import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.repository.AdminRepository;
import com.speedroller.speed_roller.repository.InstructorRepository;
import com.speedroller.speed_roller.repository.StudentRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Ahora Vamos a buscar al usuario por su email en los repositorios
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            return new CustomUserDetail(student.get().getEmail(), student.get().getPassword(), student.get().getRole());
        }

        // Si no se encuentra un estudiante, buscamos en los instructores
        Optional<Instructor> instructor = instructorRepository.findByEmail(email);
        if (instructor.isPresent()) {
            return new CustomUserDetail(
                instructor.get().getEmail(), 
                instructor.get().getPassword(), 
                instructor.get().getRole()
            );
        }

        // Si no se encuentra un instructor, buscamos en los administradores
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) {
            return new CustomUserDetail(
                admin.get().getEmail(), 
                admin.get().getPassword(), 
                admin.get().getRole()
            );
        }
         System.out.println("Email encontrado: " + email + " - Rol: " + admin.get().getRole());
        
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");

    }
   
    
}
