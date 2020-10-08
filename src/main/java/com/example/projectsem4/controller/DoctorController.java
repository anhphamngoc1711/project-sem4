package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Doctor;
import com.example.projectsem4.entity.Place;
import com.example.projectsem4.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;
@Controller
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public void setProductRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(path = {"/admin/pages/addDoctor.html"}, method = {RequestMethod.GET})
    public String createDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "/admin/pages/addDoctor";
    }

    @RequestMapping(path = {"/doctor"}, method = {RequestMethod.POST})
    public String saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/admin/pages/doctor.html";
    }

    @RequestMapping(path = {"/admin/pages/doctor.html"}, method = {RequestMethod.GET})
    public String getAllDoctors(Model model) {
        model.addAttribute("doctorsList", doctorRepository.findAll());
        return "admin/pages/doctor";
    }


    @RequestMapping(path = {"/admin/pages/editDoctor/{id}"}, method = {RequestMethod.GET})
    public String editDoctor(Model model, @PathVariable int id) {
        Optional<Doctor> doctor = this.doctorRepository.findById(id);
        model.addAttribute("doctor", doctor);
        return "admin/pages/editDoctor";
    }



    @RequestMapping(value = {"/doctors/update"}, method = {RequestMethod.POST})
   public String updateDoctor( Doctor doctor) {
        Optional<Doctor> doctor1 = this.doctorRepository.findById(doctor.getDoctor_id());
        if (doctor1.isPresent()){
            this.doctorRepository.save(doctor);
            return "redirect:/admin/pages/doctor.html";
        }
        else {
            return "/";
        }
    }


    @RequestMapping(value = "/doctor/delete/{id}", method = {RequestMethod.GET})
    public  String deleteDoctor(@PathVariable(name = "id") int id){
        Optional<Doctor> doctor = doctorRepository.findById(id);
        doctor.ifPresent(value -> doctorRepository.deleteDoctor(value.getDoctor_id()));
        return "redirect:/admin/pages/doctor.html";
    }
}

