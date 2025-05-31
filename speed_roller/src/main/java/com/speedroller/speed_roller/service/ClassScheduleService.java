package com.speedroller.speed_roller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.model.ClassSchedule;
import com.speedroller.speed_roller.repository.ClassScheduleRepository;


@Service
public class ClassScheduleService {

    @Autowired
    private ClassScheduleRepository scheduleRepository;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private StudentService studentService;

    // Guardar una nueva clase
    public ClassSchedule saveClass(ClassSchedule clase) {
        return scheduleRepository.save(clase);
    }

    // Obtener todas las clases
    public List<ClassSchedule> getAllClasses() {
        return scheduleRepository.findAll();
    }

    // Asignar instructor a una clase
    public void assignInstructorToClass(Long classId, Long instructorId) {
        ClassSchedule clase = scheduleRepository.findById(classId).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        Instructor instructor = instructorService.getInstructorById(instructorId).orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        clase.setInstructor(instructor);
        scheduleRepository.save(clase);
    }

    // Agregar alumno a una clase
    public void addStudentToClass(Long classId, Long studentId) {
        ClassSchedule clase = scheduleRepository.findById(classId).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        Student student = studentService.getStudentById(studentId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado.."));

        if (!clase.getEstudiantes().contains(student)) {
            clase.getEstudiantes().add(student);
            scheduleRepository.save(clase);
        }
    }
}