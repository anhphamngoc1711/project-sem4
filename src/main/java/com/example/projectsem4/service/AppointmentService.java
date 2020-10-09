package com.example.projectsem4.service;


import com.example.projectsem4.entity.Appointment;
import com.example.projectsem4.entity.Vaccine;
import com.example.projectsem4.repository.AppointmentRepository;
import com.example.projectsem4.validator.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
//    public Appointment checkAppointment(Appointment appointment,int id){
//        Optional<Appointment> appointment1 = appointmentRepository.findById(id);
//        if (appointment1.isPresent()){
//
//        }
//    }

}

