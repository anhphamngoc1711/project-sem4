package com.example.projectsem4.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Entity
@Data
public class Contact implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int contact_id;
    private  int name;
    private  String email;
    private  String title;
    private  String content;


}




