package com.speedroller.speed_roller.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Corporativo", description = "Controlador para la información institucional de la escuela")
@Controller
@RequestMapping("/corporativo")
public class CorporateController {

  @Operation(summary = "Ver misión", description = "Muestra la misión de la escuela")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vista de misión mostrada correctamente")
  })
  @GetMapping("/mision")
  public String mision() {
    return "corporativo/mission";
  }

  @Operation(summary = "Ver visión", description = "Muestra la visión de la escuela")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vista de visión mostrada correctamente")
  })
  @GetMapping("/vision")
  public String vision() {
    return "corporativo/vision";
  }

  @Operation(summary = "Ver valores", description = "Muestra los valores de la escuela")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vista de valores mostrada correctamente")
  })
  @GetMapping("/valores")
  public String valores() {
    return "corporativo/values";
  }

  @Operation(summary = "Ver servicios", description = "Muestra los servicios que ofrece la escuela")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vista de servicios mostrada correctamente")
  })
  @GetMapping("/servicios")
  public String servicios() {
    return "corporativo/services";
  }
}
