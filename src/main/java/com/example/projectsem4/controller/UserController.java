package com.example.projectsem4.controller;

import com.example.projectsem4.entity.User;
import com.example.projectsem4.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Data

public class UserController {
    @Autowired
    UserRepository userRepository;

//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/fontend/login.html")
    public String login(User user) {
        return "fontend/login";
    }

    @RequestMapping("/index")
    public String index(User user) {
        return "index";
    }

//    @RequestMapping(value = "/fontend/signup.html", method = RequestMethod.POST)
//    public String save(User user) {
//        userRepository.save(user);
//        return "index.html";
//    }
}
