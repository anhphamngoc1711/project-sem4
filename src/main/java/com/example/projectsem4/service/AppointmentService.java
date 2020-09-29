package com.example.projectsem4.service;


import com.example.projectsem4.entity.Appointment;
import com.example.projectsem4.repository.AppointmentRepository;
import com.example.projectsem4.validator.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentValidator appointmentValidator;


    public Appointment add(Appointment appointment){
        if (appointmentValidator.isValid(appointment)){
            return appointmentRepository.save(appointment);
        }
        return null;
    }
}

