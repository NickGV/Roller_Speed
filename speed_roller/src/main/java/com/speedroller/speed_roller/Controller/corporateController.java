package com.speedroller.speed_roller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/corporativo")
public class CorporateController {

  @GetMapping("/mision")
  public String mision() {
    return "corporativo/mission";
  }

  @GetMapping("/vision")
  public String vision() {
    return "corporativo/vision";
  }

  @GetMapping("/valores")
  public String valores() {
    return "corporativo/values";
  }

  @GetMapping("/servicios")
  public String servicios() {
    return "corporativo/services";
  }
}
