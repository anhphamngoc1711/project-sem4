package com.example.projectsem4.controller;

import com.example.projectsem4.entity.User;
import com.example.projectsem4.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Data

public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/fontend/login")
    public String login(User user) {
        return "fontend/login";
    }

    @RequestMapping("/index")
    public String index(User user) {
        return "index";
    }

    @PostMapping("/fontend/login")
    public String postLogin(@RequestParam String username, @RequestParam String password, HttpSession session){
        User user = userRepository.checkUserLogin(username,password);
        if(user != null){
            //Login success
            session.setAttribute("userLoginSuccess", user);
            return "index";
        }
        return "fontend/login";
    }
}
