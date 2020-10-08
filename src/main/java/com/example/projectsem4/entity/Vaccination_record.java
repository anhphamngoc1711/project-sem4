package com.example.projectsem4.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vaccination_record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String doctor_id;

    private String bill_id;


}
