package com.speedroller.speed_roller.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.speedroller.speed_roller.model.Admin;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.model.Instructor;
import com.speedroller.speed_roller.model.Payment;
import com.speedroller.speed_roller.model.ClassSchedule;
import com.speedroller.speed_roller.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("totalStudents", adminService.getTotalStudents());
        model.addAttribute("totalInstructors", adminService.getTotalInstructors());
        model.addAttribute("totalClasses", adminService.getTotalClasses());
        model.addAttribute("totalPayments", adminService.getTotalPayments());
        model.addAttribute("pendingPayments", adminService.getPendingPaymentsTotal());
        return "administrador/dashboard";
    }

    @GetMapping("/estudiantes")
    public String listStudents(Model model) {
        model.addAttribute("estudiantes", adminService.getAllStudents());
        return "administrador/estudiantes/studentList";
    }

    @GetMapping("/estudiantes/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Optional<Student> student = adminService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("estudiante", student.get());
            model.addAttribute("pagos", adminService.getPaymentsByStudent(student.get()));
            return "administrador/estudiantes/detail";
        }
        return "redirect:/admin/estudiantes";
    }

    @PostMapping("/estudiantes/eliminar/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("mensaje", "Estudiante eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el estudiante");
        }
        return "redirect:/admin/estudiantes";
    }

    @GetMapping("/instructores")
    public String listInstructors(Model model) {
        model.addAttribute("instructores", adminService.getAllInstructors());
        return "administrador/instructores/instructorList";
    }

    @GetMapping("/instructores/{id}")
    public String viewInstructor(@PathVariable Long id, Model model) {
        Optional<Instructor> instructor = adminService.getInstructorById(id);
        if (instructor.isPresent()) {
            model.addAttribute("instructor", instructor.get());
            return "administrador/instructores/detail";
        }
        return "redirect:/admin/instructores";
    }

    @PostMapping("/instructores/eliminar/{id}")
    public String deleteInstructor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteInstructor(id);
            redirectAttributes.addFlashAttribute("mensaje", "Instructor eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el instructor");
        }
        return "redirect:/admin/instructores";
    }

    @GetMapping("/pagos")
    public String listPayments(Model model) {
        model.addAttribute("pagos", adminService.getAllPayments());
        model.addAttribute("pagosPendientes", adminService.getPendingPayments());
        return "administrador/pagos/paymentList";
    }

    @GetMapping("/clases")
    public String listClasses(Model model) {
        model.addAttribute("clases", adminService.getAllClasses());
        return "administrador/clases/classList";
    }

    @GetMapping("/clases/{id}")
    public String viewClass(@PathVariable Long id, Model model) {
        Optional<ClassSchedule> clase = adminService.getClassById(id);
        if (clase.isPresent()) {
            model.addAttribute("clase", clase.get());
            return "administrador/clases/detail";
        }
        return "redirect:/admin/clases";
    }

    @PostMapping("/clases/eliminar/{id}")
    public String deleteClass(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteClass(id);
            redirectAttributes.addFlashAttribute("mensaje", "Clase eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la clase");
        }
        return "redirect:/admin/clases";
    }
}
