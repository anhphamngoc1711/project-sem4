package com.example.projectsem4.repository;

import com.example.projectsem4.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VaccineRepository extends JpaRepository<Vaccine,Integer> {
    @Transactional
    @Modifying
    @Query("delete from Vaccine e where e.vaccine_id=:x")
    int deleteVaccine(@Param("x") int vaccine_id);
}
