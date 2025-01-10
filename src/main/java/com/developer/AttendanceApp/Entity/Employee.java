package com.developer.AttendanceApp.Entity;
import com.developer.AttendanceApp.Service.UserDetailsServiceImpl;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
public class Employee {
    @Id
    @Column(name = "employee_id")
    long id;
    @Column(name = "name")
    String name ;
    @Column(name = "mail")
    String email;
    @Column(name = "password")
    @NonNull
    String password;

    @Column(name = "address")
    String address;
    @Column(name  = "profilePicture")
    String profilePicture;
    @Column(name = "Position")
    String position;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Column(name ="roles")
    private List<String> roles;

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    // for id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    // for name
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    // for mail
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }


    // for address
    public void setAddress(String address) {
        this.address = address;}

    public String getAddress() {
        return address;
    }



}
