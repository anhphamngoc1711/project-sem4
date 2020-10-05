package com.example.projectsem4.repository;

import com.example.projectsem4.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Transactional
    @Modifying
    @Query("delete from Doctor e where e.doctor_id=:x")
    int deleteDoctor(@Param("x") int doctor_id);
}
