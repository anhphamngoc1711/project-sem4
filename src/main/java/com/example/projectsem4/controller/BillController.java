package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Bill;
import com.example.projectsem4.entity.User;
import com.example.projectsem4.repository.AppointmentRepository;
import com.example.projectsem4.repository.BillRepository;
import com.example.projectsem4.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private VaccineRepository vaccineRepository;


    @RequestMapping(path = "/patient_registration", method = RequestMethod.GET)
    public String createBill(Model model) {
        model.addAttribute("bill", new Bill());
        return "patient_registration";
    }

    @RequestMapping(path = "/patient_registration", method = RequestMethod.POST)
    public String saveBill(Bill bill) {
        billRepository.save(bill);
        return "/";
    }
}