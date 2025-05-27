package com.speedroller.speed_roller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedroller.speed_roller.Model.Instructor;
import com.speedroller.speed_roller.Model.Student;
import com.speedroller.speed_roller.Service.instructorService;
import com.speedroller.speed_roller.Service.studentService;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/registro")
public class registroController {

    @Autowired
    private studentService estudianteService;

    @GetMapping("/nuevoEstudiante")
    public String mostrarFormularioNuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new Student());
        return "registro/registroEstudiante"; 
    }

    // Guardar estudiante enviado desde el formulario
    @PostMapping("/guardarEstudiante")
    public String guardarEstudiante(@ModelAttribute("estudiante") Student estudiante) {
        estudianteService.saveStudent(estudiante);
        return "redirect:/estudiantes/listar";
    }
    @Autowired
    private instructorService instructorService;

    @GetMapping("/nuevoInstructor")
    public String mostrarFormularioNuevoInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "registro/registroInstructor"; 
    }
    

    @PostMapping("/guardarInstructor")
    public String guardarInstructor(@ModelAttribute("instructor") Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/registro/nuevoInstructor";
        
    }
    
}
