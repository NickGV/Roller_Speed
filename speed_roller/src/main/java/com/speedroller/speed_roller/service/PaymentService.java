package com.speedroller.speed_roller.service;

import com.speedroller.speed_roller.model.Payment;
import com.speedroller.speed_roller.model.Student;
import com.speedroller.speed_roller.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByStudent(Student student) {
        return paymentRepository.findByEstudianteOrderByFechaDesc(student);
    }

    public List<Payment> getPendingPaymentsByStudent(Student student) {
        return paymentRepository.findByEstudianteAndEstadoOrderByFechaDesc(student, "Pendiente");
    }

    public Double getTotalPaidByStudent(Student student) {
        return paymentRepository.findByEstudianteAndEstadoOrderByFechaDesc(student, "Completado")
                .stream()
                .mapToDouble(Payment::getMonto)
                .sum();
    }

    public Double getPendingAmountByStudent(Student student) {
        return paymentRepository.findByEstudianteAndEstadoOrderByFechaDesc(student, "Pendiente")
                .stream()
                .mapToDouble(Payment::getMonto)
                .sum();
    }
}