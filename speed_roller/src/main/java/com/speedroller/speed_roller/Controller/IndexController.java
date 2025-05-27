package com.speedroller.speed_roller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping("home")
    public String index() {
        return "index";
    } 
}
