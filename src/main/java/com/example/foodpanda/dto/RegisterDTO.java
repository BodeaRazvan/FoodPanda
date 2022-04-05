package com.example.foodpanda.dto;

import javax.persistence.Column;

public class RegisterDTO {
    private String username;
    private String password1;
    private String password2;
    private String address;
    private String email;

    public RegisterDTO(String username, String password1, String password2, String address, String email) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
        this.address = address;
        this.email = email;
    }

    public RegisterDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password) {
        this.password1 = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

}
