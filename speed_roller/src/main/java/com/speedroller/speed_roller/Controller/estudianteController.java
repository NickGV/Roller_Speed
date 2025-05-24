package com.speedroller.speed_roller.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedroller.speed_roller.Model.Student;
import com.speedroller.speed_roller.Service.studentService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/estudiantes")   
public class estudianteController {

    @Autowired
    private studentService estudianteService;

    @RequestMapping(value = "/listar") 
    public String getAllStudens(Model model) {
        List<Student> studentsList = estudianteService.getStudents();
        model.addAttribute("Estudiantesdb", studentsList); 
        return "estudiantes/listarEstudiantes";
    }
    
}
