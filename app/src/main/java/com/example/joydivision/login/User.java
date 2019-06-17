package com.example.joydivision.login;

public class User {

    private String name;
    private String phone;
    private String password;
    private String email;
    private String address;



    public User(String name, String phone, String password, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String surName) {
        this.phone = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
