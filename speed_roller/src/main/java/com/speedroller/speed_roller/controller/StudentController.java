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

@Controller
@RequestMapping("/estudiantes")   
public class StudentController {

    @Autowired
    private StudentService estudianteService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/listar") 
    public String getAllStudens(Model model) {
        List<Student> studentsList = estudianteService.getStudents();
        model.addAttribute("Estudiantesdb", studentsList); 
        return "estudiantes/listStudents";
    }
    
    @GetMapping("/perfil")
    public String mostrarPerfilEstudiante(Model model, Principal principal) {
        Student student = estudianteService.findByEmail(principal.getName())
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        model.addAttribute("Estudiante", student);
        return "estudiantes/studentProfile";
    }

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

    @GetMapping("/comprobante/{id}")
    public String verComprobante(@PathVariable Long id, Model model, Authentication authentication) {
        return "estudiantes/comprobante";
    }
}
