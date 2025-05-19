package com.speedroller.speed_roller.Controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping("home")
    public String index() {
        return "home";
    }
     @GetMapping("mision")
    public String mision() {
        return "mision";
    }
    @GetMapping("vision")
    public String vision() {
        return "vision";
    }
   
    @GetMapping("valores")
    public String valores() {
        return "valores";
    }
    @GetMapping("servicios")
    public String servicios() {
        return "servicios";
    }
    @GetMapping("eventos")
    public String eventos() {
        return "eventos";
    }
     @GetMapping("/registro/estudiante")
    public String inscribirEstudiante() {
        return "registro/inscribir-estudiante";
    }

    @GetMapping("/registro/instructor")
    public String inscribirInstructor() {
        return "registro/inscribir-instructor";
    }

    @GetMapping("/registro/clases")
    public String registroClases() {
        return "registro/clases";
    }

    @GetMapping("/estudiantes/listar")
    public String listarEstudiantes(Model model) {
        List<String> estudiantes = Arrays.asList("Ana Torres", "Luis Gómez", "Carlos Rojas");
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes/listar-estudiantes";
    }

    @GetMapping("/calendario/estudiantes")
    public String horarioEstudiantes() {
        return "calendario/horario-estudiantes";
    }

    @GetMapping("/calendario/instructores")
    public String horarioInstructores() {
        return "calendario/horario-instructores";
    }
    
}
