package com.speedroller.speed_roller.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.service.StudentService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.speedroller.speed_roller.model.Payment;
import com.speedroller.speed_roller.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estudiantes", description = "Controlador para la gestión de estudiantes y sus pagos")
@Controller
@RequestMapping("/estudiantes")
public class StudentController {

    @Autowired
    private StudentService estudianteService;

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Listar estudiantes", description = "Obtiene la lista de todos los estudiantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estudiantes mostrada correctamente", content = @Content(mediaType = "text/html"))
    })
    @RequestMapping(value = "/listar")
    public String getAllStudens(Model model) {
        List<Student> studentsList = estudianteService.getStudents();
        model.addAttribute("Estudiantesdb", studentsList);
        return "estudiantes/listStudents";
    }

    @Operation(summary = "Mostrar perfil de estudiante", description = "Muestra el perfil del estudiante autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil mostrado correctamente", content = @Content(mediaType = "text/html"))
    })
    @GetMapping("/perfil")
    public String mostrarPerfilEstudiante(Model model, Principal principal) {
        Student student = estudianteService.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        model.addAttribute("Estudiante", student);
        return "estudiantes/studentProfile";
    }

    @Operation(summary = "Actualizar perfil de estudiante", description = "Actualiza los datos del perfil del estudiante autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras actualizar correctamente"),
            @ApiResponse(responseCode = "302", description = "Redirección por error")
    })
    @PostMapping("/actualizarPerfil")
    public String actualizarPerfilEstudiante(@ModelAttribute("Estudiante") Student student,
            Principal principal,
            RedirectAttributes redirectAttributes) {
        try {
            Student existingStudent = estudianteService.findByEmail(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

            if (existingStudent.getId() != student.getId()) {
                throw new RuntimeException("No autorizado para actualizar este perfil");
            }

            student.setRole(existingStudent.getRole());
            student.setPassword(existingStudent.getPassword());

            estudianteService.saveStudent(student);
            redirectAttributes.addFlashAttribute("mensaje", "Perfil actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el perfil: " + e.getMessage());
        }

        return "redirect:/estudiantes/perfil";
    }

    @Operation(summary = "Mostrar pagos del estudiante", description = "Muestra los pagos realizados y pendientes del estudiante autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vista de pagos mostrada correctamente", content = @Content(mediaType = "text/html"))
    })
    @GetMapping("/pagos")
    public String mostrarPagos(Model model, Authentication authentication) {
        Student estudiante = estudianteService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<Payment> pagos = paymentService.getPaymentsByStudent(estudiante);
        Double totalPagado = paymentService.getTotalPaidByStudent(estudiante);
        Double pendientePagar = paymentService.getPendingAmountByStudent(estudiante);

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("pagos", pagos);
        model.addAttribute("totalPagado", totalPagado);
        model.addAttribute("pendientePagar", pendientePagar);
        model.addAttribute("proximoPago", 100.00);

        return "estudiantes/pagos";
    }

    @Operation(summary = "Realizar pago", description = "Permite al estudiante realizar un pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras pago exitoso o error")
    })
    @PostMapping("/realizarPago")
    public String realizarPago(@RequestParam String concepto,
            @RequestParam Double monto,
            @RequestParam String metodoPago,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {
        try {
            Student estudiante = estudianteService.findByEmail(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

            Payment pago = new Payment();
            pago.setEstudiante(estudiante);
            pago.setConcepto(concepto);
            pago.setMonto(monto);
            pago.setMetodoPago(metodoPago);
            pago.setEstado("Completado");

            paymentService.savePayment(pago);

            redirectAttributes.addFlashAttribute("mensaje", "Pago realizado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al realizar el pago: " + e.getMessage());
        }

        return "redirect:/estudiantes/pagos";
    }

    @Operation(summary = "Cambiar método de pago", description = "Permite al estudiante cambiar su método de pago preferido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras cambio exitoso o error")
    })
    @PostMapping("/cambiarMetodoPago")
    public String cambiarMetodoPago(@RequestParam String nuevoMetodoPago,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {
        try {
            Student estudiante = estudianteService.findByEmail(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

            estudiante.setMetodoPago(nuevoMetodoPago);
            estudianteService.saveStudent(estudiante);

            redirectAttributes.addFlashAttribute("mensaje", "Método de pago actualizado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el método de pago: " + e.getMessage());
        }

        return "redirect:/estudiantes/pagos";
    }

    @Operation(summary = "Ver comprobante de pago", description = "Muestra el comprobante de un pago específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comprobante mostrado correctamente", content = @Content(mediaType = "text/html"))
    })
    @GetMapping("/comprobante/{id}")
    public String verComprobante(@PathVariable Long id, Model model, Authentication authentication) {
        return "estudiantes/comprobante";
    }
}
