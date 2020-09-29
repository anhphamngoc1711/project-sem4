package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Place;
import com.example.projectsem4.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    public void setProductRepository(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @RequestMapping(path = "/place/add", method = RequestMethod.GET)
    public String createPlace(Model model) {
        model.addAttribute("place", new Place());
        return "addPlace";
    }

    @RequestMapping(path = "/places", method = RequestMethod.POST)
    public String savePlace(Place place) {
        placeRepository.save(place);
        return "redirect:/places";
    }

    @RequestMapping(path = "/places", method = RequestMethod.GET)
    public String getAllPlaces(Model model) {
        model.addAttribute("placesList", placeRepository.findAll());
        return "places";
    }

    @RequestMapping(path = "/places/editPlace/{id}", method = RequestMethod.GET)
    public String editPlace(Model model, @PathVariable int id) {
        Optional<Place> place = placeRepository.findById(id);
        model.addAttribute("place", place);
        return "editPlace";
    }


    @PostMapping("/places/update")
    String updatePlace( Place place) {
        Optional<Place> place1 = placeRepository.findById(place.getPlace_id());
        if (place1.isPresent()){

            placeRepository.save(place);
            return "redirect:/places";
        }
        else {
            return "/";
        }
    }


    @RequestMapping(value = "/places/delete/{id}", method = RequestMethod.GET)
    public  String deletePlace(@PathVariable(name = "id") int id){
        Optional<Place> place = placeRepository.findById(id);
        place.ifPresent(value -> placeRepository.deletePlace(value.getPlace_id()));
        return "redirect:/places";
    }
}
