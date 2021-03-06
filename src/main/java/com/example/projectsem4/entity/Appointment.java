package com.example.projectsem4.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @NotNull
    private int phone;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotBlank(message = "Vui lòng chọn ngày tiêm")
    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Vui lòng nhập ngày sinh")
    private Date date_of_birth;


    @NotNull
    @NotBlank(message = "Vui lòng nhập địa chỉ")
    private String address;


    private int vaccine_id;

    @NotNull
    private String gender;

    private int place_id;

    private int user_id;


    private String date_now;

    public int getAppointment_id() {
        return appointment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate_now() {
        return date_now;
    }

    public void setDate_now(String date_now) {
        this.date_now = date_now;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,insertable = false, updatable = false)
    private User user;


    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Bill> billAppointment = new ArrayList<Bill>();

    @ManyToOne
    @JoinColumn(name = "vaccine_id", nullable = false,insertable = false, updatable = false)
    private Vaccine vaccine;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false,insertable = false,updatable = false)
    private Place place;

    @Override
    public void setSystemId(String systemId) {

    }

    @Override
    public String getSystemId() {
        return null;
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


    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
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



    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", date=" + date +
                ", date_of_birth=" + date_of_birth +
                ", address='" + address + '\'' +
                ", vaccine_id=" + vaccine_id +
                ", gender='" + gender + '\'' +
                ", billAppointment=" + billAppointment +
                ", vaccine=" + vaccine.getPrice() +
                '}';
    }
}
