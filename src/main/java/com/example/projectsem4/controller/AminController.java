package com.example.projectsem4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AminController {

    @GetMapping("/admin/template/index")
    public String admin(){
        return "admin";
    }

}
