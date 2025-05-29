package com.speedroller.speed_roller.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.speedroller.speed_roller.model.Class;

@Repository
public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {
    // Métodos personalizados si es necesario
}