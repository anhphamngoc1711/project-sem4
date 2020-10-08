package com.example.projectsem4.controller;

import com.example.projectsem4.entity.*;
import com.example.projectsem4.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;
import java.util.Random;

@Controller
public class Vaccination_recordController {

    @Autowired
    private Vaccination_recordRepository vaccination_recordRepository;


    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    public void setVaccination_recordRepository(Vaccination_recordRepository vaccination_recordRepository) {
        this.vaccination_recordRepository = vaccination_recordRepository;
    }



    @RequestMapping(path = "/bills", method = RequestMethod.GET)
    public String getAllBills(Model model) {
        model.addAttribute("billsList", billRepository.findAll());
        model.addAttribute("doctorsList", doctorRepository.findAll());
        return "bills";
    }
//    @GetMapping("/success")
//    public String getAllDoctors(Model model){
//        model.addAttribute("doctorsList", billRepository.findAll());
//        return "success";
//    }

    @GetMapping("/success/{id}")
    public String index(Model model, @PathVariable int id,Doctor doctor) {
        Optional<Bill> bill = billRepository.findById(id);
        Optional<Doctor> doctor1 = doctorRepository.findById(id);
        model.addAttribute("doctors", doctor);
        bill.ifPresent(value -> model.addAttribute("bill", value));
        return "success";
    }
}
