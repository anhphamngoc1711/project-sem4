package com.example.projectsem4.repository;

import com.example.projectsem4.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Transactional
    @Modifying
    @Query("delete from Appointment e where e.appointment_id=:x")
    int deleteAppointment(@Param("x") int appointment_id);

    //get appointment by id
    @Query("SELECT a FROM Appointment a WHERE a.user_id = :user_id")
    Appointment getAppointmentById(@Param("user_id") int user_id);
}
