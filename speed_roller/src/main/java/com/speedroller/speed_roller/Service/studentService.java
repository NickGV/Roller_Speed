package com.speedroller.speed_roller.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speedroller.speed_roller.Model.Student;
import com.speedroller.speed_roller.Repository.StudentRepository;

@Service
public class studentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }


    
}
