package com.developer.AttendanceApp.Entity;

import jakarta.persistence.Entity;


public class LoginPageDetail {

    String name;
     String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
