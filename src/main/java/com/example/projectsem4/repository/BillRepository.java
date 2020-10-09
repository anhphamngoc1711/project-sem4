package com.example.projectsem4.repository;


import com.example.projectsem4.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillRepository extends JpaRepository<Bill,Integer> {

//    @Query("SELECT u FROM Bill u WHERE u.is_confirmed=false ")
//    public Bill getBillByDoctor(@Param("doctor") String doctor);

}
