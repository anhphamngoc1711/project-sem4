package com.example.projectsem4.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Appointment implements Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointment_id;
    private String name;
    private int phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_of_birth;
    private String address;
    private String time_zone;
    private String injection_address;
    private int vaccine_id;
    private String gender;

    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Bill> billAppointment = new ArrayList<Bill>();

    @ManyToOne
    @JoinColumn(name = "vaccine_id", nullable = false,insertable = false, updatable = false)
    private Vaccine vaccine;

    @Override
    public void setSystemId(String systemId) {

    }

    @Override
    public String getSystemId() {
        return null;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", date=" + date +
                ", date_of_birth=" + date_of_birth +
                ", address='" + address + '\'' +
                ", injection_address='" + injection_address + '\'' +
                ", gender='" + gender + '\'' +
                ", billAppointment=" + billAppointment +
                ", vaccine=" + vaccine +
                '}';
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getInjection_address() {
        return injection_address;
    }

    public void setInjection_address(String injection_address) {
        this.injection_address = injection_address;
    }

    public int getVaccine_id() {
        return vaccine_id;
    }

    public void setVaccine_id(int vaccine_id) {
        this.vaccine_id = vaccine_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Bill> getBillAppointment() {
        return billAppointment;
    }

    public void setBillAppointment(List<Bill> billAppointment) {
        this.billAppointment = billAppointment;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
}
