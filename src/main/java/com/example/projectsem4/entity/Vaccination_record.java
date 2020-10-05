//package com.example.projectsem4.entity;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.springframework.stereotype.Controller;
//
//import javax.persistence.CascadeType;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
//import java.sql.Time;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class Vaccination_record {
//    private int id;
//    private String name;
//    private int phone;
//    private int gender;
//
//    private Time datetime;
//    private String doctor;
//    private int injections;
//
//
//    @OneToMany(mappedBy = "vaccination_record",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<Bill> bill_vaccination_record = new ArrayList<Bill>();
//}
