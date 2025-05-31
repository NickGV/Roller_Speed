package com.speedroller.speed_roller.repository;

import com.speedroller.speed_roller.model.Payment;
import com.speedroller.speed_roller.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByEstudianteOrderByFechaDesc(Student estudiante);
    List<Payment> findByEstudianteAndEstadoOrderByFechaDesc(Student estudiante, String estado);
}