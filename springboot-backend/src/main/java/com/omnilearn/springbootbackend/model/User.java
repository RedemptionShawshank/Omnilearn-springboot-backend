package com.omnilearn.springbootbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email_id")
    private String emailId;

    @Column(name="name")
    private String name;

    @Column(name="college")
    private String college;
    @Column(name="company")
    private String company;
    @Column(name="active")
    private boolean active;
    @Column(name="otp")
    private String otp;

    public LocalDateTime getOTpGeneratedTime() {
        return OtpGeneratedTime;
    }

    public void setOTpGeneratedTime(LocalDateTime tpGeneratedTime) {
        this.OtpGeneratedTime = tpGeneratedTime;
    }

    @Column
    private LocalDateTime OtpGeneratedTime;
    public boolean isActive() {
        return active;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //also create a default contructor
    public User(){

    }

    public User(String username, String password, String emailId) {
        super();
        this.username = username;
        this.password = password;
        this.emailId= emailId;
        this.college=college;
        this.name=name;
        this.company=company;
        this.otp=otp;
        this.active=false;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}