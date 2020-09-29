package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Appointment;
import com.example.projectsem4.repository.AppointmentRepository;
import com.example.projectsem4.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/fontend/registration.html")
    public String registration(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "/fontend/registration";
    }

    @PostMapping("/fontend/registration")
    public String registration(@ModelAttribute Appointment appointment, Model model)
    {
        model.addAttribute("appointment", appointment);
        return Optional.ofNullable(appointmentService.add(appointment)).map(a -> "payment").orElse("failed");
    }

}
