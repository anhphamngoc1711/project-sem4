package com.example.projectsem4.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctor_id;

    private String name;

    private Date date_of_birth;

    private int phone;

    private String email;

    private String address;

    private int gender;

    private String status;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Bill> doctorbill = new ArrayList<Bill>();
}
