package com.speedroller.speed_roller.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/corporativo")
public class CorporativoController {

  @GetMapping("/mision")
  public String mision() {
    return "mision";
  }

  @GetMapping("/vision")
  public String vision() {
    return "vision";
  }

  @GetMapping("/valores")
  public String valores() {
    return "valores";
  }

  @GetMapping("/servicios")
  public String servicios() {
    return "servicios";
  }
}
