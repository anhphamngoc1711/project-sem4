package com.example.projectsem4.config;

import com.example.projectsem4.validator.AppointmentValidator;
import org.springframework.context.annotation.Bean;

public class AppointmentConfig {

    @Bean
    public AppointmentValidator appointmentValidator(){
        return new AppointmentValidator();
    }
}

