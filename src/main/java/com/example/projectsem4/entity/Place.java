package com.example.projectsem4.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int place_id;
    private String places;
    private int amount;
    private int vaccine_id;

    @ManyToOne
    @JoinColumn(name = "vaccine_id", nullable = false,insertable = false, updatable = false)
    private Vaccine vaccine;

    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Appointment> appointments = new ArrayList<Appointment>();

    public static void setId(int id) {
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getVaccine_id() {
        return vaccine_id;
    }

    public void setVaccine_id(int vaccine_id) {
        this.vaccine_id = vaccine_id;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
}

