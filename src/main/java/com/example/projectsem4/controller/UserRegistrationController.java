package com.example.projectsem4.controller;

import com.example.projectsem4.entity.User;
import com.example.projectsem4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @RequestMapping(path = "/fontend/signup", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "fontend/signup";
    }

    @RequestMapping(value = "/fontend/signup", method = RequestMethod.POST)
    public String saveUser(@Valid User user, Errors errors) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "fontend/signup";
        } else {
            userRepository.save(user);

            return "redirect:/fontend/signup?success";
        }
    }
}
