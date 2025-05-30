package com.speedroller.speed_roller.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.speedroller.speed_roller.model.Admin;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.model.Payment;
import com.speedroller.speed_roller.model.ClassSchedule;
import com.speedroller.speed_roller.repository.AdminRepository;
import com.speedroller.speed_roller.repository.StudentRepository;
import com.speedroller.speed_roller.repository.InstructorRepository;
import com.speedroller.speed_roller.repository.PaymentRepository;
import com.speedroller.speed_roller.repository.ClassScheduleRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Métodos para gestionar administradores
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public Admin saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    // Métodos para gestionar estudiantes
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    // Métodos para gestionar instructores
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    public void updateInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    // Métodos para gestionar pagos
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByStudent(Student student) {
        return paymentRepository.findByEstudianteOrderByFechaDesc(student);
    }

    public List<Payment> getPendingPayments() {
        return paymentRepository.findByEstudianteAndEstadoOrderByFechaDesc(null, "Pendiente");
    }

    // Métodos para gestionar clases
    public List<ClassSchedule> getAllClasses() {
        return classScheduleRepository.findAll();
    }

    public Optional<ClassSchedule> getClassById(Long id) {
        return classScheduleRepository.findById(id);
    }

    public void deleteClass(Long id) {
        classScheduleRepository.deleteById(id);
    }

    // Métodos para reportes
    public long getTotalStudents() {
        return studentRepository.count();
    }

    public long getTotalInstructors() {
        return instructorRepository.count();
    }

    public long getTotalClasses() {
        return classScheduleRepository.count();
    }

    public double getTotalPayments() {
        return paymentRepository.findAll().stream()
                .filter(p -> "Completado".equals(p.getEstado()))
                .mapToDouble(Payment::getMonto)
                .sum();
    }

    public double getPendingPaymentsTotal() {
        return paymentRepository.findAll().stream()
                .filter(p -> "Pendiente".equals(p.getEstado()))
                .mapToDouble(Payment::getMonto)
                .sum();
    }
} 