package com.example.projectsem4.validator;


import com.example.projectsem4.entity.Appointment;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Service
public class AppointmentValidator {

    public boolean isValid(Appointment appointment){
        return Optional.ofNullable(appointment)
                .filter( a -> !StringUtils.isEmpty(a.getName()))
                .filter( a -> !StringUtils.isEmpty(String.valueOf(a.getPhone())))
                .filter( a -> !StringUtils.isEmpty(a.getAddress()))
                .filter( a -> !StringUtils.isEmpty(String.valueOf(a.getDate())))
                .isPresent();
    }
}

