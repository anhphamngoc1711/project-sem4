package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Doctor;
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

    @RequestMapping(path = "/doctor/add", method = RequestMethod.GET)
    public String createDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "addDoctor";
    }

    @RequestMapping(path = "/doctors", method = RequestMethod.POST)
    public String saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @RequestMapping(path = "/doctors", method = RequestMethod.GET)
    public String getAllDoctors(Model model) {
        model.addAttribute("doctorsList", doctorRepository.findAll());
        return "doctors";
    }

    @RequestMapping(path = "/doctors/editDoctor/{id}", method = RequestMethod.GET)
    public String editDoctor(Model model, @PathVariable int id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        model.addAttribute("doctor", doctor);
        return "editDoctor";
    }


    @PostMapping("/doctors/update")
    String updateDoctor( Doctor doctor) {
        Optional<Doctor> doctor1 = doctorRepository.findById(doctor.getDoctor_id());
        if (doctor1.isPresent()){

            doctorRepository.save(doctor);
            return "redirect:/doctors";
        }
        else {
            return "/";
        }
    }


    @RequestMapping(value = "/doctors/delete/{id}", method = RequestMethod.GET)
    public  String deleteDoctor(@PathVariable(name = "id") int id){
        Optional<Doctor> doctor = doctorRepository.findById(id);
        doctor.ifPresent(value -> doctorRepository.deleteDoctor(value.getDoctor_id()));
        return "redirect:/doctors";
    }
}

