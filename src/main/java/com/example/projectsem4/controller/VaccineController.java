package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Vaccine;
import com.example.projectsem4.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class VaccineController {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    public void setProductRepository(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @RequestMapping(path = "/vaccine/add", method = RequestMethod.GET)
    public String createVaccine(Model model) {
        model.addAttribute("vaccine", new Vaccine());
        return "addVaccine";
    }

    @RequestMapping(path = "/vaccines", method = RequestMethod.POST)
    public String saveVaccine(Vaccine vaccine) {
        vaccineRepository.save(vaccine);
        return "redirect:/vaccines";
    }

    @RequestMapping(path = "/vaccines", method = RequestMethod.GET)
    public String getAllVaccines(Model model) {
        model.addAttribute("vaccinesList", vaccineRepository.findAll());
        return "vaccines";
    }

    @RequestMapping(path = "/vaccines/editVaccine/{id}", method = RequestMethod.GET)
    public String editVaccine(Model model, @PathVariable int id) {
        Optional<Vaccine> vaccine = vaccineRepository.findById(id);
        model.addAttribute("vaccine", vaccine);
        return "editVaccine";
    }


    @PostMapping("/vaccines/update")
    String updateVaccine( Vaccine vaccine) {
        Optional<Vaccine> vaccine1 = vaccineRepository.findById(vaccine.getVaccine_id());
        if (vaccine1.isPresent()){

            vaccineRepository.save(vaccine);
            return "redirect:/vaccines";
        }
        else {
            return "/";
        }
    }


    @RequestMapping(value = "/vaccines/delete/{id}", method = RequestMethod.GET)
    public  String deleteVaccine(@PathVariable(name = "id") int id){
        Optional<Vaccine> vaccine = vaccineRepository.findById(id);
        vaccine.ifPresent(value -> vaccineRepository.deleteVaccine(value.getVaccine_id()));
        return "redirect:/vaccines";
    }
}

