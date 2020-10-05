package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Appointment;
import com.example.projectsem4.entity.Place;
import com.example.projectsem4.repository.AppointmentRepository;
import com.example.projectsem4.repository.PlaceRepository;
import com.example.projectsem4.repository.VaccineRepository;
import com.example.projectsem4.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    PlaceRepository placeRepository;

    @GetMapping("/fontend/registration.html")
    public String registration(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("vaccinesList", vaccineRepository.findAll());
        model.addAttribute("placesList", placeRepository.findAll());
        return "/fontend/registration";
    }
    @RequestMapping(path = "/appointments", method = RequestMethod.GET)
    public String getAllAppointment(Model model) {
        model.addAttribute("appointmentsList", appointmentRepository.findAll());
        return "appointments";
    }
    @RequestMapping(path = "/appointments/edit/{id}", method = RequestMethod.GET)
    public String editAppointment(Model model, @PathVariable int id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        model.addAttribute("appointment", appointment);
        return "editAppointment";
    }


    @PostMapping("/appointments/update")
    String updateAppointment( Appointment appointment) {
        Optional<Appointment> appointment1 = appointmentRepository.findById(appointment.getAppointment_id());
        if (appointment1.isPresent()){

            appointmentRepository.save(appointment);
            return "redirect:/appointments";
        }
        else {
            return "/";
        }
    }


    @RequestMapping(value = "/appointments/delete/{id}", method = RequestMethod.GET)
    public  String deleteAppointment(@PathVariable(name = "id") int id){
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointment.ifPresent(value -> appointmentRepository.deleteAppointment(value.getAppointment_id()));
        return "redirect:/appointments";
    }


    @PostMapping("/fontend/registration")
    public String registration( @ModelAttribute Appointment appointment, Model model)
    {
        model.addAttribute("appointment", appointment);
        appointmentService.add(appointment);
        return "redirect:/payment/" + appointment.getAppointment_id();
    }


}
