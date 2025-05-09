package com.speedroller.speed_roller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}