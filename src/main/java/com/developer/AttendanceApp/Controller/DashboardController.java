package com.developer.AttendanceApp.Controller;

import com.developer.AttendanceApp.Entity.Employee;
import com.developer.AttendanceApp.Repository.EmployeeRepo;
import com.developer.AttendanceApp.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/user-details")

    public ResponseEntity<?> getDashboardDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Employee employee = employeeRepo.findByname(name);
        // Fetch the current date

        Object dashboard = dashboardService.getDashboard(employee);

        return new ResponseEntity<>(dashboard,HttpStatus.OK);


    }
}