package com.omnilearn.springbootbackend.Dto;

import jakarta.persistence.Column;

public class RegisterDto {
    private String password;

    private String emailId;
    private String name;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String college;

    public RegisterDto(String password, String emailId, String name, String college, String company,String username) {
        this.password = password;
        this.emailId = emailId;
        this.name = name;
        this.college = college;
        this.company = company;
        this.username = username;
    }

    private String company;

    public String getEmailId() {
        return emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
