package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Appointment;
import com.example.projectsem4.entity.Place;
import com.example.projectsem4.entity.User;
import com.example.projectsem4.entity.Vaccine;
import com.example.projectsem4.repository.AppointmentRepository;
import com.example.projectsem4.repository.PlaceRepository;
import com.example.projectsem4.repository.VaccineRepository;
import com.example.projectsem4.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
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


    @GetMapping("/fontend/registration")
    public String registration(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userLoginSuccess");
        //chua tiem data =1, tiem 1 lan data =2
        int numVaccine = user.getVaccinated();

        //Check if vaccine  = 0, display 1
        //Check if vaccine  = 1, display 2
        List<Vaccine> vaccinesList = vaccineRepository.findAll();
        Vaccine vaccine = new Vaccine();
        if(numVaccine == 0){
            vaccine = vaccinesList.get(0);
        } else if(numVaccine == 1){
            vaccine = vaccinesList.get(1);
        }



        model.addAttribute("appointment", new Appointment());
        model.addAttribute("vaccine", vaccine);
        model.addAttribute("placesList", placeRepository.findAll());
        model.addAttribute("id", user.getId());
        return "/fontend/registration";
    }

    @GetMapping("/admin/pages/addAppointments")
    public String addAppointment(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("vaccinesList", vaccineRepository.findAll());
        model.addAttribute("placesList", placeRepository.findAll());
        return "/admin/pages/addAppointments";
    }
    @PostMapping("/appointments/save")
    public String registration( @ModelAttribute Appointment appointment, Model model)
    {
        model.addAttribute("appointment", appointment);
        appointmentService.add(appointment);
        return "redirect:/payment/" + appointment.getAppointment_id();
    }


//    @RequestMapping({"/admin/pages/appointments.html"})
//    public String appointment() {
//        return "admin/pages/appointments";
//    }

    @RequestMapping(path = "/admin/pages/appointments.html", method = RequestMethod.GET)
    public String getAllAppointment(Model model) {
        model.addAttribute("appointmentsList", appointmentRepository.findAll());
        return "/admin/pages/appointments";
    }

    @RequestMapping(path = {"/appointments"}, method = {RequestMethod.POST})
    public String saveAppointment(Appointment appointment) {
    appointmentRepository.save(appointment);
        return "redirect:/admin/pages/appointments.html";
    }
    @PostMapping("/appointments/update")
    String updateAppointment( Appointment appointment) {
        Optional<Appointment> appointment1 = appointmentRepository.findById(appointment.getAppointment_id());
        if (appointment1.isPresent()){

            appointmentRepository.save(appointment);
            return "redirect:/admin/pages/appointments.html";
        }
        else {
            return "/";
        }
    }
    @RequestMapping(path = {"/admin/pages/editAppointment/{id}"}, method = {RequestMethod.GET})
    public String editAppointments(Model model, @PathVariable int id) {
        Optional<Appointment> appointment = this.appointmentRepository.findById(id);
        model.addAttribute("apointments", appointment);
        return "admin/pages/editAppointments";
    }


    @RequestMapping(value = "/appointments/delete/{id}", method = RequestMethod.GET)
    public  String deleteAppointment(@PathVariable(name = "id") int id){
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointment.ifPresent(value -> appointmentRepository.deleteAppointment(value.getAppointment_id()));
        return "redirect:/admin/pages/appointments.html";
    }

    @RequestMapping(path = {"/admin/pages/addAppointments.html"}, method = {RequestMethod.GET})
    public String createAppointment(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "/admin/pages/addAppointments";
    }



}
