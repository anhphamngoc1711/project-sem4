package com.example.projectsem4.entity;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Role(String role_user) {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
