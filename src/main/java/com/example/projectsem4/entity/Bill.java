package com.example.projectsem4.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int bill_id;
    private float price;
    private int user_id;
    private Date create_at;
    private Date update_at;
    private Date delete_at;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false,insertable = false, updatable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false, insertable = false, updatable = false)
    private Doctor doctor;

//    @ManyToOne
//    @JoinColumn(name = "vaccination_record", nullable = false,insertable = false, updatable = false)
//    private Vaccination_record vaccination_record;
}
