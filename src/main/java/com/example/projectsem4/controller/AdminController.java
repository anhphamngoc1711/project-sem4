package com.example.projectsem4.controller;

import com.example.projectsem4.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    private PlaceRepository placeRepository;

    public AdminController() {
    }
    @GetMapping({"/admin/login"})
    public String login() {
        return "admin/pages/LoginSignup/login";
    }

    @GetMapping({"/admin/index"})
    public String index() {
        return "admin/index";
    }

//    @RequestMapping({"/admin/pages/doctor.html"})
//    public String doctor() {
//        return "admin/pages/doctor";
//    }

    @RequestMapping({"/admin/pages/chartjs"})
    public String user() {
        return "admin/pages/chartjs";
    }

//    @RequestMapping({"/admin/pages/basic-table.html"})
//    public String appointment() {
//        return "admin/pages/basic-table";
//    }
}
