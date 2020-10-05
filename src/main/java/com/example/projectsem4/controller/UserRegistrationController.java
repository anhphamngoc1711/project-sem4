package com.example.projectsem4.controller;

import com.example.projectsem4.entity.Place;
import com.example.projectsem4.entity.User;
import com.example.projectsem4.repository.PlaceRepository;
import com.example.projectsem4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setProductRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    @GetMapping("/fontend/signup.html")
//    public String showRegistrationForm(){
//        return "fontend/signup";
//    }

    @RequestMapping(path = "/fontend/signup.html", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "fontend/signup";
    }

    @RequestMapping(value = "/fontend/signup.html", method = RequestMethod.POST)
    public String saveUser(User user) {
        userRepository.save(user);
        return "redirect:/";
    }


}
