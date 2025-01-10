package com.developer.AttendanceApp.Service;

import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepo.findByname(username);

        if (employee != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(employee.getName())
                    .password(employee.getPassword())
                    .roles(employee.getRoles().toArray(new String[0]))
                    .build();
        }
        else {
            throw new UsernameNotFoundException("there is no user related to this name " + username);
        }
    }

}
