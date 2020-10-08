package com.example.projectsem4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectSem4Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjectSem4Application.class, args);

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPassword = "Anh";
//        String encoderPassword = encoder.encode(rawPassword);
//
//        System.out.println(encoderPassword);

    }

}
