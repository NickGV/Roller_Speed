package com.speedroller.speed_roller.controller;

import com.speedroller.speed_roller.model.ClassSchedule;
import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.model.Payment;
import com.speedroller.speed_roller.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Tag(name = "Administración", description = "API para la gestión administrativa del sistema")
@SecurityRequirement(name = "JWT")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Operation(summary = "Dashboard de administración", description = "Muestra el panel de control con estadísticas generales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dashboard mostrado correctamente", content = @Content(mediaType = "text/html")),
            @ApiResponse(responseCode = "403", description = "Acceso denegado", content = @Content)
    })
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Estadísticas para el dashboard
        model.addAttribute("totalStudents", adminService.getTotalStudents());
        model.addAttribute("totalInstructors", adminService.getTotalInstructors());
        model.addAttribute("totalClasses", adminService.getTotalClasses());
        model.addAttribute("totalPayments", adminService.getTotalPayments());
        model.addAttribute("pendingPayments", adminService.getPendingPaymentsTotal());

        return "administrador/dashboard";
    }

    @Operation(summary = "Listar instructores", description = "Muestra la lista de todos los instructores")
    @GetMapping("/instructores")
    public String listInstructors(Model model) {
        model.addAttribute("instructors", adminService.getAllInstructors());
        return "administrador/instructores/instructorList";
    }

    @Operation(summary = "Ver detalle de instructor", description = "Muestra los detalles de un instructor específico")
    @GetMapping("/instructores/{id}")
    public String viewInstructor(@Parameter(description = "ID del instructor") @PathVariable Long id, Model model) {
        Optional<Instructor> instructor = adminService.getInstructorById(id);
        if (instructor.isPresent()) {
            model.addAttribute("instructor", instructor.get());
            // Aquí podrías añadir más atributos como las clases del instructor
            return "administrador/instructores/detail";
        }
        return "redirect:/admin/instructores";
    }

    @Operation(summary = "Eliminar instructor", description = "Elimina un instructor del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras eliminar correctamente"),
            @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })
    @PostMapping("/instructores/eliminar/{id}")
    public String deleteInstructor(
            @Parameter(description = "ID del instructor a eliminar") @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteInstructor(id);
            redirectAttributes.addFlashAttribute("mensaje", "Instructor eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar instructor: " + e.getMessage());
        }
        return "redirect:/admin/instructores";
    }

    @Operation(summary = "Formulario para editar instructor", description = "Muestra el formulario para editar un instructor")
    @GetMapping("/instructores/editar/{id}")
    public String editInstructorForm(
            @Parameter(description = "ID del instructor a editar") @PathVariable Long id,
            Model model) {
        Optional<Instructor> instructor = adminService.getInstructorById(id);
        if (instructor.isPresent()) {
            model.addAttribute("instructor", instructor.get());
            return "administrador/instructores/editInstructor";
        }
        return "redirect:/admin/instructores";
    }

    @Operation(summary = "Actualizar instructor", description = "Actualiza la información de un instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras actualizar correctamente"),
            @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })
    @PostMapping("/instructores/editar/{id}")
    public String updateInstructor(
            @Parameter(description = "ID del instructor") @PathVariable Long id,
            @ModelAttribute Instructor instructor,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.updateInstructor(instructor);
            redirectAttributes.addFlashAttribute("mensaje", "Instructor actualizado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar instructor: " + e.getMessage());
        }
        return "redirect:/admin/instructores";
    }

    @Operation(summary = "Listar clases", description = "Muestra la lista de todas las clases")
    @GetMapping("/clases")
    public String listClasses(Model model) {
        model.addAttribute("clases", adminService.getAllClasses());
        return "administrador/clases/classList";
    }

    @Operation(summary = "Ver detalle de clase", description = "Muestra los detalles de una clase específica")
    @GetMapping("/clases/{id}")
    public String viewClass(
            @Parameter(description = "ID de la clase") @PathVariable Long id,
            Model model) {
        Optional<ClassSchedule> clase = adminService.getClassById(id);
        if (clase.isPresent()) {
            model.addAttribute("clase", clase.get());
            return "administrador/clases/detail";
        }
        return "redirect:/admin/clases";
    }

    @Operation(summary = "Eliminar clase", description = "Elimina una clase del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras eliminar correctamente"),
            @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    @PostMapping("/clases/eliminar/{id}")
    public String deleteClass(
            @Parameter(description = "ID de la clase a eliminar") @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteClass(id);
            redirectAttributes.addFlashAttribute("mensaje", "Clase eliminada correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar clase: " + e.getMessage());
        }
        return "redirect:/admin/clases";
    }

    @Operation(summary = "Formulario para nueva clase", description = "Muestra el formulario para crear una nueva clase")
    @GetMapping("/clases/nueva")
    public String newClassForm(Model model) {
        model.addAttribute("clase", new ClassSchedule());
        model.addAttribute("instructores", adminService.getAllInstructors());
        return "administrador/clases/newClass";
    }

    @Operation(summary = "Guardar nueva clase", description = "Guarda la información de una nueva clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras guardar correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de clase inválidos")
    })
    @PostMapping("/clases/guardar")
    public String saveClass(
            @ModelAttribute ClassSchedule clase,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.saveClass(clase);
            redirectAttributes.addFlashAttribute("mensaje", "Clase creada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear la clase: " + e.getMessage());
        }
        return "redirect:/admin/clases";
    }

    @Operation(summary = "Formulario para editar clase", description = "Muestra el formulario para editar una clase")
    @GetMapping("/clases/editar/{id}")
    public String editClassForm(
            @Parameter(description = "ID de la clase a editar") @PathVariable Long id,
            Model model) {
        Optional<ClassSchedule> clase = adminService.getClassById(id);
        if (clase.isPresent()) {
            model.addAttribute("clase", clase.get());
            model.addAttribute("instructores", adminService.getAllInstructors());
            return "administrador/clases/editClass";
        }
        return "redirect:/admin/clases";
    }

    @Operation(summary = "Actualizar clase", description = "Actualiza la información de una clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras actualizar correctamente"),
            @ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    @PostMapping("/clases/editar/{id}")
    public String updateClass(
            @Parameter(description = "ID de la clase") @PathVariable Long id,
            @ModelAttribute ClassSchedule clase,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.updateClass(clase);
            redirectAttributes.addFlashAttribute("mensaje", "Clase actualizada correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar clase: " + e.getMessage());
        }
        return "redirect:/admin/clases";
    }

    @Operation(summary = "Listar estudiantes", description = "Muestrala lista de todos los estudiantes")
    @GetMapping("/estudiantes")
    public String listStudents(Model model) {
        // Cambiar esto:
        // model.addAttribute("Estudiantesdb", adminService.getAllStudents());

        // Por esto:
        model.addAttribute("students", adminService.getAllStudents());
        return "administrador/estudiantes/studentList";
    }

    @Operation(summary = "Ver detalle de estudiante", description = "Muestra los detalles de un estudiante específico")
    @GetMapping("/estudiantes/{id}")
    public String viewStudent(
            @Parameter(description = "ID del estudiante") @PathVariable Long id,
            Model model) {
        Optional<Student> estudiante = adminService.getStudentById(id);
        if (estudiante.isPresent()) {
            model.addAttribute("estudiante", estudiante.get());
            return "administrador/estudiantes/detail";
        }
        return "redirect:/admin/estudiantes";
    }

    @Operation(summary = "Eliminar estudiante", description = "Elimina un estudiante del sistema")
    @PostMapping("/estudiantes/eliminar/{id}")
    public String deleteStudent(
            @Parameter(description = "ID del estudiante a eliminar") @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("mensaje", "Estudiante eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar estudiante: " + e.getMessage());
        }
        return "redirect:/admin/estudiantes";
    }

    @Operation(summary = "Formulario para editar estudiante", description = "Muestra el formulario para editar un estudiante")
    @GetMapping("/estudiantes/editar/{id}")
    public String editStudentForm(
            @Parameter(description = "ID del estudiante a editar") @PathVariable Long id,
            Model model) {
        Optional<Student> estudiante = adminService.getStudentById(id);
        if (estudiante.isPresent()) {
            model.addAttribute("estudiante", estudiante.get());
            return "administrador/estudiantes/editStudent";
        }
        return "redirect:/admin/estudiantes";
    }

    @Operation(summary = "Listar pagos", description = "Muestra la lista de todos los pagos realizados")
    @GetMapping("/pagos")
    public String listPayments(Model model) {
        model.addAttribute("pagos", adminService.getAllPayments());
        model.addAttribute("totalPagos", adminService.getTotalPayments());
        model.addAttribute("pagosPendientes", adminService.getPendingPaymentsCount());
        model.addAttribute("pagosCompletados", adminService.getCompletedPaymentsCount());
        return "administrador/pagos/paymentList";
    }

    @Operation(summary = "Ver detalle de pago", description = "Muestra los detalles de un pago específico")
    @GetMapping("/pagos/{id}")
    public String viewPayment(
            @Parameter(description = "ID del pago") @PathVariable Long id,
            Model model) {
        Optional<Payment> pago = adminService.getPaymentById(id);
        if (pago.isPresent()) {
            model.addAttribute("pago", pago.get());
            return "administrador/pagos/detail";
        }
        return "redirect:/admin/pagos";
    }

    @Operation(summary = "Formulario para nuevo pago", description = "Muestra el formulario para registrar un nuevo pago")
    @GetMapping("/pagos/nuevo")
    public String newPaymentForm(Model model) {
        model.addAttribute("pago", new Payment());
        model.addAttribute("estudiantes", adminService.getAllStudents());
        model.addAttribute("metodosPago", Arrays.asList("Efectivo", "Tarjeta", "Transferencia", "PSE"));
        return "administrador/pagos/newPayment";
    }

    @Operation(summary = "Guardar nuevo pago", description = "Registra un nuevo pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras guardar correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de pago inválidos")
    })
    @PostMapping("/pagos/guardar")
    public String savePayment(
            @ModelAttribute Payment pago,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.savePayment(pago);
            redirectAttributes.addFlashAttribute("mensaje", "Pago registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el pago: " + e.getMessage());
        }
        return "redirect:/admin/pagos";
    }

    @Operation(summary = "Cambiar estado de pago", description = "Actualiza el estado de un pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras actualizar correctamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PostMapping("/pagos/cambiarEstado/{id}")
    public String changePaymentStatus(
            @Parameter(description = "ID del pago") @PathVariable Long id,
            @RequestParam String nuevoEstado,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.updatePaymentStatus(id, nuevoEstado);
            redirectAttributes.addFlashAttribute("mensaje", "Estado del pago actualizado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar estado: " + e.getMessage());
        }
        return "redirect:/admin/pagos";
    }

    @Operation(summary = "Eliminar pago", description = "Elimina un pago del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirección tras eliminar correctamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PostMapping("/pagos/eliminar/{id}")
    public String deletePayment(
            @Parameter(description = "ID del pago a eliminar") @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            adminService.deletePayment(id);
            redirectAttributes.addFlashAttribute("mensaje", "Pago eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar pago: " + e.getMessage());
        }
        return "redirect:/admin/pagos";
    }

    @Operation(summary = "Filtrar pagos", description = "Muestra pagos filtrados por estudiante o estado")
    @GetMapping("/pagos/filtrar")
    public String filterPayments(
            @RequestParam(required = false) Long estudianteId,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            Model model) {

        List<Payment> pagosFiltrados;

        if (estudianteId != null) {
            Optional<Student> estudiante = adminService.getStudentById(estudianteId);
            if (estudiante.isPresent()) {
                pagosFiltrados = adminService.getPaymentsByStudent(estudiante.get());
            } else {
                pagosFiltrados = adminService.getAllPayments();
            }
        } else if (estado != null && !estado.isEmpty()) {
            pagosFiltrados = adminService.getPaymentsByStatus(estado);
        } else {
            pagosFiltrados = adminService.getAllPayments();
        }

        model.addAttribute("pagos", pagosFiltrados);
        model.addAttribute("estudiantes", adminService.getAllStudents());
        model.addAttribute("estados", Arrays.asList("Pendiente", "Completado", "Cancelado"));
        return "administrador/pagos/paymentList";
    }
}
